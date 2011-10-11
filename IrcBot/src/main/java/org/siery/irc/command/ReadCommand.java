package org.siery.irc.command;

import java.util.ArrayList;
import java.util.List;

import org.siery.irc.action.memo.Memo;
import org.siery.irc.action.memo.MemoHolder;
import org.siery.irc.command.abstracts.UserCommand;
import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;

public class ReadCommand extends UserCommand {

	@Override
	public String getCommand() {
		return "read";
	}

	@Override
	public String getUsage() {
		return getCommandPrefix() + getCommand();
	}

	@Override
	public String getInfo() {
		return "Odczytuje zapisane wiadomości dla danego użytkownika";
	}

	@Override
	protected void onSuccess() {
		ChannelUser channelUser = getContext().getChannelUser();
		User user = channelUser.getUser();
		List<Memo> memos = MemoHolder.getInstance().getMemosFor(channelUser);
		
		if(memos.size() == 0 ) {
			getContext().sendMessage(user.getNick() + ", brak wiadomości dla Ciebie");
		} else {
			List<String> messages = new ArrayList<String>();
			
			for(Memo m : memos)
				messages.add(m.toString());
			
			getContext().sendMultipleMessages(messages);
		}
	}

	@Override
	protected void onFailiture() {
	}

}
