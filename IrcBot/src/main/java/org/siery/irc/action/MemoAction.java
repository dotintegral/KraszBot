package org.siery.irc.action;

import java.util.Date;

import org.siery.irc.action.memo.Memo;
import org.siery.irc.action.memo.MemoHolder;
import org.siery.irc.context.ActionContext;
import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;

public class MemoAction implements Action {

	private MemoHolder memoHolder = MemoHolder.getInstance();
	
	public boolean toExecute(ActionContext context) {
		if(context.hasChannel() && 
				context.isChannelAction() &&
				context.getActionType() != ActionType.PART &&
				context.getActionType() != ActionType.QUIT)
			return true;
		else
			return false;
	}
	
	
	public void execute(ActionContext context) {
		if(context.isChannelAction()) {	
			ChannelUser channelUser = context.getChannelUser();
			int newMemos = memoHolder.countMemo(channelUser, true);
			
			if(newMemos > 0)
				sendNewMemosInfo(context, newMemos);
		}
	}

	private void sendNewMemosInfo(ActionContext context, int newMemos) {
		String message = context.getUser().getNick() + ", masz " + newMemos + " nowych wiadomości. Wpisz ;read aby przeczytać.";
		context.sendMessage(message);
	}

}
