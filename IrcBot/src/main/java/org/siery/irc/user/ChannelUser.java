package org.siery.irc.user;

public class ChannelUser {

	private User user;
	private String channel;
	private String server;
	
	public ChannelUser(User user, String channel, String server) {
		this.user = user;
		this.channel = channel;
		this.server = server;
	}
	
	public boolean equals(ChannelUser cu) {
		return server.equals(cu.getServer()) &&
				channel.equals(cu.getChannel()) &&
				user.equals(user);
	}

	public User getUser() {
		return user;
	}

	public String getChannel() {
		return channel;
	}

	public String getServer() {
		return server;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((server == null) ? 0 : server.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelUser other = (ChannelUser) obj;
		if (channel == null) {
			if (other.channel != null)
				return false;
		} else if (!channel.equals(other.channel))
			return false;
		if (server == null) {
			if (other.server != null)
				return false;
		} else if (!server.equals(other.server))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelUser [user=");
		builder.append(user);
		builder.append(", channel=");
		builder.append(channel);
		builder.append(", server=");
		builder.append(server);
		builder.append("]");
		return builder.toString();
	}
}
