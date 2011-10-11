package org.siery.irc.command;

import java.util.List;

import org.siery.irc.buffer.MessageBuffer;
import org.siery.irc.command.abstracts.UserCommand;
import org.siery.irc.user.ChannelUser;

public class MoreCommand extends UserCommand {

	@Override
	public String getCommand() {
		return "more";
	}

	@Override
	public String getUsage() {
		return getCommandPrefix() + getCommand();
	}

	@Override
	public String getInfo() {
		return "Kontynuuje wyświetlanie długiej treści";
	}

	@Override
	protected void onSuccess() {
		MessageBuffer messageBuffer = MessageBuffer.getInstance();
		ChannelUser channelUser = getContext().getChannelUser();
		
		List<String> messages = messageBuffer.getBufferedMessages(channelUser);
		
		if(messages != null) {
			getContext().sendMultipleMessages(messages);
		} else {
			String message = getContext().getUser().getNick() + ", nie mam nic do wyświetlenia";
			getContext().sendMessage(message);
		}
	}

	@Override
	protected void onFailiture() {
		
	}


}
