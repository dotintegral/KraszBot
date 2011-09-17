package org.siery.irc.context;

import org.siery.irc.KraszBot;
import org.siery.irc.reply.ReplyHandler;
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
	
	public String getHostname() {
		return bot.getServer();
	}
	
	public void sendMessage(String message) {
		bot.sendMessage(channel, message);
	}
	
	public ReplyHandler getReplyHolder() {
		return bot.getReplyHandler();
	}
	
}
