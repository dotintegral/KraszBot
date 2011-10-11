package org.siery.irc.config;

public class MainConfigHolder {

	private int messagesDelay;
	private int messagesSentTogether;
	
	public MainConfigHolder() {
		messagesDelay = 1500;
		messagesSentTogether = 5;
	}
	
	public int getMessagesDelay() {
		return messagesDelay;
	}
	public void setMessagesDelay(int messagesDelay) {
		this.messagesDelay = messagesDelay;
	}
	public int getMessagesSentTogether() {
		return messagesSentTogether;
	}
	public void setMessagesSentTogether(int messagesSentTogether) {
		this.messagesSentTogether = messagesSentTogether;
	}
}
