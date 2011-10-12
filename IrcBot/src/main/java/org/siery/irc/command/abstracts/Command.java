package org.siery.irc.command.abstracts;

import org.siery.irc.context.CommandContext;


public abstract class Command {

	private CommandContext context;
	
	public CommandContext getContext() {
		return context;
	}

	public final void execute(CommandContext context) {
		this.context = context;
		
		if(isAuthorised())
			onSuccess();
		else
			onFailiture();
	}
	
	public static final String getCommandPrefix() {
		return ";";
	}
	
	public abstract String getCommand();
	public abstract String getUsage();
	public abstract String getInfo();
	
	protected abstract boolean isAuthorised();
	protected abstract void onSuccess();
	protected abstract void onFailiture();
}
