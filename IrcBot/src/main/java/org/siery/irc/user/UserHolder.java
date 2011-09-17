package org.siery.irc.user;

import java.util.ArrayList;

public class UserHolder {

	private ArrayList<User> users;
	
	public UserHolder() {
		users = UserFactory.getUsers();
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public User updateRole(User user) {
		
		for(User listUser : users)
			if(listUser.matches(user))
				return listUser;
		
		return user;
	}
}
