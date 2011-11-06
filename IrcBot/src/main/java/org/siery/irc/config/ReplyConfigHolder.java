package org.siery.irc.config;

import java.util.ArrayList;

import org.siery.irc.config.reader.ReplyConfigReader;
import org.siery.irc.context.ReplyContext;
import org.siery.irc.dto.Reply;
import org.siery.utils.regex.RegexMatcher;

public class ReplyConfigHolder {
	private ArrayList<Reply> replies;
	
	public ReplyConfigHolder() {
		replies = ReplyConfigReader.getReplies();
	}
	
	public void reload() {
		replies = ReplyConfigReader.getReplies();
	}
	
	public void reply(ReplyContext context) {
		for(Reply reply : replies) {
			if(RegexMatcher.match(reply.getRegexp(), context.getMessage())) {
				sendReply(reply, context);
				break;
			}
		}
	}
	
	private void sendReply(Reply reply, ReplyContext context) {
		context.sendMessage(reply.getReply());
	}
}
