package com.project.bookGroup.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.bookGroup.models.User;
import com.project.bookGroup.repositories.UserRepo;

@Service
public class UserService {
	
	private UserRepo _ur;
	private BCryptPasswordEncoder _bcrypt;
		
	public UserService(UserRepo _ur){
		this._ur = _ur;
		this._bcrypt = new BCryptPasswordEncoder();

	}
	
	
	//****************************************Create
	public void createUser(User user) {
		user.setPassword(_bcrypt.encode(user.getPassword()));
		if( ((List<User>) _ur.findAll()).size() == 0) {
			user.setLevel(1);
		} else {
			user.setLevel(0);
		}
		_ur.save(user);
	}
	
	//****************************************Retrive/Read
	public User getUserByUsername(String username) {
		return _ur.findByUsername(username);
	}
	
	public User getUserById(Long id) {
		return _ur.findOne(id);
	}
	
	public boolean checkUserPassword(User user, String rawPassword) {
		return (_bcrypt.matches(rawPassword, user.getPassword()));
	}
	
	public List<User> getAllUsers() {
		return (List<User>) _ur.findAll();
	}
	
	public List<User> getUsersWithExclusions(List<User> invitees) {
		return (List<User>) _ur.getUsersWithExclusions(invitees);
	}
	
}
