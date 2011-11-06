package org.siery.irc;

import org.jibble.pircbot.*;
import org.siery.irc.config.ConfigHolder;
import org.siery.irc.config.ConnectionConfigHolder;

public class KraszBotMain {

	public static void main(String[] args) throws Exception {

		ConnectionConfigHolder config = ConfigHolder.getInstance().getConnectionConfigHolder();
		KraszBot bot = new KraszBot(config.getName());
	
		bot.setMessageDelay(config.getMessagesDelay());
		bot.setVerbose(true);
		bot.connect(config.getServer());
		
		for(String channel : config.getChannels())
			bot.joinChannel(channel);
		
	}

}