package org.siery.irc.user;

import org.siery.utils.regex.WildcardRegex;

public class User {

	private String nick;
	private String login;
	private String hostname;
	private String auth;
	private boolean authed;
	
	public User(String nick, String login, String hostname) {
		this.nick = nick;
		this.login = login;
		this.hostname = hostname;
	}
	
	public User(String nick, String login, String hostname, String auth) {
		this.nick = nick;
		this.login = login;
		this.hostname = hostname;
		this.auth = auth;
	}

	public User(User user) {
		this.nick = user.nick;
		this.login = user.login;
		this.hostname = user.hostname;
	}

	public String getNick() {
		return nick;
	}

	public String getLogin() {
		return login;
	}

	public String getHostname() {
		return hostname;
	}
	
	public String getAuth() {
		return auth;
	}
	
	public boolean isAuthed() {
		return authed;
	}

	public boolean matches(User u) {
		if(!matchNick(u)) 
			return false;
		if(!matchLogin(u)) 
			return false;
		if(!matchHostname(u)) 
			return false;
		
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		return matches(user) || user.matches(this);
	}
	
	@Override
	public int hashCode() {
		return 42;
	}

	private boolean matchNick(User u) {;
		boolean match = WildcardRegex.matchWildcards(getNick(), u.getNick());
		return match;
	}
	
	private boolean matchLogin(User u) {
		boolean match = WildcardRegex.matchWildcards(getLogin(), u.getLogin());
		return match;
	}
	
	private boolean matchHostname(User u) {
		boolean match = WildcardRegex.matchWildcards(getHostname(), u.getHostname());
		return match;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [nick=");
		builder.append(nick);
		builder.append(", login=");
		builder.append(login);
		builder.append(", hostname=");
		builder.append(hostname);
		builder.append(", auth=");
		builder.append(auth);
		builder.append(", authed=");
		builder.append(authed);
		builder.append("]");
		return builder.toString();
	}
}
