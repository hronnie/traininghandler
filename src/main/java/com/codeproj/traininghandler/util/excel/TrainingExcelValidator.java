package com.codeproj.traininghandler.util.excel;

import static com.codeproj.traininghandler.util.Constants.*;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.codeproj.traininghandler.dto.TrainingExcelDto;

public class TrainingExcelValidator {

	// return "" if no message
	public static String validateTrainingExcelList(
			List<TrainingExcelDto> trainingAttendendList) {
		// TODO implement method with tests
		return null;
	}

	public static String validateImportExcelInputParams(String year,
			String month, String day, MultipartFile importFile) {
		
		StringBuilder validationMsg = new StringBuilder("");
		String result = null;
		
		if (!StringUtils.isNumeric(year)) {
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_YEAR); 
			addSeparator(validationMsg);
		}
		if (!StringUtils.isNumeric(month)) {
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_MONTH); 
			addSeparator(validationMsg);
		}
		if (!StringUtils.isNumeric(day)) {
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_DAY); 
			addSeparator(validationMsg);
		}
		if (importFile == null) {
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
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_YEAR); 
			addSeparator(validationMsg);
		}
		
		if (monthInt < 1 || monthInt > 12) {
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_MONTH); 
			addSeparator(validationMsg);
		}
		
		if (dayInt < 1 || dayInt > 31) {
			validationMsg.append(VALIDATION_EXCEL_IMPORT_INVALID_DAY); 
			addSeparator(validationMsg);
		}
		
		return cutSplitter(validationMsg);
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
