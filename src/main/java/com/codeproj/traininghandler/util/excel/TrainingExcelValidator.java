package com.codeproj.traininghandler.util.excel;

import static com.codeproj.traininghandler.util.Constants.*;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class TrainingExcelValidator {

	public static String validateTrainingExcelList(
			List<TrainingExcelDto> trainingAttendendList) {
		
		StringBuilder validationMsg = new StringBuilder("");
		
		if (trainingAttendendList == null || trainingAttendendList.size() == 0) {
			return VALIDATION_EXCEL_EMPTY_LIST;
		}
		
		for (TrainingExcelDto item : trainingAttendendList) {
			appendFieldValidationMsgs(validationMsg, item.getName(), VALIDATION_EXCEL_IMPORT_NAME_EMPTY, VALIDATION_EXCEL_IMPORT_NAME_TOO_LONG, 100);
			appendFieldValidationMsgs(validationMsg, item.getPostCode(), VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY, VALIDATION_EXCEL_IMPORT_POST_CODE_TOO_LONG, 15);
			appendFieldValidationMsgs(validationMsg, item.getAddress(), VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY, VALIDATION_EXCEL_IMPORT_ADDRESS_TOO_LONG, 100);
			appendFieldValidationMsgs(validationMsg, item.getPhoneNo(), VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY, VALIDATION_EXCEL_IMPORT_PHONE_NO_TOO_LONG, 50);
			if (!StringUtils.isEmpty(item.getEmail())) {
				if (!ValidatorBaseUtility.isValidEmailAddress(item.getEmail())) {
					appendValidationMsgWithMsgAndSeparator(validationMsg, VALIDATION_EXCEL_IMPORT_EMAIL_INVALID);
				}
				appendFieldValidationMsgs(validationMsg, item.getEmail(), "", VALIDATION_EXCEL_IMPORT_EMAIL_TOO_LONG, 80);
			}
		}
		
		return cutSplitter(validationMsg);
	}

	public static String validateImportExcelInputParams(String trainingTypeId, String year,
			String month, String day, MultipartFile importFile) {
		
		StringBuilder validationMsg = new StringBuilder("");
		String result = null;
		
		appendValidationMsgIfNotNumeric(trainingTypeId, validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_TRAINING_TYPE_ID);
		
		appendValidationMsgIfNotNumeric(year, validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_YEAR);

		appendValidationMsgIfNotNumeric(month, validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_MONTH);

		appendValidationMsgIfNotNumeric(day, validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_DAY);
		
		if (importFile == null || importFile.isEmpty()) {
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_FILE); 
			addSeparator(validationMsg);
		}
		
		result = cutSplitter(validationMsg);
		if (!"".equals(result)) {
			return result;
		}
		
		Integer yearInt = new Integer(year);
		Integer monthInt = new Integer(month);
		Integer dayInt = new Integer(day);
		
		if (yearInt < 1992) {
			appendValidationMsgWithMsgAndSeparator(validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_YEAR);
		}
		
		if (monthInt < 1 || monthInt > 12) {
			appendValidationMsgWithMsgAndSeparator(validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_MONTH);
		}
		
		if (dayInt < 1 || dayInt > 31) {
			appendValidationMsgWithMsgAndSeparator(validationMsg, VALIDATION_EXCEL_IMPORT_INVALID_DAY);
		}
		
		return cutSplitter(validationMsg);
	}

	private static void appendFieldValidationMsgs(StringBuilder validationMsg,
			String inParam, String emptyMsg, String tooLongMsg, int maxSize) {
		if (inParam == null || "".equals(inParam)) {
			appendValidationMsgWithMsgAndSeparator(validationMsg, emptyMsg);
		}
		if (inParam != null && inParam.length() > maxSize) {
			appendValidationMsgWithMsgAndSeparator(validationMsg, tooLongMsg);
		}
	}

	private static void appendValidationMsgWithMsgAndSeparator(
			StringBuilder validationMsg, String message) {
		validationMsg.append(message); 
		addSeparator(validationMsg);
	}

	private static void appendValidationMsgIfNotNumeric(String year,
			StringBuilder validationMsg, String message) {
		if (!StringUtils.isNumeric(year)) {
			validationMsg.append(message); 
			addSeparator(validationMsg);
		}
	}
	
	private static String cutSplitter(StringBuilder validationMsg) {
		if ("".equals(validationMsg.toString())) {
			return "";
		}
		return validationMsg.toString().substring(0, validationMsg.toString().length() - 2);
	}
	
	private static void addSeparator(StringBuilder validationMsg) {
		validationMsg.append(VALIDATION_EXCEL_SEPARATOR);
	}

}
