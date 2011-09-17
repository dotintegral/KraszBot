package org.siery.irc.context;

import org.siery.irc.KraszBot;
import org.siery.irc.dto.Message;
import org.siery.irc.user.User;

public class ActionContext extends GenericContext {

	private String action;
	private boolean channelAction;
	
	public ActionContext(KraszBot bot, User user, String channel, String action) {
		super(bot, user, channel);
		this.action = action;
		
		if(channel.startsWith("#")) {
			channelAction = true;
		} else {
			channelAction = false;
		}
	}
	
	public ActionContext(KraszBot bot, Message message) {
		super(bot, message.getUser(), message.getChannel());
		channelAction = true;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isChannelAction() {
		return channelAction;
	}
	
	public boolean isUserAction() {
		return !channelAction;
	}
}
