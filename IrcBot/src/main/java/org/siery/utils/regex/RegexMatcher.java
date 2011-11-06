package org.siery.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

	public static boolean match(String pattern, String string) {
		//Pattern patternObj = Pattern.compile(pattern);
		//Matcher matcherObj = patternObj.matcher(string);
		
		//return matcherObj.matches();
		
		return string.matches(pattern);
	}
}
