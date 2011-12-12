package org.siery.irc.action;

import java.util.ArrayList;
import java.util.List;

public class ActionFactory {
	public static List<Action> getActions() {
		List<Action> actions = new ArrayList<Action>();
		
		actions.add(new MemoAction());
		actions.add(new ActivityAction());
		
		return actions;
	}
}
