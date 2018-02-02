package com.project.bookGroup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bookGroup.models.Group;

@Repository 												
public interface GroupRepo extends CrudRepository<Group,Long>{
	// Query methods go here.
	
	// Example:
	// public YourModelHere findByName(String name);
}
