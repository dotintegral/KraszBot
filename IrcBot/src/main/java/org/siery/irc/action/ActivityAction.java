package org.siery.irc.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.siery.irc.context.ActionContext;
import org.siery.irc.db.model.ActivityModel;

public class ActivityAction implements Action {


	public boolean toExecute(ActionContext context) {
		if( context.getActionType() == ActionType.PART || 
				context.getActionType() == ActionType.QUIT || 
				context.getActionType() == ActionType.JOIN || 
				( context.hasChannel() && context.isChannelAction() ))
			return true;
		else
			return false;
	}

	public void execute(ActionContext context) {
		
		try {
			tryExecute(context);
		} catch (SQLException e) {
			System.out.println("Problem with database!");
			e.printStackTrace();
		}
		
	}
	
	private void tryExecute(ActionContext context) throws SQLException {
		
		ActivityModel activityModel = null;
		ActivityModel.Columns column = null;
		String nick = context.getUser().getNick();
		String status = "";
		
		if( context.getActionType() == ActionType.JOIN) {
			String channel = context.getChannel();
			
			activityModel = new ActivityModel(nick, channel);
			column = column.JOIN_DATE;
			status = "online";
			
		} else if( context.getActionType() == ActionType.PART) {
			String channel = context.getChannel();
			
			activityModel = new ActivityModel(nick, channel);
			column = column.PART_DATE;
			status = "offline";
			
		} else if( context.getActionType() == ActionType.QUIT) {

			activityModel = new ActivityModel(nick);
			column = column.PART_DATE;
			status = "offline";
			
		} else {
			String channel = context.getChannel();
			
			activityModel = new ActivityModel(nick, channel);
			column = column.ACTIVITY_DATE;	
			status = "online";
		}
		
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		String date = df.format(cal.getTime());
		
		activityModel.update(column, date);
		activityModel.update(column.ONLINE, status);
		
		ResultSet rs = activityModel.getResultSet();
		
		while(rs.next()) {
			System.out.println("$$$ " + rs.getString("nick") + " " + rs.getString("channel") + ": " +
					rs.getString("online")  + " (j: " + rs.getString("join_date")  + ")  (p: " + 
					rs.getString("part_date")  + ") (a: " + rs.getString("activity_date") + ")");
		}
		
	}

}
