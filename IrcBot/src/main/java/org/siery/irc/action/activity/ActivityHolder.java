package org.siery.irc.action.activity;

public class ActivityHolder {

	private static ActivityHolder instance;
	
	private ActivityHolder() {
		
	}	
	
	public static ActivityHolder getInstance() {
		if(instance == null)
			instance = new ActivityHolder();
		return instance;
	}
	
	public void setJoin(String nick, String channel) {
		
	}
	
	public void setActivity(String nick, String channel) {
		
	}

	public void setPart(String nick, String channel) {
		
	}
	
	public void setQuit(String nick) {
		
	}
	
}
