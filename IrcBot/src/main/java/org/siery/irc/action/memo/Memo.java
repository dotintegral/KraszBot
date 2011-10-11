package org.siery.irc.action.memo;

import java.util.Date;

import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;

// TODO: Use ChannelUser class
public class Memo {
	private ChannelUser reciever;
	private ChannelUser sender;
	private Date date;
	private String message;
	private boolean newMemo;
	
	public ChannelUser getReciever() {
		return reciever;
	}
	public void setReciever(ChannelUser reciever) {
		this.reciever = reciever;
	}
	public ChannelUser getSender() {
		return sender;
	}
	public void setSender(ChannelUser sender) {
		this.sender = sender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
	public String toString() {
		String string;
		string = date.toString();
		string += " <" + sender.getUser().getNick()+">: ";
		string += message;
		return string;
	}
	
}
