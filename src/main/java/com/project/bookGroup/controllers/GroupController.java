package com.project.bookGroup.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bookGroup.models.Book;
import com.project.bookGroup.models.Group;
import com.project.bookGroup.models.User;
import com.project.bookGroup.services.BookService;
import com.project.bookGroup.services.GroupService;
import com.project.bookGroup.services.UserService;

@Controller
@RequestMapping("/groups")
public class GroupController{
	//Member variables go here

	private GroupService _gs;
	private UserService _us;
	private BookService _bs;
	
	public GroupController(GroupService _gs, UserService _us, BookService _bs) {
		super();
		this._gs = _gs;
		this._us = _us;
		this._bs = _bs;
	}

	@RequestMapping("/new/{bookId}")
	public String showNewGroupForm(HttpSession session, Model model, @PathVariable("bookId") Long bookId, @ModelAttribute("newGroupAddl") Group newGroupAddl){
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		//Keeping track of the book we're looking to build a group around
		Book newGroupBook = _bs.getBookById(bookId);
		session.setAttribute("newGroupBook", newGroupBook);
		
		Group newGroup;
		List<User> invitees;
		
		//If there's already a group being created in session, redirect to the second-stage page.
		try {
			newGroup = (Group) session.getAttribute("newGroup");
			invitees = newGroup.getInvitees();
			System.out.println("We've successfully pulled newGroup from session. It's holding a group with the name: " + newGroup.getName());
			return "redirect:/groups/sendInvites";
			
		// If not, create a new group object, invite the current user, relate it to the subject book, and render the page
		} catch (NullPointerException e) {
			newGroup = new Group();
			invitees = new ArrayList<>();
			invitees.add(currentUser);
			newGroup.setInvitees(invitees);
			newGroup.setSubjectBook(newGroupBook);
			System.out.println("Initial render. No newGroup in session, so we made a new one. This time the list of invitees includes: " + newGroup.getInvitees().get(0).getUsername());
		}
		//Going into page render, we've got the whole newGroup (with the currentUser invitee and subject book) to draw on.
		model.addAttribute("newGroup", newGroup);
		//We're also putting the same newGroup object into session so we can have that on form submission. Then we're merging data from the form into the session object.
		session.setAttribute("newGroup", newGroup);
		return "newGroupForm";
	}
	
	@PostMapping("/createStageOne")
	public String createGroupStageOne(@ModelAttribute("newGroupAddl") Group newGroupAddl, HttpSession session) {
		//Need to validate incoming data from the first-stage form
		//Dates & scheduling for groups is after that
		Group newGroup = (Group) session.getAttribute("newGroup");
		newGroup = _gs.stageOneMerge(newGroup, newGroupAddl);
		session.setAttribute("newGroup", newGroup);
		return "redirect:/groups/sendInvites";
	}
	
	//This route figures out who's invited, who isn't yet, and shows the two lists, along with an option to add or remove additional invitees
	@RequestMapping("/sendInvites")
	public String showSendInvites(Model model, HttpSession session) {
		Group newGroup;
		try {
			newGroup = (Group) session.getAttribute("newGroup");
		} catch (NullPointerException e) {
			return "redirect:/groups/new";
		}
		List<User> invitees = newGroup.getInvitees();
		List<User> uninvitedUsers = _us.getUsersWithExclusions(invitees);
		model.addAttribute("newGroup", newGroup);
		model.addAttribute("uninvitedUsers", uninvitedUsers);
		return "sendInvites";
	}
	
	@RequestMapping("/uninvite/{inviteeId}")
	public String removeInvitee(HttpSession session, @PathVariable("inviteeId") Long inviteeId) {
		session.setAttribute("newGroup", _gs.removeInvitee(inviteeId, (Group) session.getAttribute("newGroup")));
		return "redirect:/groups/new";
	}
	
	@PostMapping("/invite")
	public String addInvitee(@RequestParam("newInviteeId") Long newInviteeId, HttpSession session, @ModelAttribute("newGroup") Group newGroup) {
		System.out.println("The id of the user we're passing along is: " + newInviteeId);
		System.out.println("The name of the group coming back from the modelAttribute is: " + newGroup.getName());
		session.setAttribute("newGroup", _gs.addInvitee(newInviteeId, newGroup));
		return "redirect:/groups/new";
	}
	
}
