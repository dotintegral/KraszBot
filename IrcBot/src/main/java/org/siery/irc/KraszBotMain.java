package org.siery.irc;

import org.jibble.pircbot.*;

public class KraszBotMain {

	public static void main(String[] args) throws Exception {

		KraszBot bot = new KraszBot();
		bot.setMessageDelay(500);
		bot.setVerbose(true);
		bot.connect("irc.quakenet.org");
		bot.joinChannel("#kucbot");
		
	}

}