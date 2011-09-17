package org.siery.irc.context;

import java.util.ArrayList;

import org.siery.irc.KraszBot;
import org.siery.irc.command.utils.CommandHolder;
import org.siery.irc.user.User;

public class CommandContext extends GenericContext {

	// TODO: Rozważyć dodanie Message jako pola w kontekście
	
	public CommandContext(KraszBot bot, User user, String channel) {
		super(bot, user, channel);
	}
	
	public CommandHolder getCommandHolder() {
		return bot.getCommandHolder();
	}
}
