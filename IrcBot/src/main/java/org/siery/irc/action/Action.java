package org.siery.irc.action;

import org.siery.irc.context.ActionContext;

public interface Action {
	public void execute(ActionContext context);
}
