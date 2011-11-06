package org.siery.irc.config.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ConnectionConfigReader {

	private static String configFile = "config/conf.yml";
	
	private int msgDelay;
	private int msgSentTogether;
	private boolean verbose;
	
	private String name;
	private String server;
	private List<String> channels;
	
	public ConnectionConfigReader() throws FileNotFoundException {
		InputStream input = new FileInputStream(new File(configFile));
		
		Yaml yaml = new Yaml();
		Map<String, Object> data = (Map<String, Object>) yaml.load(input);
		
		msgDelay = (Integer) data.get("msgDelay");
		msgSentTogether = (Integer) data.get("msgSentTogether");
		verbose = (Boolean) data.get("verbose");
		
		name = (String) data.get("name");
		server = (String) data.get("server");
		
		channels = (ArrayList<String>) data.get("channels");
	}

	public static String getConfigFile() {
		return configFile;
	}
	public int getMsgDelay() {
		return msgDelay;
	}
	public int getMsgSentTogether() {
		return msgSentTogether;
	}
	public boolean isVerbose() {
		return verbose;
	}
	public String getName() {
		return name;
	}
	public String getServer() {
		return server;
	}
	public List<String> getChannels() {
		return channels;
	}
}
