package com.codeproj.traininghandler.util.excel;

import static org.junit.Assert.*;
import static com.codeproj.traininghandler.util.Constants.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TrainingExcelValidatorTest {
	
	public static final String VALID_YEAR = "2014";
	public static final String INVALID_YEAR = "1990";
	public static final String VALID_MONTH1 = "12";
	public static final String VALID_MONTH2 = "01";
	public static final String VALID_MONTH3 = "1";
	public static final String INVALID_MONTH1 = "100";
	public static final String INVALID_MONTH2 = "100";
	public static final String INVALID_NUMBER = "AAAAA";
	public static final String MINUS_INVALID_NUMBER = "AAAAA";
	public static final String VALID_DAY1 = "31";
	public static final String VALID_DAY2 = "1";
	public static final String VALID_DAY3 = "01";
	public static final String INVALID_DAY1 = "-1";
	public static final String INVALID_DAY2 = "32";
	public MultipartFile importFile;

	@Before
	public void setUp() {
		byte[] emptyArray = {};
		importFile = new MockMultipartFile("fileData", "none", "text/plain", emptyArray);
	}
	
	@Test
	public void testValidateImportExcelInputParamsMethod() {
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH1, VALID_DAY1, importFile));
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH2, VALID_DAY2, importFile));
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_INVALID_YEAR''", VALIDATION_EXCEL_IMPORT_INVALID_YEAR, TrainingExcelValidator.validateImportExcelInputParams(INVALID_NUMBER, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_INVALID_YEAR''", VALIDATION_EXCEL_IMPORT_INVALID_YEAR, TrainingExcelValidator.validateImportExcelInputParams(INVALID_YEAR, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_MONTH'", VALIDATION_EXCEL_IMPORT_INVALID_MONTH, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, INVALID_NUMBER, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_MONTH'", VALIDATION_EXCEL_IMPORT_INVALID_MONTH, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, INVALID_MONTH1, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_MONTH'", VALIDATION_EXCEL_IMPORT_INVALID_MONTH, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, INVALID_MONTH2, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_DAY'", VALIDATION_EXCEL_IMPORT_INVALID_DAY, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH1, INVALID_NUMBER, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_DAY'", VALIDATION_EXCEL_IMPORT_INVALID_DAY, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH1, INVALID_DAY1, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_DAY'", VALIDATION_EXCEL_IMPORT_INVALID_DAY, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH1, INVALID_DAY2, importFile));
		assertEquals("Validation result should return 'VALIDATION_EXCEL_IMPORT_INVALID_FILE'", VALIDATION_EXCEL_IMPORT_INVALID_FILE, TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH1, VALID_DAY1, null));
	}

}
