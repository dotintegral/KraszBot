package org.siery.utils.regex;

import static org.junit.Assert.*;

import org.junit.Test;

public class WildcardRegexTest {

	@Test
	public void testWildcardToRegex() {
		String wildCardPattern = "abc*def??x";
		String regexpPattern = "^abc.*def..x$";
		String convertedPattern = WildcardRegex.wildcardToRegex(wildCardPattern);
		boolean comparation = regexpPattern.equals(convertedPattern);
		
		assertTrue(comparation);
	}

	@Test
	public void testMatchWildcards() {
		String pattern = "abc*test?v";
		String matcher = "abc xvx test-v";
		boolean match = WildcardRegex.matchWildcards(pattern, matcher);
		
		assertTrue(match);
	}

}
