package org.siery.irc.reply;

import java.util.ArrayList;

import org.siery.irc.context.ReplyContext;
import org.siery.irc.dto.Reply;
import org.siery.utils.regex.RegexMatcher;

public class ReplyHandler {
	private ArrayList<Reply> replies;
	
	public ReplyHandler() {
		replies = ReplyFactory.getReplies();
	}
	
	public void reload() {
		replies = ReplyFactory.getReplies();
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
