package org.siery.irc.dto;

public class Reply {
	private String regexp;
	private String reply;
	
	public Reply(String regexp, String reply) {
		this.regexp = regexp;
		this.reply = reply;
	}

	public String getRegexp() {
		return regexp;
	}

	public String getReply() {
		return reply;
	}
	
}
