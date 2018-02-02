package com.project.bookGroup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bookGroup.models.User;

@Repository 												
public interface UserRepo extends CrudRepository<User,Long>{
	User findByUsername(String username);
	
	// Example:
	// public YourModelHere findByName(String name);
}
