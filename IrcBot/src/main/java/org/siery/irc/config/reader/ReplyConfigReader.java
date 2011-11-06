package org.siery.irc.config.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.siery.irc.dto.Reply;
import org.yaml.snakeyaml.Yaml;

public class ReplyConfigReader {
	
	private static String repliesFileLocation = "config/replies.yml";

	public static ArrayList<Reply> getReplies() {
		ArrayList<Reply> replies = new ArrayList<Reply>();
		
		try {
			replies = loadRepliesFromConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return replies;
	}

	private static ArrayList<Reply> loadRepliesFromConfig() throws FileNotFoundException {
		InputStream input;
		input = new FileInputStream(new File(repliesFileLocation));
		ArrayList<Reply> replies = new ArrayList<Reply>();
		
		Yaml yaml = new Yaml();
		Object data = yaml.load(input);
		List<Map<String, String>> list = (List<Map<String, String>>) data;
		
		for(Map map : list) {
			String matchString = (String) map.get("match");
			String replyString = (String) map.get("reply");
			Reply reply = new Reply(matchString, replyString);
			replies.add(reply);
		}
		
		return replies;
	}
}
