package org.siery.irc.context;

import org.siery.irc.KraszBot;
import org.siery.irc.action.ActionType;
import org.siery.irc.dto.Message;
import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;

public class ActionContext extends GenericContext {

	private String details;
	private boolean channelAction;
	private ActionType actionType;
	
	public ActionContext(KraszBot bot, ChannelUser channelUser, String details, ActionType actionType) {
		super(bot, channelUser);
		this.details = details;
		this.actionType = actionType;
		
		setChannelAction();
	}
	
	public ActionContext(KraszBot bot, User user, String details, ActionType actionType) {
		super(bot, user);
		this.details = details;
		this.actionType = actionType;
	}

	// TODO: Enum for channel, private message or not channel related
	private void setChannelAction() {
		if(hasChannel()) {
			if(channel.startsWith("#")) {
				channelAction = true;
			} else {
				channelAction = false;
			}
		}
	}
	
	public ActionContext(KraszBot bot, Message message) {
		super(bot, message.getUser(), message.getChannel());
		channelAction = true;
		this.actionType = ActionType.CHANNEL_MESSAGE;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isChannelAction() {
		return channelAction;
	}
	
	public boolean isUserAction() {
		return !channelAction;
	}

	public ActionType getActionType() {
		return actionType;
	}
	
	
}
