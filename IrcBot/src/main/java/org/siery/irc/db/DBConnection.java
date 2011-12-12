package org.siery.irc.db;

import java.sql.*;

public class DBConnection {

	private static DBConnection instance;
	private Connection connection;
	
	private DBConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	    try {
			connection = DriverManager.getConnection("jdbc:sqlite:kraszbot.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public Connection getConnection() {
		return connection;
	}
	
	public static DBConnection getInstance() {
		if(instance == null)
			instance = new DBConnection();
		return instance;
	}
}
