package com.codeproj.traininghandler.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class ThDateUtilsTest {

	public DateTime TRAINING_COMPL_DATE = new DateTime(2015, 2, 5, 0, 0);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsDateEqualsWithoutMs() {
		assertTrue("Should be equals", ThDateUtils.isDateEqualsWithoutMs(new Date(500000L), new Date(499000L)));
		assertFalse("Should be 2 different date", ThDateUtils.isDateEqualsWithoutMs(new Date(500000L), new Date(100000L)));
	}
	
	@Test
	public void testConvertStringDateToDateTime() {
		String inputDateString = "2015-01-01";
		DateTime inputDate = new DateTime(2015, 1, 1, 0, 0);
		assertEquals("The result date is not correct", ThDateUtils.convertStringDateToDateTime(inputDateString), inputDate);
	}

}
