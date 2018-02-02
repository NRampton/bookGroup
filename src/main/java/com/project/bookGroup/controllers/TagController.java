package com.project.bookGroup.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.bookGroup.models.Tag;
import com.project.bookGroup.models.User;
import com.project.bookGroup.services.BookService;
import com.project.bookGroup.services.TagService;
import com.project.bookGroup.services.UserService;

@Controller
@RequestMapping("/tags")
public class TagController{
	
	private TagService _ts;
	private UserService _us;
	private BookService _bs;

	public TagController(TagService _ts, UserService _us, BookService _bs) {
		super();
		this._ts = _ts;
		this._us = _us;
		this._bs = _bs;
	}



	@RequestMapping("/{tagId}")
	public String showTagDisplay(@PathVariable("tagId") Long tagId, Model model, HttpSession session){
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		Tag currentTag = _ts.getTagById(tagId);
		if (currentTag == null) {
			return "redirect:/books";					//need a flash message to let the user know what happened. If this happens, something's busted.
		}
		model.addAttribute("currentTag", currentTag);
		model.addAttribute("booksSans", _bs.getBooksSansTag(currentTag));
//		model.addAttribute("books", _bs.getBooksByTag(currentTag));
		return "displayTag";
	}	
	
}
