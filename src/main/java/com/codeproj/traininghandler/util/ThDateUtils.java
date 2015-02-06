package com.codeproj.traininghandler.util;

import java.util.Date;

public class ThDateUtils {
	public static boolean isDateEqualsWithoutMs(Date date1, Date date2) {
		return date1.equals(date2) || !(Math.abs(date1.getTime() - date2.getTime()) > 3000);
	}
}
