package com.project.bookGroup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.bookGroup.models.Tag;

@Repository 												
public interface TagRepo extends CrudRepository<Tag,Long>{
	public Tag findByName(String name);
}
