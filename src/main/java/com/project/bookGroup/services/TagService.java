package com.project.bookGroup.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bookGroup.models.Tag;
import com.project.bookGroup.repositories.TagRepo;

@Service
public class TagService {
	
	private TagRepo _tr;
		
	public TagService(TagRepo _tr){
		this._tr = _tr;
	}
	
	public String tagsToString(List<Tag> tagsList) {
		ArrayList<String> tags = new ArrayList<>();
		for (Tag tag : tagsList) {
			tags.add(tag.getName());
		}
		return String.join(", ", tags);
	}
	
	public List<Tag> tagsToList(String tagsString) {
		String[] tagsArray = tagsString.toLowerCase().split("\\s*,\\s*");	//see DojoOverflow assignment for additional validations here.
		List<Tag> tagList = new ArrayList<Tag>();
		for (String element : tagsArray) {
			Tag temp = getTagByName(element);
			if (temp == null) {
				temp = new Tag(element);
				createTag(temp);
				temp = getTagByName(temp.getName());
			}
			tagList.add(temp);
		}
		return tagList;
	}
	
	public Tag getTagByName(String name) {
		return _tr.findByName(name);
	}
	
	public Tag getTagById(Long id) {
		return _tr.findOne(id);
	}
	
	public void createTag(Tag tag) {
		_tr.save(tag);
	}
	
}
