package org.siery.irc.command;

import org.siery.irc.command.abstracts.OwnerCommand;
import org.siery.irc.user.Owner;
import org.siery.irc.user.User;

public class TestCommand extends OwnerCommand {

	public String getCommand() {
		return "test";
	}

	public String getUsage() {
		return null;
	}

	public String getInfo() {
		return null;
	}

	protected void onSuccess() {
		getContext().sendMessage( getContext().getUser().getNick() + ", rządzisz!");
	}

	protected void onFailiture() {
		getContext().sendMessage( getContext().getUser().getNick() + ", goń kuca frajerze");
	}
}
