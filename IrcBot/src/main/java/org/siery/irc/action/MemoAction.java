package org.siery.irc.action;

import java.util.Date;

import org.siery.irc.action.memo.Memo;
import org.siery.irc.action.memo.MemoHolder;
import org.siery.irc.context.ActionContext;
import org.siery.irc.user.User;

public class MemoAction implements Action {

	private MemoHolder memoHolder;
	
	public void execute(ActionContext context) {
		if(context.isChannelAction()) {
			prepareMemoHolder();
			
			User user = context.getUser();
			String channel = context.getChannel();
			
			int newMemos = memoHolder.countMemo(user, channel, true);
			if(newMemos > 0)
				sendNewMemosInfo(context, newMemos);
		}
	}
	
	private void prepareMemoHolder() {
		if(memoHolder == null) {
			memoHolder = new MemoHolder();
			
			Memo memo = new Memo();
			memo.setChannel("#kucbot");
			memo.setDate(new Date());
			memo.setMessage("bla bla");
			memo.setReciever(new User("artur", "*", "*"));
			memo.setSender(null);
			memo.setNewMemo(true);
			
			memoHolder.addMemo(memo);
		}
	}

	private void sendNewMemosInfo(ActionContext context, int newMemos) {
		String message = context.getUser().getNick() + ", masz " + newMemos + " nowych wiadomości.";
		context.sendMessage(message);
	}

}
