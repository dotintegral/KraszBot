package org.siery.irc;

import java.sql.SQLException;

import org.siery.irc.config.ConfigHolder;
import org.siery.irc.config.ConnectionConfigHolder;
import org.siery.irc.db.model.ActivityModel;

public class KraszBotMain {

	public static void main(String[] args) throws Exception {
		
		//test();
		
		initDataBase();

		ConnectionConfigHolder config = ConfigHolder.getInstance().getConnectionConfigHolder();
		KraszBot bot = new KraszBot(config.getName());
	
		bot.setMessageDelay(config.getMessagesDelay());
		bot.setVerbose(true);
		bot.connect(config.getServer());
		
		for(String channel : config.getChannels())
			bot.joinChannel(channel);
		
	}
	
	private static void initDataBase() {
		
		try {
			ActivityModel.createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void test () {
		
		System.out.println("TEST EXIT");
		System.exit(0);
	}

}