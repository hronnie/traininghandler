package com.codeproj.traininghandler.util;

import org.apache.commons.lang3.StringUtils;

public class ThStringUtils {

	public static String cleanPhoneNumber(String phoneNo) {
		if (StringUtils.isEmpty(phoneNo)) {
			return "";
		}
		return phoneNo.replaceAll("[^0-9]", "");
	}
	
	public static boolean isEmailPhoneEmail(String email) {
		String emailHead = email.substring(0, email.indexOf("@"));
		return StringUtils.isNumeric(emailHead);
	}
}
