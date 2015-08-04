package com.codeproj.traininghandler.util.excel;

import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_IMPORT_INVALID_DAY;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_IMPORT_INVALID_FILE;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_IMPORT_INVALID_MONTH;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_IMPORT_INVALID_TRAINING_TYPE_ID;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_IMPORT_INVALID_YEAR;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_EXCEL_SEPARATOR;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.address.AddressServiceValidator;
import com.codeproj.traininghandler.rest.user.UserServiceValidator;

public class TrainingExcelValidator {

	public static void validateTrainingExcelList(
			List<TrainingExcelDto> trainingAttendendList) throws ValidationException {
		
		for (TrainingExcelDto item : trainingAttendendList) {
			UserServiceValidator.createUserFromExcel(item);
			AddressServiceValidator.createAddressFromExcel(item);
		}
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
