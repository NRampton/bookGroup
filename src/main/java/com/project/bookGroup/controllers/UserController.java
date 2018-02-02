package com.project.bookGroup.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.bookGroup.models.User;
import com.project.bookGroup.services.UserService;
import com.project.bookGroup.validators.UserValidator;

@Controller
@RequestMapping("/users")
public class UserController{
	
	private UserService _us;
	private UserValidator _uv;

	public UserController(UserService _us, UserValidator _uv){
		this._us = _us;
		this._uv = _uv;
	}
	
	@RequestMapping("/registration")
	public String showRegistration(HttpSession session, @ModelAttribute("user") User user){
		session.invalidate();
		return "register";
	}	
	
	@PostMapping("/registration")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		_uv.validate(user, result);
		if (result.hasErrors()) {
			return "register";
		}
		_us.createUser(user);
		User loggedIn = _us.getUserByUsername(user.getUsername());
		session.setAttribute("id", loggedIn.getId());
		return "redirect:/users/dashboard";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes rA, HttpSession session) {
		User user = _us.getUserByUsername(username);
		if(user == null) {
			rA.addFlashAttribute("error", "Sorry, we didn't recognize those credentials.");
			session.invalidate();
			return "redirect:/users/registration";
		}
		if (_us.checkUserPassword(user, password)) {					//if their credentials check out:
			session.setAttribute("id", user.getId());	
			return "redirect:/users/dashboard";
		}
		rA.addFlashAttribute("error", "Invalid credentials.");
		session.invalidate();
		return "redirect:/users/registration";
	}
	
	@RequestMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		model.addAttribute("currentUser", currentUser);
		return "dashboard";
	}
	
}
