package org.siery.irc.dto;

import org.siery.irc.user.User;

public class Message {
	private String channel;
	private String nick;
	private String login;
	private String hostname;
	private String message;
	
	public Message(String channel, String sender, String login, String hostname, String message) {
		this.channel = channel;
		this.nick = sender;
		this.login = login;
		this.hostname = hostname;
		this.message = message;
	}
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String sender) {
		this.nick = sender;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public User getUser() {
		return new User(nick, login, hostname);
	}
}
