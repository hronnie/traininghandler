package com.codeproj.traininghandler.util;

import java.util.Date;

import org.joda.time.DateTime;

public class ThDateUtils {
	public static boolean isDateEqualsWithoutMs(Date date1, Date date2) {
		return date1.equals(date2) || !(Math.abs(date1.getTime() - date2.getTime()) > 3000);
	}

	public static DateTime convertStringDateToDateTime(
			String trainingComplDateAttr) {
		return Constants.HUNGARIAN_DATE_FORMATTER.parseDateTime(trainingComplDateAttr);
	}
}
