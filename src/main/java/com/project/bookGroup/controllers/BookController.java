package com.project.bookGroup.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.bookGroup.models.Book;
import com.project.bookGroup.models.User;
import com.project.bookGroup.services.BookService;
import com.project.bookGroup.services.TagService;
import com.project.bookGroup.services.UserService;

@Controller
@RequestMapping("/books")
public class BookController{
	
	private BookService _bs;
	private UserService _us;
	private TagService _ts;

	public BookController(BookService _bs, UserService _us, TagService _ts){
		this._bs = _bs;
		this._us = _us;
		this._ts = _ts;
	}
	
	@RequestMapping("")
	public String showAllBooks(Model model, HttpSession session){
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allBooks", _bs.getAllBooks());
		return "booksMaster";
	}
	
	@RequestMapping("/new")
	public String showNewBookForm(Model model, HttpSession session, @ModelAttribute("book") Book book) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		model.addAttribute("currentUser", currentUser);
		return "newBook";
	}
	
	@PostMapping("/new")
	public String addNewBook(Model model, HttpSession session, @ModelAttribute("book") Book book, @RequestParam("newTags") String tags) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		book.setTags(_ts.tagsToList(tags));
		_bs.createBook(book);
		return "redirect:/books";
	}
	
	@RequestMapping("/remove/{id}")
	public String removeFromList(HttpSession session, @PathVariable("id") Long id) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		_bs.removeBookById(currentUser, id);
		return "redirect:/books";
	}
	
	@RequestMapping("/remove_dash/{id}")
	public String removeFromListDash(HttpSession session, @PathVariable("id") Long id) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		_bs.removeBookById(currentUser, id);
		return "redirect:/users/dashboard";
	}
	
	@RequestMapping("add/{id}")
	public String addToList(HttpSession session, @PathVariable("id") Long id) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		_bs.addBookById(currentUser, id);
		return "redirect:/books";
	}
	
	@RequestMapping("/{id}")
	public String showBook(HttpSession session, @PathVariable("id") Long id, Model model) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		Book currentBook = _bs.getBookById(id);
		model.addAttribute("currentBook", currentBook);
		model.addAttribute("currentUser", currentUser);
		return "displayBook";
	}
	
	@RequestMapping("/edit/{bookId}")
	public String showEditBookForm(Model model, @PathVariable("bookId") Long bookId, HttpSession session) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		Book currentBook = _bs.getBookById(bookId);
		model.addAttribute("tags", _ts.tagsToString(currentBook.getTags()));
		model.addAttribute("currentBook", currentBook);
		return "editBook";
	}
	
	@PostMapping("/edit/{bookId}")
	public String updateBook(HttpSession session, Model model, @RequestParam("newTags") String newTags, @PathVariable("bookId") Long bookId, @ModelAttribute("currentBook") Book currentBook) {
		User currentUser = _us.getUserById((Long) session.getAttribute("id"));
		if (currentUser == null) {
			return "redirect:/logout";
		}
		_bs.updateBook(currentBook, bookId, newTags);
		return "redirect:/users/dashboard";
	}
	
}
