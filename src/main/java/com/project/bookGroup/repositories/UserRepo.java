package com.project.bookGroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bookGroup.models.User;

@Repository 												
public interface UserRepo extends CrudRepository<User,Long>{
	User findByUsername(String username);
	@Query(value="select user from User user where user not in ?1")
	List<User> getUsersWithExclusions(List<User> invitees);
	
	// Example:
	// public YourModelHere findByName(String name);
}
