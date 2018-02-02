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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="groups")
public class Group{
	@Id
	@GeneratedValue
	private long id;

	private String name;
	private Date date;
	private String dispDate;
	private Date time;
	private String dispTime;
	
	private String location;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_groups",
		joinColumns=@JoinColumn(name="group_id"), 
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> users;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="invitees_groups",
		joinColumns=@JoinColumn(name="group_id"), 
		inverseJoinColumns=@JoinColumn(name="invitee_id")
	)
	private List<User> invitees;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="attendees_groups",
		joinColumns=@JoinColumn(name="group_id"), 
		inverseJoinColumns=@JoinColumn(name="non_attendee_id")
	)
	private List<User> nonAttendees;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Book subjectBook;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDispDate() {
		return dispDate;
	}
	public void setDispDate(String dispDate) {
		this.dispDate = dispDate;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDispTime() {
		return dispTime;
	}
	public void setDispTime(String dispTime) {
		this.dispTime = dispTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<User> getInvitees() {
		return invitees;
	}
	public void setInvitees(List<User> invitees) {
		this.invitees = invitees;
	}
	public List<User> getNonAttendees() {
		return nonAttendees;
	}
	public void setNonAttendees(List<User> nonAttendees) {
		this.nonAttendees = nonAttendees;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Book getSubjectBook() {
		return subjectBook;
	}
	public void setSubjectBook(Book subjectBook) {
		this.subjectBook = subjectBook;
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
	
	public Group(){
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
}
