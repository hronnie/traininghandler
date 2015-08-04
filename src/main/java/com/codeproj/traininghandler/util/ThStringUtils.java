package com.codeproj.traininghandler.util;

import org.apache.commons.lang3.StringUtils;

public class ThStringUtils {

	public static String cleanPhoneNumber(String phoneNo) {
		if (StringUtils.isEmpty(phoneNo)) {
			return "";
		}
		return phoneNo.replaceAll("[^0-9]", "");
	}
	
	public static boolean isFakeEmail(String email) {
		if (Constants.EXCEL_TRAINING_MISSING_EMAIL.equals(email)) {
			return true;
		}
		return false;
	}
}
