package org.siery.irc.command.utils;

import java.util.ArrayList;

import org.siery.irc.command.CommandsCommand;
import org.siery.irc.command.HelpCommand;
import org.siery.irc.command.Magic8BallCommand;
import org.siery.irc.command.NickHuntCommand;
import org.siery.irc.command.PonyCommand;
import org.siery.irc.command.ReloadCommand;
import org.siery.irc.command.TestCommand;
import org.siery.irc.command.abstracts.Command;

public class CommandFactory {
	
	public static ArrayList<Command> getCommands() {

		ArrayList<Command> commands = new ArrayList<Command>();

		commands.add(new CommandsCommand());
		commands.add(new TestCommand());
		commands.add(new PonyCommand());
		commands.add(new NickHuntCommand());
		commands.add(new ReloadCommand());
		commands.add(new HelpCommand());
		commands.add(new Magic8BallCommand());

		return commands;
	}
}
