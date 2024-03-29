package org.siery.irc;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jibble.pircbot.PircBot;
import org.siery.irc.action.ActionHandler;
import org.siery.irc.action.ActionType;
import org.siery.irc.command.abstracts.ArgumentCommand;
import org.siery.irc.command.abstracts.Command;
import org.siery.irc.command.abstracts.OptionalArgumentCommand;
import org.siery.irc.command.utils.CommandHolder;
import org.siery.irc.config.ReplyConfigHolder;
import org.siery.irc.context.ActionContext;
import org.siery.irc.context.CommandContext;
import org.siery.irc.context.ReplyContext;
import org.siery.irc.dto.Message;
import org.siery.irc.exception.CommandNotFoundException;
import org.siery.irc.exception.NullArgumentsException;
import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;
import org.siery.irc.user.UserHolder;

public class KraszBot extends PircBot {
	
	private UserHolder userHolder;
	private CommandHolder commandHolder;
	private ReplyConfigHolder replyHandler;
	private ActionHandler actionHandler;

	public KraszBot(String name) {
		commandHolder = new CommandHolder();
		userHolder = new UserHolder();
		replyHandler = new ReplyConfigHolder();
		actionHandler = new ActionHandler();
		
		this.setName(name);
	}
	
	@Override
	public void onAction(String sender, String login, String hostname, String target, String action) {
		User user = new User(sender, login, hostname);
		ChannelUser channelUser = new ChannelUser(user, target, this.getServer());
		ActionContext context = new ActionContext(this, channelUser, action, ActionType.ACTION);
		executeAction(context);
	}

	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String chanelMessage) {
		Message message = new Message(channel, sender, login, hostname, chanelMessage);
		parseMessage(message);
		ActionContext context = new ActionContext(this, message);
		executeAction(context);
	}
	
	@Override
	public void onJoin(String channel, String nick, String login, String hostname) {
		User user = new User(nick, login, hostname);
		ChannelUser channelUser = new ChannelUser(user, channel, this.getServer());
		ActionContext context = new ActionContext(this, channelUser, "", ActionType.JOIN);
		executeAction(context);
	}
	
	@Override
	public void onPart(String channel, String nick, String login, String hostname) {
		User user = new User(nick, login, hostname);
		ChannelUser channelUser = new ChannelUser(user, channel, this.getServer());
		ActionContext context = new ActionContext(this, channelUser, "", ActionType.PART);
		executeAction(context);
	}
	
	@Override
	public void onQuit(String nick, String login, String hostname, String reason) {
		User user = new User(nick, login, hostname);
		ActionContext context = new ActionContext(this, user, reason, ActionType.QUIT);
		executeAction(context);
	}
	
	
	private void parseMessage(Message message) {
		
		if (isCommand(message))
			executeCommand(message);
		else
			executeReply(message);
	}

	private void executeAction(ActionContext context) {
		actionHandler.executeAction(context);
	}

	private boolean isCommand(Message message) {
		Pattern pattern = Pattern.compile("^\\"+ Command.getCommandPrefix() + ".*");
		Matcher matcher = pattern.matcher(message.getMessage());
		
		if(matcher.matches())
			return true;
		
		return false;
	}
	
	private void executeCommand(Message message) {
		User roleUser = userHolder.updateRole(message.getUser());
		CommandContext context = new CommandContext(this, roleUser, message.getChannel());
		
		try {
			tryExecuteCommand(message, context);
		} catch (CommandNotFoundException e) {}
	}

	private void tryExecuteCommand(Message message, CommandContext context) throws CommandNotFoundException {
		boolean commandEcecutionFlag = true;
		Command command = commandHolder.getCommand(message.getMessage());
		
		System.out.println("Command executed");
		
		if(command instanceof ArgumentCommand) {
			
			try {
				addArgumentsToCommand((ArgumentCommand)command, message);
			} catch (NullArgumentsException e) {
				commandEcecutionFlag = false;
				context.sendMessage("Użytkowanie: " + command.getUsage());
			}
		}
		
		if(commandEcecutionFlag)
			command.execute(context);
	}
	
	private void addArgumentsToCommand(ArgumentCommand command, Message message) throws NullArgumentsException {
		StringTokenizer tokenizer = new StringTokenizer(message.getMessage(), " ");
		tokenizer.nextToken(); // Needed, as the first token is the name of command, not an argument 
		
		if(!tokenizer.hasMoreTokens())
			throw new NullArgumentsException();
		
		ArrayList<String> arguments = new ArrayList<String>();
		while(tokenizer.hasMoreTokens())
			arguments.add(tokenizer.nextToken());
		
		
		command.setAgruments(arguments);
	}

	public CommandHolder getCommandHolder() {
		return commandHolder;
	}
	
	private void executeReply(Message message) {
		ReplyContext context = new ReplyContext(this, message);
		replyHandler.reply(context);
	}
	
	
	public ReplyConfigHolder getReplyHandler() {
		return replyHandler;
	}

}