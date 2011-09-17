package org.siery.irc.command;

import javax.naming.Context;

import org.siery.irc.command.abstracts.UserCommand;

public class CommandsCommand extends UserCommand {

	public String getCommand() {
		return "commands";
	}

	public String getUsage() {
		return null;
	}

	public String getInfo() {
		return null;
	}

	protected void onSuccess() {
		String commands = "Dostępne komendy: ";
		
		for(String c : getContext().getCommandHolder().getCommandList())
			commands += getCommandPrefix() + c + " ";
		
		getContext().sendMessage(commands);	
	}

	protected void onFailiture() {
		
	}


}
