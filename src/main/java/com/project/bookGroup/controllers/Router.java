package com.project.bookGroup.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/*") // Wildcard all routes.
public class Router{
	
	
	
	public Router() {

	}

	@RequestMapping("")
	public String redirectToRegistration(HttpServletRequest req){		
		return "redirect:/users/registration";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/registration";
	}
	
}
