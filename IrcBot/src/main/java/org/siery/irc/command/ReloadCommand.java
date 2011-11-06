package org.siery.irc.command;

import org.siery.irc.command.abstracts.OwnerCommand;
import org.siery.irc.config.reader.ReplyConfigReader;

public class ReloadCommand extends OwnerCommand {

	@Override
	public String getCommand() {
		return "reload";
	}

	@Override
	public String getUsage() {
		return null;
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	protected void onSuccess() {
		getContext().getReplyHolder().reload();
		getContext().sendMessage( getContext().getUser().getNick() + ", ok, config przeładowany");
	}

	@Override
	protected void onFailiture() {
		getContext().sendMessage( getContext().getUser().getNick() + ", nie wydaje mi się");
	}

}
