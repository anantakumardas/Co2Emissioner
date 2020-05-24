package com.ananta.co2emissioner.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testIsEmptyString() {
		assertTrue(StringUtil.isEmptyString(""));
	}

	@Test
	public void testIsNotEmptyString() {
		assertTrue(StringUtil.isNotEmptyString("Test"));
	}

	
}