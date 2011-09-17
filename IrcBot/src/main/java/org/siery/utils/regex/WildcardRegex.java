package org.siery.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class WildcardRegex {
	
	/**
	 * @source: http://www.rgagnon.com/javadetails/java-0515.html
	 */
	
	public static String wildcardToRegex(String wildcard){
        StringBuffer s = new StringBuffer(wildcard.length());
        s.append('^');
        for (int i = 0, is = wildcard.length(); i < is; i++) {
            char c = wildcard.charAt(i);
            switch(c) {
                case '*':
                    s.append(".*");
                    break;
                case '?':
                    s.append(".");
                    break;
                    // escape special regexp-characters
                case '(': case ')': case '[': case ']': case '$':
                case '^': case '.': case '{': case '}': case '|':
                case '\\':
                    s.append("\\");
                    s.append(c);
                    break;
                default:
                    s.append(c);
                    break;
            }
        }
        s.append('$');
        return(s.toString());
    }
	
	public static boolean matchWildcards(String pattern, String string) {
		String wildcardPattern = WildcardRegex.wildcardToRegex(pattern);

		return RegexMatcher.match(wildcardPattern, string);
	}
}
