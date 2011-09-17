package org.siery.irc.action.memo;

import java.util.Date;

import org.siery.irc.user.User;

public class Memo {
	private User reciever;
	private User sender;
	private Date date;
	private String channel;
	private String message;
	private boolean newMemo;
	
	public User getReciever() {
		return reciever;
	}
	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isNewMemo() {
		return newMemo;
	}
	public void setNewMemo(boolean newMemo) {
		this.newMemo = newMemo;
	}
	
}
