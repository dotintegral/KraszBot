package org.siery.irc.context;

import org.siery.irc.KraszBot;
import org.siery.irc.dto.Message;
import org.siery.irc.user.User;

public class ReplyContext extends GenericContext {

	private String messageString;
	
	public ReplyContext(KraszBot bot, Message message) {
		super(bot, message.getUser(), message.getChannel());
		messageString = message.getMessage();
	}
	
	public String getMessage() {
		return messageString;
	}

}
