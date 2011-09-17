package org.siery.irc;

import java.io.IOException;
import java.util.Random;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;
import org.siery.irc.context.CommandContext;

public class NickHunter extends PircBot{
	private CommandContext context;
	
	private String targetNick;
	private String hunterNick;
	private String botNickBase;
	private boolean success;
	private boolean hunting;
	
	public NickHunter(CommandContext context) {
		this.botNickBase = "nickHunter";
		this.success = false;
		this.hunting = false;
		this.context = context;
	}
	
	public void setHuntDetails(String targetNick, String hunterNick) {
		this.targetNick = targetNick;
		this.hunterNick = hunterNick;
	}
	
	public void hunt() {
		try {
			tryConnectWithNewName();
			hunting = true;
		} catch (NickAlreadyInUseException e) {
			hunt();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	private void tryConnectWithNewName() throws IOException, IrcException, NickAlreadyInUseException {
		Random r = new Random();
		this.setVerbose(true);
		this.setName(botNickBase + r.nextInt(1000));
		this.connect(context.getHostname());
		this.joinChannel(context.getChannel());
		
		this.sendMessage(context.getChannel(), "Hunting for " + targetNick);
	}
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String chanelMessage) {
		if(success == true) {
			if(sender.equals(hunterNick) && chanelMessage.equals("dawaj nick")) {
				this.disconnect();
			}
		}
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void changeNick() {
		if(hunting == true) {
			System.out.println("zmieniam nick...");
			this.changeNick(targetNick);
			if(getNick().equals(targetNick)) {
				hunting = false;
				success = true;
			}
		}
	}
}
