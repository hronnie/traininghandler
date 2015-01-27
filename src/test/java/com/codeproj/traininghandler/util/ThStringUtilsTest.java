package com.codeproj.traininghandler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ThStringUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCleanPhoneNumber() {
		assertEquals("The input string wasn't cleaned correctly", "", ThStringUtils.cleanPhoneNumber(""));
		assertEquals("The input string wasn't cleaned correctly", "", ThStringUtils.cleanPhoneNumber(null));
		assertEquals("The input string wasn't cleaned correctly", "001444", ThStringUtils.cleanPhoneNumber(" -001-/dfdf444 "));
	}

}
