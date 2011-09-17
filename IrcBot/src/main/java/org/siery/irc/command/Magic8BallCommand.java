package org.siery.irc.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.siery.irc.command.abstracts.ArgumentCommand;
import org.siery.irc.command.abstracts.UserCommand;

public class Magic8BallCommand extends UserCommand implements ArgumentCommand {

	private String question;
	private ArrayList<String> answers;
	
	public void setAgruments(List<String> args) {
		question = "";
		for(String argument : args)
			question += argument;
	}

	public String getCommand() {
		return "8ball";
	}

	public String getUsage() {
		return getCommandPrefix()+getCommand()+" pytanie";
	}

	public String getInfo() {
		return "Odpowiada na pytanie, niczym magiczna kula 8";
	}

	protected void onSuccess() {
		String message = getContext().getUser().getNick() + ": ";
		
		initAnswers();
		message += getReply();
		
		getContext().sendMessage(message);
	}

	protected void onFailiture() {
		
	}
	
	private String getReply() {
		
		Random r = new Random();
		int rand = r.nextInt(answers.size());
		return answers.get(rand);
	}

	private void initAnswers() {
		if(answers == null) {
			answers = new ArrayList<String>();
			
			answers.add("It is certain");
			answers.add("It is decidedly so");
			answers.add("Without a doubt");
			answers.add("Yes – definitely");
			answers.add("You may rely on it");
			answers.add("As I see it, yes");
			answers.add("Most likely");
			answers.add("Outlook good");
			answers.add("Signs point to yes");
			answers.add("Yes");
			
			answers.add("Reply hazy, try again");
			answers.add("Ask again later");
			answers.add("Better not tell you now");
			answers.add("Cannot predict now");
			answers.add("Concentrate and ask again");
			answers.add("Don't count on it");
			answers.add("My reply is no");
			answers.add("My sources say no");
			answers.add("Outlook not so good");
			answers.add("Very doubtful");
		}
	}
}
