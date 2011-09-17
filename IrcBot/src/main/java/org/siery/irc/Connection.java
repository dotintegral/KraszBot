package org.siery.irc;

import java.util.List;

public class Connection {

	private String name;
	private String hostname;
	private List<String> channels;
	private List<String> connectionCommands;
	
	public Connection(String name, String hostname) {
		this.name = name;
		this.hostname = hostname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public List<String> getChannels() {
		return channels;
	}
	public void setChannels(List<String> channels) {
		this.channels = channels;
	}
	public List<String> getConnectionCommands() {
		return connectionCommands;
	}
	public void setConnectionCommands(List<String> connectionCommands) {
		this.connectionCommands = connectionCommands;
	}
	
	
}
