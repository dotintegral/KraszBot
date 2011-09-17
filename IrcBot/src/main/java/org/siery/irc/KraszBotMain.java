package org.siery.irc;

import org.jibble.pircbot.*;

public class KraszBotMain {

	public static void main(String[] args) throws Exception {

		KraszBot bot = new KraszBot();
		bot.setVerbose(false);
		bot.connect("irc.quakenet.org");
		bot.joinChannel("#kucbot");
		bot.joinChannel("#karton");
	}

}