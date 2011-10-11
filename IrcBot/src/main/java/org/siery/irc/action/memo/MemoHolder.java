package org.siery.irc.action.memo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.siery.irc.user.ChannelUser;
import org.siery.irc.user.User;

public class MemoHolder {

	private Map<ChannelUser, List<Memo>> memoMap;
	
	private static MemoHolder instance;
	
	private MemoHolder() {
		memoMap = new HashMap<ChannelUser, List<Memo>>();
	}	
	
	public static MemoHolder getInstance() {
		if(instance == null)
			instance = new MemoHolder();
		return instance;
	}

	public void addMemo(Memo memo) {
		List<Memo> memos = getMemoListForUser(memo.getReciever());
		memos.add(memo);
	}
	
	private List<Memo> getMemoListForUser(ChannelUser reciever) {
		if(memoMap.containsKey(reciever)) {
			return memoMap.get(reciever);
		} else {
			List<Memo> memos = new ArrayList<Memo>();
			memoMap.put(reciever, memos);
			return memos;
		}
	}

	public int countMemo(ChannelUser channelUser, boolean isNew) {
		int i = 0;
		
		for(Memo memo : getMemoListForUser(channelUser)) {
			memo.setNewMemo(false);
			i++;
		}
		
		return i;
	}
	
	public List<Memo> getMemosFor(ChannelUser channelUser) {
		List<Memo> memos = getMemoListForUser(channelUser);
		clearMemoListFor(channelUser);
		return memos;
	}
	
	private void clearMemoListFor(ChannelUser channelUser) {
		List<Memo> emptyMemoList = new ArrayList<Memo>();
		memoMap.put(channelUser, emptyMemoList);
	}
}
