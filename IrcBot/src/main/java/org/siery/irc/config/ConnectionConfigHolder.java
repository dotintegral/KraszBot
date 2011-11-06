package org.siery.irc.config;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.siery.irc.config.reader.ConnectionConfigReader;

public class ConnectionConfigHolder {

	private int messagesDelay;
	private int messagesSentTogether;
	
	private boolean verbose;
	private String name;
	private String server;
	private List<String> channels;
	
	public ConnectionConfigHolder() {
		reload();
	}

	public void reload() {
		load();
	}
	
	private void load() {
		try {
			ConnectionConfigReader r = new ConnectionConfigReader();
			
			messagesDelay = r.getMsgDelay();
			messagesSentTogether = r.getMsgSentTogether();
			
			verbose = r.isVerbose();
			name = r.getName();
			server = r.getServer();
			channels = r.getChannels();
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getMessagesDelay() {
		return messagesDelay;
	}
	public int getMessagesSentTogether() {
		return messagesSentTogether;
	}
	public String getName() {
		return name;
	}
	public String getServer() {
		return server;
	}
	public List<String> getChannels() {
		return channels;
	}
	
	public boolean getVerbose() {
		return verbose;
	}
}