package org.siery.irc.command.abstracts;


public abstract class UserCommand extends Command {

	protected final boolean isAuthorised() {
		if(getContext().getUser() != null)
			return true;
		return false;
	}
	
	
}
