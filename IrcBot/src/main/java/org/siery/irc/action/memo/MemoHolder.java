package org.siery.irc.action.memo;

import java.util.ArrayList;
import java.util.List;

import org.siery.irc.user.User;

public class MemoHolder {

	private List<Memo> memoList;
	
	public MemoHolder() {
		memoList = new ArrayList<Memo>();
	}
	
	public void addMemo(Memo memo) {
		memoList.add(memo);
	}
	
	public int countMemo(User user, String channel, boolean isNew) {
		int i = 0;
		
		for(Memo memo : memoList) {
			if(memoMatches(user, channel, memo)) {
				i = countConsideringNewAndOld(isNew, i, memo);
			}
		}
		
		return i;
	}

	private boolean memoMatches(User user, String channel, Memo memo) {
		return memo.getReciever().matches(user) && channel.equals(memo.getChannel());
	}

	private int countConsideringNewAndOld(boolean isNew, int i, Memo memo) {
		if(isNew) {
			if(memo.isNewMemo()) {
				i++;
				memo.setNewMemo(false);
			}
		} else {
			i++;
		}
		
		return i;
	}
	
	public List<Memo> getMemosFor(User user) {
		List<Memo> memos = new ArrayList<Memo>();
		
		for(Memo memo : memoList) {
			if(user.matches(memo.getReciever())) {
				memos.add(memo);
				memoList.remove(memo);
			}
		}
		
		return memos;
	}
}
