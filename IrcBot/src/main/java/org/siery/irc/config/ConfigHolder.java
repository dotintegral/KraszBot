package org.siery.irc.config;

public class ConfigHolder {

	private static ConfigHolder instance;
	
	private CommandConfigHolder commandConfigHolder;
	private MainConfigHolder mainConfigHolder;
	
	private ConfigHolder() {
		commandConfigHolder = new CommandConfigHolder();
		mainConfigHolder = new MainConfigHolder();
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

	public MainConfigHolder getMainConfigHolder() {
		return mainConfigHolder;
	}
}
