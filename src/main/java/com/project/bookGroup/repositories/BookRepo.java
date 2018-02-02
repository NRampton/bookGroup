package com.project.bookGroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bookGroup.models.Book;
import com.project.bookGroup.models.Tag;

@Repository 												
public interface BookRepo extends CrudRepository<Book,Long>{
	
	@Query(value="SELECT b FROM Book b WHERE :tag IN b.tags")
	List<Book> findByTag(Tag tag);
	
	// Query methods go here.
	
	// Example:
	// public YourModelHere findByName(String name);
}
