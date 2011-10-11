package org.siery.irc.command;

import java.util.ArrayList;
import java.util.List;

import org.siery.irc.command.abstracts.ArgumentCommand;
import org.siery.irc.command.abstracts.UserCommand;
import org.siery.irc.config.ConfigHolder;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class WolframAlphaCommand extends UserCommand implements ArgumentCommand {

	private String arguments;

	public void setAgruments(List<String> args) {
		arguments = "";
		for (String argument : args)
			arguments += argument;
	}
	
	private void l(String s) {
		System.out.print(s);
	}

	public String getCommand() {
		return "wa";
	}

	public String getUsage() {
		return getCommandPrefix() + getCommand() + " zapytanie";
	}

	public String getInfo() {
		return "Pobiera dane z serwisu WolframAlpha";
	}

	protected void onSuccess() {
		//String message = getContext().getUser().getNick() + ": ";

		l("wykonujemy polecenie");
		
		List<String> messages = test();
		getContext().sendMultipleMessages(messages);
	}

	protected void onFailiture() {

	}

	protected List<String> test() {

		
		
		String appId = ConfigHolder.getInstance().getCommandConfigHolder().getWolframAlphaAppId();

		l("appid " + appId);
		
		WAEngine engine = new WAEngine();
		engine.setAppID(appId);
		engine.addFormat("plaintext");

		WAQuery query = engine.createQuery();

		query.setInput(arguments);

		List<String> responses = new ArrayList<String>();

		l("jak dotad dobrze");
		
		try {

			WAQueryResult queryResult = engine.performQuery(query);

			if (queryResult.isError()) {
				System.out.println("Query error");
				System.out.println("  error code: " + queryResult.getErrorCode());
				System.out.println("  error message: " + queryResult.getErrorMessage());
			} else if (!queryResult.isSuccess()) {
				System.out.println("Query was not understood; no results available.");
			} else {
				System.out.println("Successful query. Pods follow:\n");
				for (WAPod pod : queryResult.getPods()) {
					if (!pod.isError()) {
						responses.add(pod.getTitle());
						System.out.print(pod.getTitle());
						for (WASubpod subpod : pod.getSubpods()) {
							for (Object element : subpod.getContents()) {
								if (element instanceof WAPlainText) {
									responses.add("| " + ((WAPlainText) element).getText());
									System.out.print("| " + ((WAPlainText) element).getText());
								}
							}
						}
					}
				}
			}
		} catch (WAException e) {
			e.printStackTrace();
		}
		
		l("zwracamy");
		return responses;
	}

}
