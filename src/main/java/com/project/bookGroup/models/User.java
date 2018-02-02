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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User{
	
	//Fields
	@Id
	@GeneratedValue
	private long id;

	@Size(min=1)
	private String username;
	@Email()
	@Size(min=1)	
	private String email;
	@Size(min=8)
	private String password;
	private String location;
	@Size(min=2, max=2)
	private String state;
	@Size(max=1000)
	private String bio;
	
	private int level;
	
	@Transient
	private String confirm;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM:dd:yyyy HH:mm:ss")
	private Date updatedAt;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Message> sentMessages;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_threads",
		joinColumns=@JoinColumn(name="user_id"), 
		inverseJoinColumns=@JoinColumn(name="thread_id")
	)
	private List<Thread> threads;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_groups",
		joinColumns=@JoinColumn(name="user_id"), 
		inverseJoinColumns=@JoinColumn(name="group_id")
	)
	private List<Group> groups;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="invitees_groups",
		joinColumns=@JoinColumn(name="invitee_id"), 
		inverseJoinColumns=@JoinColumn(name="group_id")
	)
	private List<Group> groupsInvited;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="invitees_groups",
		joinColumns=@JoinColumn(name="non_attendee_id"), 
		inverseJoinColumns=@JoinColumn(name="group_id")
	)
	private List<Group> groupsNotAttending;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_books",
		joinColumns=@JoinColumn(name="user_id"), 
		inverseJoinColumns=@JoinColumn(name="book_id")
	)
	private List<Book> books;
	
	
	
	//created-/updatedAt
	@PrePersist
	public void onCreate(){this.createdAt = new Date();}
	@PreUpdate
	public void onUpdate(){this.updatedAt = new Date();}
	
	//getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public List<Message> getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}
	public List<Thread> getThreads() {
		return threads;
	}
	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	//constructors
	public User(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}
