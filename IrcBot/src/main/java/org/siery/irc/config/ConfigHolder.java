package org.siery.irc.config;

public class ConfigHolder {

	private static ConfigHolder instance;
	
	private CommandConfigHolder commandConfigHolder;
	private ConnectionConfigHolder connectionConfigHolder;
	
	private ConfigHolder() {
		commandConfigHolder = new CommandConfigHolder();
		connectionConfigHolder = new ConnectionConfigHolder();
	}
	
	public static ConfigHolder getInstance() {
		if(instance == null) {
			instance = new ConfigHolder();
		}
		return instance;
	}

	public CommandConfigHolder getCommandConfigHolder() {
		return commandConfigHolder;
	}

	public ConnectionConfigHolder getConnectionConfigHolder() {
		return connectionConfigHolder;
	}
	
	public void reload() {
		commandConfigHolder.reload();
		connectionConfigHolder.reload();
	}
}
