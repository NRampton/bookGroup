package com.project.bookGroup.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.bookGroup.models.Group;
import com.project.bookGroup.models.User;
import com.project.bookGroup.repositories.GroupRepo;

@Service
public class GroupService {
	
	private GroupRepo _gr;
	private UserService _us;

	public GroupService(GroupRepo _gr, UserService _us) {
		super();
		this._gr = _gr;
		this._us = _us;
	}
	
	public Group removeInvitee(Long inviteeId, Group newGroup) {
		User uninvitee = _us.getUserById(inviteeId);
		List<User> inviteesList = newGroup.getInvitees();
		inviteesList.remove(uninvitee);
		newGroup.setInvitees(inviteesList);
		return newGroup;
	}
	
	public Group addInvitee(Long inviteeId, Group newGroup) {
		User invitee = _us.getUserById(inviteeId);
		System.out.println("Here's the user we got in the GroupService based on that id: " + invitee.getUsername());
		List<User> inviteesList;
		try {
			inviteesList = newGroup.getInvitees();
		} catch (NullPointerException e) {
			inviteesList = new ArrayList<User>();
		}		
		inviteesList.add(invitee);
		System.out.println("And while we're at it, here's the list we're going to try to set to the newGroup: " + inviteesList.toString());
		newGroup.setInvitees(inviteesList);
		return newGroup;
	}
	
	public Group stageOneMerge(Group newGroup, Group supplement) {
		newGroup.setName(supplement.getName());
		newGroup.setLocation(supplement.getLocation());
		return newGroup;
	}	
}
