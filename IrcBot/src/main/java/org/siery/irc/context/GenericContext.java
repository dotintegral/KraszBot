package org.siery.irc.context;

import java.util.ArrayList;
import java.util.List;

import org.siery.irc.KraszBot;
import org.siery.irc.buffer.MessageBuffer;
import org.siery.irc.command.MoreCommand;
import org.siery.irc.config.ConfigHolder;
import org.siery.irc.config.MainConfigHolder;
import org.siery.irc.reply.ReplyHandler;
import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;

public abstract class GenericContext {
	
	protected User user;
	protected KraszBot bot;
	protected String channel;
	
	public GenericContext(KraszBot bot, User user, String channel) {
		this.user = user;
		this.bot = bot;
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public String getChannel() {
		return channel;
	}
	
	public String getServer() {
		return bot.getServer();
	}
	
	public ChannelUser getChannelUser() {
		return new ChannelUser(getUser(), getChannel(), getServer());
	}
	
	public void sendMessage(String message) {
		bot.sendMessage(channel, message);
	}
	
	public void sendMultipleMessages(List<String> messages) {
		
		MainConfigHolder config = ConfigHolder.getInstance().getMainConfigHolder();
		int limit = config.getMessagesSentTogether();
		int delay = config.getMessagesDelay();
		
		if(messages.size() <= limit) {
			sendAllMessages(messages);
		} else {
			sendOrBufferMessages(messages, limit);
		}
		
	}

	private void sendOrBufferMessages(List<String> messages, int limit) {
		List<String> bufferedMessages = new ArrayList<String>();
		
		for(int i=0; i<messages.size(); i++) {
			String message = messages.get(i);
			if(i < limit) {
				sendMessage(message);
			} else {
				bufferedMessages.add(message);
			}
		}
		
		MessageBuffer.getInstance().addMessages(getChannelUser(), bufferedMessages);
		
		MoreCommand moreCommand = new MoreCommand();
		
		sendMessage("* Jeszcze " + bufferedMessages.size() + " linii. Wpisz " + moreCommand.getUsage());
	}

	private void sendAllMessages(List<String> messages) {
		for(String message : messages) {
			sendMessage(message);
		}
	}

	private void sleep(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 
	public ReplyHandler getReplyHolder() {
		return bot.getReplyHandler();
	}
	
}
