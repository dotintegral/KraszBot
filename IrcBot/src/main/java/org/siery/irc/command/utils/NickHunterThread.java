package org.siery.irc.command.utils;

import org.siery.irc.NickHunter;

public class NickHunterThread extends Thread {
	
	private int sleepTime = 15000;
	NickHunter nickHunter;
	
	public NickHunterThread(NickHunter nickHunter) {
		this.nickHunter = nickHunter;
	}
	
	public void run() {
		System.out.println("Uruchamiam wątek nickhuntera");
		nickHunter.hunt();
		System.out.println("Ok, chyba się uruchomił");
		
		while(true) {
			try {
				sleep(sleepTime);
				nickHunter.changeNick();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
