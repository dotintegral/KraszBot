package org.siery.irc.command;

import java.util.List;

import org.siery.irc.command.abstracts.ArgumentCommand;
import org.siery.irc.command.abstracts.Command;
import org.siery.irc.command.abstracts.UserCommand;
import org.siery.irc.command.utils.CommandHolder;
import org.siery.irc.exception.CommandNotFoundException;

public class HelpCommand extends UserCommand implements ArgumentCommand {

	private String command;
	
	public void setAgruments(List<String> args) {
		command = args.get(0);
		
		if(!command.startsWith(Command.getCommandPrefix()))
			command = Command.getCommandPrefix() + command;
	}

	public String getCommand() {
		return "help";
	}

	public String getUsage() {
		return Command.getCommandPrefix() + "help nazwa_komendy";
	}

	public String getInfo() {
		return getContext().getUser().getNick() + ", serio, potrzebujesz pomocy do pomocy?";
	}

	protected void onSuccess() {
		CommandHolder commandHolder = getContext().getCommandHolder();
		try {
			Command c = commandHolder.getCommand(command);
			getContext().sendMessage("Użytkowanie: " + c.getUsage());
			getContext().sendMessage(c.getInfo());
		} catch (CommandNotFoundException e) {
			getContext().sendMessage("Komenda " + command + " nie istnieje");
		}
	}

	protected void onFailiture() {

	}

}
