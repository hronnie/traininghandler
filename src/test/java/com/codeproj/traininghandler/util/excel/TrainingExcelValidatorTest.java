package com.codeproj.traininghandler.util.excel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TrainingExcelValidatorTest {
	
	public static final String VALID_YEAR = "2014";
	public static final String VALID_MONTH1 = "12";
	public static final String VALID_MONTH2 = "01";
	public static final String VALID_MONTH3 = "1";
	public static final String INVALID_MONTH = "100";
	public static final String INVALID_NUMBER = "AAAAA";
	public static final String MINUS_INVALID_NUMBER = "AAAAA";
	public static final String VALID_DAY1 = "31";
	public static final String VALID_DAY2 = "1";
	public static final String VALID_DAY3 = "01";
	public MultipartFile importFile;

	@Before
	public void setUp() {
		byte[] emptyArray = {};
		importFile = new MockMultipartFile("fileData", "none", "text/plain", emptyArray);
	}
	
	// String year,
	//String month, String day, MultipartFile importFile
	@Test
	public void testValidateImportExcelInputParamsMethod() {
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH1, VALID_DAY1, importFile));
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH2, VALID_DAY2, importFile));
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_YEAR, VALID_MONTH3, VALID_DAY3, importFile));
		
	}

}
