package org.siery.irc.db.model;

import java.sql.*;
import java.util.Map;

import org.siery.irc.db.DBConnection;

public class ActivityModel {
	private static final String tableName = "activity";
	
	private Connection connection;
	private String nick;
	private String channel;
	private ResultSet resultSet;
	
	public ActivityModel(String nick, String channel) throws SQLException {
		this.connection = DBConnection.getInstance().getConnection();
		this.nick = nick;
		this.channel = channel;
		
		resultSet = select();
		
		if(!resultSet.next()) {
			insert();
		}
	}
	
	public ActivityModel(String nick) throws SQLException {
		this.connection = DBConnection.getInstance().getConnection();
		this.nick = nick;
		
		resultSet = select();
	}


	public static void createTable() throws SQLException {
		Statement s = DBConnection.getInstance().getConnection().createStatement();
		
		s.executeUpdate("drop table if exists " +tableName);
		s.executeUpdate("create table if not exists " +tableName+ " (nick, channel, online, join_date, part_date, activity_date)");
	}
	
	private ResultSet select() throws SQLException {
		Statement s = connection.createStatement();
		
		String sql = "SELECT * FROM " +tableName+ " WHERE nick='" +nick+ "' ";
		if(channel != null) {
			sql += " AND channel='" +channel+ "';";
		}
		
		ResultSet rs = s.executeQuery(sql);
		
		return rs;
	}
	
	private void insert() throws SQLException {
		PreparedStatement prep = connection.prepareStatement("insert into " +tableName+ " values (?, ?, ?, ?, ?, ?);");

	    prep.setString(1, nick);
	    prep.setString(2, channel);
	    prep.setString(3, "1");
	    prep.setString(4, "1");
	    prep.setString(5, "1");
	    prep.setString(6, "1");
	    prep.addBatch();
	    
	    connection.setAutoCommit(false);
	    prep.executeBatch();
	    connection.setAutoCommit(true);
	}
	
	public void update(Columns column, String value) throws SQLException {
		
		String sql = "UPDATE " +tableName+ " SET "+column.getColumn()+"='"+value+"' WHERE nick='"+nick+"' ";
		if(channel != null) {
			sql += " AND channel='" +channel+ "';";
		}
		
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.executeUpdate();
	}
	
	public ResultSet getResultSet() throws SQLException {
		return select();
	}

	public enum Columns {
		NICK("nick"),
		CHANNEL("channel"),
		ONLINE("online"),
		JOIN_DATE("join_date"),
		PART_DATE("part_date"),
		ACTIVITY_DATE("activity_date");
		
		private String column;
		
		Columns(String column) {
			this.column = column;
		}
		
		public String getColumn() {
			return column;
		}
	}
}
