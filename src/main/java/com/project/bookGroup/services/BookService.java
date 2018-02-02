package com.project.bookGroup.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bookGroup.models.Book;
import com.project.bookGroup.models.Tag;
import com.project.bookGroup.models.User;
import com.project.bookGroup.repositories.BookRepo;
import com.project.bookGroup.repositories.TagRepo;
import com.project.bookGroup.repositories.UserRepo;

@Service
public class BookService {
	
	private BookRepo _br;
	private UserRepo _ur;
	private TagService _ts;
	private TagRepo _tr;

	public BookService(BookRepo _br, UserRepo _ur, TagService _ts, TagRepo _tr) {
		super();
		this._br = _br;
		this._ur = _ur;
		this._ts = _ts;
		this._tr = _tr;
	}
		
	public List<Book> getAllBooks() {
		return (List<Book>) _br.findAll();
	}
	
	public void createBook(Book book) {
		_br.save(book);
	}
	
	public Book getBookById(Long id) {
		return _br.findOne(id);
	}
	
	public void removeBookById(User user, Long id) {
		Book book = _br.findOne(id);
		List<Book> books = user.getBooks();
		books.remove(book);
		user.setBooks(books);
		_ur.save(user);
	}
	
	public void addBookById(User user, Long id) {
		Book book = _br.findOne(id);
		List<Book> books = user.getBooks();
		books.add(0, book);
		user.setBooks(books);
		_ur.save(user);
	}
	
	public void updateBook(Book updatedBook, Long id, String newTags) {
		Book book = _br.findOne(id);		
		book.setTags(_ts.tagsToList(newTags));
		book.setTitle(updatedBook.getTitle());
		book.setAuthor(updatedBook.getAuthor());
		_br.save(book);
	}
	
	public List<Book> getBooksSansTag(Tag tag) {
		List<Book> booksByTag = tag.getBooks();
		for (Book book : booksByTag) {
			List<Tag> tagList = book.getTags();
			tagList.remove(tag);
			book.setTags(tagList);
		}
		return booksByTag;
	}
	
	public List<Book> getBooksByTag(Tag tag) {
		return _br.findByTag(tag);
	}
	
}
