package org.siery.irc.command.abstracts;

import java.util.List;

public interface ArgumentCommand {
	public void setAgruments(List<String> args);
	public String getUsage();
}
