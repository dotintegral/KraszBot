package org.siery.irc.command;

import java.util.List;

import org.siery.irc.NickHunter;
import org.siery.irc.command.abstracts.ArgumentCommand;
import org.siery.irc.command.abstracts.UserCommand;
import org.siery.irc.command.utils.NickHunterThread;

public class NickHuntCommand extends UserCommand implements ArgumentCommand {
	
	private String targetNick;

	public String getCommand() {
		return "nickhunt";
	}

	public String getUsage() {
		return getCommandPrefix() + "nickhunt nick";
	}

	public String getInfo() {
		return "Uruchamia bota polującego na podany nick. Nick zostanie zwolniony po wydaniu komendy: dawaj nick";
	}

	protected void onSuccess() {
		String hostname = getContext().getHostname();
		String channel = getContext().getChannel();
		String hunterNick = getContext().getUser().getNick();
		
		System.out.println("New hunter starts, targetNick" + targetNick);
		
		NickHunter nickHunter = new NickHunter(getContext());
		nickHunter.setHuntDetails(targetNick, hunterNick);
		
		NickHunterThread nht = new NickHunterThread(nickHunter);
		nht.start();
	}

	protected void onFailiture() {
		getContext().sendMessage("fail");
	}

	@Override
	public void setAgruments(List<String> args) {
		targetNick = args.get(0);
	}
}
