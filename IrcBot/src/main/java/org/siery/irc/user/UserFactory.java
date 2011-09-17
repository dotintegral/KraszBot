package org.siery.irc.user;

import java.util.ArrayList;

public class UserFactory {

	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		users.add( new Owner("artur", "*", "*") );
		
		return users;
	}
}
