package org.siery.utils.regex;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexMatcherTest {

	@Test
	public void testMatch() {
		String pattern = "^[a-z]{1,}\\s\\d{4}$";
		String matcher = "abc 1234";
		
		assertTrue( RegexMatcher.match(pattern, matcher) );
	}

}
