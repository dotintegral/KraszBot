package org.siery.irc.command.utils;

import java.util.ArrayList;

import org.siery.irc.command.abstracts.Command;
import org.siery.irc.exception.CommandNotFoundException;

public class CommandHolder {
	
	private ArrayList<Command> commands;
	
	public CommandHolder() {
		commands = CommandFactory.getCommands();
	}
	
	public Command getCommand(String com) throws CommandNotFoundException {
		
		System.out.println("Getting command for string:" + com);
		
		for(Command c : commands) {
			if(com.matches("^\\" + Command.getCommandPrefix() +c.getCommand() + ".*$" )) {
				return c;
			}
		}
		
		throw new CommandNotFoundException();
	}
	
	public ArrayList<String> getCommandList() {
		ArrayList<String> commandList = new ArrayList<String>();
		
		for(Command c : commands)
			commandList.add(c.getCommand());
		
		return commandList;
	}
}
