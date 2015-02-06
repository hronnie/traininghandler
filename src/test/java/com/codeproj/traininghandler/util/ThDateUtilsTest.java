package com.codeproj.traininghandler.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ThDateUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsDateEqualsWithoutMs() {
		assertTrue("Should be equals", ThDateUtils.isDateEqualsWithoutMs(new Date(500000L), new Date(499000L)));
		assertFalse("Should be 2 different date", ThDateUtils.isDateEqualsWithoutMs(new Date(500000L), new Date(100000L)));
	}

}
