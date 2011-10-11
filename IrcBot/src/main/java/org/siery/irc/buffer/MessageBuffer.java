package org.siery.irc.buffer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.siery.irc.user.ChannelUser;

public class MessageBuffer {

	private Map<ChannelUser, List<String>> buffer;
	
	private static MessageBuffer instance;
	
	private MessageBuffer () {
		buffer = new HashMap<ChannelUser, List<String>>();
	}
	
	public static MessageBuffer getInstance() {
		if(instance == null)
			instance = new MessageBuffer();
		return instance;
	}
	
	public void addMessages(ChannelUser user, List<String> messages) {
		buffer.put(user, messages);
	}
	
	public List<String> getBufferedMessages(ChannelUser u) {
		return buffer.get(u);
	}

}
