package org.siery.irc.command.abstracts;

import org.siery.irc.user.Owner;

public abstract class OwnerCommand extends Command {

	protected boolean isAuthorised() {
		
		if(getContext().getUser() instanceof Owner)
			return true;
			
		return false;
	}

}
