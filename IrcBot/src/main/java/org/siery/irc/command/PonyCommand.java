package org.siery.irc.command;

import java.util.Random;

import org.siery.irc.command.abstracts.UserCommand;

public class PonyCommand extends UserCommand {

	public String getCommand() {
		return "pony";
	}

	public String getUsage() {
		return null;
	}

	public String getInfo() {
		return null;
	}

	protected void onSuccess() {
		String message = "<3 My Little Pony";
		
		Random r = new Random();
		int i = r.nextInt(4);
		
		if(i == 1) 
			message = "Fluttershy FTW!";
		if(i == 2)
			message = "I'm a Brony!";
		if(i == 3)
			message = "Friendship is magic!";
		
		getContext().sendMessage(message);
	}

	protected void onFailiture() {
		getContext().sendMessage("fail");
	}
}
