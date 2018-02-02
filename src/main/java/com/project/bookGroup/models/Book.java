package com.project.bookGroup.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book{
	@Id
	@GeneratedValue
	private long id;

	private String title;
	private String author;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_books",
		joinColumns=@JoinColumn(name="book_id"), 
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> users;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="tags_books",
		joinColumns=@JoinColumn(name="book_id"), 
		inverseJoinColumns=@JoinColumn(name="tag_id")
	)
	private List<Tag> tags;
	
	private String tagsString;
	
	@OneToMany(mappedBy="subjectBook", fetch=FetchType.LAZY)
	private List<Group> groups;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;

	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getTagsString() {
		return tagsString;
	}
	public void setTagsString(String tagsString) {
		this.tagsString = tagsString;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	// Setters and Getters go here
	
	public Book(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}
