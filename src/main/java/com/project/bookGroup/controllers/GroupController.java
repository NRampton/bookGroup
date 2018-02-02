package com.project.bookGroup.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String showNewGroupForm(HttpSession session, Model model, @PathVariable("bookId") Long bookId){
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
//		
//		try {
//			newGroup = (Group) session.getAttribute("newGroup");
//			invitees = newGroup.getInvitees();
//			System.out.println("We've successfully pulled newGroup from session. It's holding a group with the name: " + newGroup.getName());
//		} catch (NullPointerException e) {
//			newGroup = new Group();
//			invitees = new ArrayList<>();
//			invitees.add(currentUser);
//			newGroup.setInvitees(invitees);
//			System.out.println("Initial render. No newGroup in session, so we made a new one. This time the list of invitees includes: " + newGroup.getInvitees().get(0).getUsername());
//		}
//		model.addAttribute("newGroup", newGroup);
		
//		session.setAttribute("newGroup", newGroup);
//		model.addAttribute("uninvitedUsers", uninvitedUsers);
		model.addAttribute("book", _bs.getBookById(bookId));
		return "newGroupForm";
	}
	
	@PostMapping("/createStageOne")
	public String createGroupStageOne(@ModelAttribute("newGroup") Group newGroup, HttpSession session) {
		if (newGroup == null) {							//validations!
			return "redirect:/groups/new";
		}
		session.setAttribute("newGroup", newGroup);
		return "redirect:/groups/sendInvites";
	}
	
	@RequestMapping("/sendInvites")
	public String showSendInvites(Model model, HttpSession session) {
		Group newGroup;
		try {
			newGroup = (Group) session.getAttribute("newGroup");
		} catch (NullPointerException e) {
			return "redirect:/groups/new";
		}
		List<User> invitees = newGroup.getInvitees();
		List<User> uninvitedUsers = _us.getAllUsers();
		if (invitees.size() > 0) {
			for (User invitee : invitees) {
				uninvitedUsers.remove(invitee);
			}
		}
		model.addAttribute("newGroup", newGroup);
		model.addAttribute("uninvitedUsers", uninvitedUsers);
		return "sendInvites";
	}
	
	@PostMapping("/new")								//create a new group!
	public String createNewGroup() {
		return "redirect:/users/dashboard";
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
