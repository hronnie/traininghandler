package com.codeproj.traininghandler.util;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.validator.EmailValidator;
import org.joda.time.DateTime;

public class ValidatorBaseUtility {
	public static void mandatoryParameter(String name, Object object)
			throws ValidationException {
		if (object == null) {
			throw new ValidationException("Mandatory parameter " + name
					+ " is null");
		} else if (object instanceof String) {
			if (((String) object).trim().isEmpty()) {
				throw new ValidationException("Mandatory parameter " + name
						+ " is empty");
			}
		}
	}

	public static void validateStringLength(String name, String strAttr,
			int maxLength) throws ValidationException {
		if (strAttr.length() > maxLength) {
			throw new ValidationException("Parameter " + name
					+ " is too long! Maximum length is " + maxLength);
		}
	}

	public static String stripXSS(String value) {
		if (value != null) {

			// Avoid null characters
			value = value.replaceAll("", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:",
					Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
		}
		return value;
	}
	
	public static void entityIdValidator(Long id) throws ValidationException {
		if (id == null || id < 1) {
			throw new ValidationException("Id parameter: " + id + " is not valid. Cannot be null or less then 1");
		}
	}
	
	public static void emailValidator(String email) throws ValidationException {
		if (!isValidEmailAddress(email)) {
			throw new ValidationException("Email parameter: " + email + " is not valid.");
		}
	}
	
	public static void isDateLater(Date date, int year) throws ValidationException {
		DateTime curDate = new DateTime();
		curDate = curDate.minusYears(year);
		if (curDate.isBefore(date.getTime())) {
			throw new ValidationException("The given date: " + date + " must be older then " + year + " years");
		}
	}
	
	public static void isDateInTheFuture(Date date) throws ValidationException {
		DateTime curDate = new DateTime();
		if (curDate.isBefore(date.getTime())) {
			throw new ValidationException("The given date: " + date + " must be in the past.");
		}
	}
	
	public static void completedUserTrainingValidator(
			List<CompletedUserTrainingDto> completedUserTrainingList) throws ValidationException {
		for (CompletedUserTrainingDto compTr : completedUserTrainingList) {
			entityIdValidator(compTr.getTrainingTypeId());
			mandatoryParameter("Completed user training date", compTr.getCompletedDate());
		}
	}
	
	private static boolean isValidEmailAddress(String email) {
		EmailValidator emailValidator= EmailValidator.getInstance();
		return emailValidator.isValid(email);
	}
}
