package org.siery.irc.action;

import java.util.List;

import org.siery.irc.context.ActionContext;

public class ActionHandler {

	private List<Action> actions;
	
	public ActionHandler() {
		actions = ActionFactory.getActions();
	}
	
	public void executeAction(ActionContext context) {
		for(Action a : actions) {
			if(a.toExecute(context))
				a.execute(context);
		}
	}
}
