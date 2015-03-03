package com.codeproj.traininghandler.util.excel;

import static org.junit.Assert.*;
import static com.codeproj.traininghandler.util.Constants.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;

import com.codeproj.traininghandler.dto.TrainingExcelDto;

@RunWith(MockitoJUnitRunner.class)
public class TrainingExcelValidatorTest {
	
	public static final String VALID_YEAR = "2014";
	public static final String INVALID_YEAR = "1990";
	public static final String VALID_MONTH1 = "12";
	public static final String VALID_MONTH2 = "01";
	public static final String VALID_MONTH3 = "1";
	public static final String VALID_TRAINING_TYPE = "1";
	public static final String INVALID_TRAINING_TYPE = "";
	public static final String INVALID_MONTH1 = "100";
	public static final String INVALID_MONTH2 = "100";
	public static final String INVALID_NUMBER = "AAAAA";
	public static final String MINUS_INVALID_NUMBER = "AAAAA";
	public static final String VALID_DAY1 = "31";
	public static final String VALID_DAY2 = "1";
	public static final String VALID_DAY3 = "01";
	public static final String INVALID_DAY1 = "-1";
	public static final String INVALID_DAY2 = "32";
	
	public static final String VALID_NAME = "Teszt Elek";
	public static final String VALID_POST_CODE = "23233R";
	public static final String VALID_ADDRESS = "long address line";
	public static final String VALID_PHONE_NO = "00-113434-344";
	public static final String VALID_EMAIL = "a@b.com";
	
	public static final TrainingExcelDto VALID_EXCEL_DTO = null;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_EMPTY_NAME;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_NULL_NAME;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_LONG_NAME;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_EMPTY_POST_CODE;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_NULL_POST_CODE;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_LONG_POST_CODE;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_EMPTY_ADDRESS;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_NULL_ADDRESS;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_LONG_ADDRESS;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_EMPTY_PHONE_NO;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_NULL_PHONE_NO;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_LONG_PHONE_NO;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_EMPTY_EMAIL;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_NULL_EMAIL;
	public static final TrainingExcelDto INVALID_EXCEL_DTO_LONG_EMAIL;

	@Mock
	public MultipartFile importFile;
	
	static {
		INVALID_EXCEL_DTO_EMPTY_NAME = new TrainingExcelDto("", VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_NULL_NAME = new TrainingExcelDto(null, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_LONG_NAME = new TrainingExcelDto(PARAMETER_STRING_SIZE_MORE_THEN_300, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_EMPTY_POST_CODE = new TrainingExcelDto(VALID_NAME, "", VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_NULL_POST_CODE = new TrainingExcelDto(VALID_NAME, null, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_LONG_POST_CODE = new TrainingExcelDto(VALID_NAME, PARAMETER_STRING_SIZE_MORE_THEN_300, VALID_ADDRESS, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_EMPTY_ADDRESS = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, "", VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_NULL_ADDRESS = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, null, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_LONG_ADDRESS = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_300, VALID_PHONE_NO, VALID_EMAIL);
		INVALID_EXCEL_DTO_EMPTY_PHONE_NO = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, "", VALID_EMAIL);
		INVALID_EXCEL_DTO_NULL_PHONE_NO = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, null, VALID_EMAIL);
		INVALID_EXCEL_DTO_LONG_PHONE_NO = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, PARAMETER_STRING_SIZE_MORE_THEN_300, VALID_EMAIL);
		INVALID_EXCEL_DTO_EMPTY_EMAIL = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, "");
		INVALID_EXCEL_DTO_NULL_EMAIL = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, null);
		INVALID_EXCEL_DTO_LONG_EMAIL = new TrainingExcelDto(VALID_NAME, VALID_POST_CODE, VALID_ADDRESS, VALID_PHONE_NO, PARAMETER_STRING_SIZE_MORE_THEN_300);

	}
	
	@Before
	public void setUp() {
		byte[] emptyArray = {};
		when(importFile.isEmpty()).thenReturn(false);
	}
	
	public List<TrainingExcelDto> trainingAttendendList;

	@Test
	public void testValidateTrainingExcelListMethod() {
		assertEquals("Validation result should return: VALIDATION_EXCEL_EMPTY_LIST", VALIDATION_EXCEL_EMPTY_LIST, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList = new ArrayList<>();
		assertEquals("Validation result should return: VALIDATION_EXCEL_EMPTY_LIST", VALIDATION_EXCEL_EMPTY_LIST, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.add(INVALID_EXCEL_DTO_EMPTY_NAME);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_NAME_EMPTY", VALIDATION_EXCEL_IMPORT_NAME_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_NULL_NAME);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_NAME_EMPTY", VALIDATION_EXCEL_IMPORT_NAME_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_LONG_NAME);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_NAME_TOO_LONG", VALIDATION_EXCEL_IMPORT_NAME_TOO_LONG, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_EMPTY_POST_CODE);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY", VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_NULL_POST_CODE);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY", VALIDATION_EXCEL_IMPORT_POST_CODE_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_LONG_POST_CODE);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_POST_CODE_TOO_LONG", VALIDATION_EXCEL_IMPORT_POST_CODE_TOO_LONG, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_EMPTY_ADDRESS);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY", VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_NULL_ADDRESS);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY", VALIDATION_EXCEL_IMPORT_ADDRESS_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_LONG_ADDRESS);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_ADDRESS_TOO_LONG", VALIDATION_EXCEL_IMPORT_ADDRESS_TOO_LONG, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_EMPTY_PHONE_NO);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY", VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_NULL_PHONE_NO);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY", VALIDATION_EXCEL_IMPORT_PHONE_NO_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_LONG_PHONE_NO);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_PHONE_NO_TOO_LONG", VALIDATION_EXCEL_IMPORT_PHONE_NO_TOO_LONG, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_EMPTY_EMAIL);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_EMAIL_EMPTY", VALIDATION_EXCEL_IMPORT_EMAIL_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_NULL_EMAIL);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_EMAIL_EMPTY", VALIDATION_EXCEL_IMPORT_EMAIL_EMPTY, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
		trainingAttendendList.add(INVALID_EXCEL_DTO_LONG_EMAIL);
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_EMAIL_TOO_LONG", VALIDATION_EXCEL_IMPORT_EMAIL_TOO_LONG, TrainingExcelValidator.validateTrainingExcelList(trainingAttendendList));
		trainingAttendendList.clear();
	}
	
	@Test
	public void testValidateImportExcelInputParamsMethod() {
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH1, VALID_DAY1, importFile));
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH2, VALID_DAY2, importFile));
		assertEquals("Validation result should be empty string", "", TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should be empty string", VALIDATION_EXCEL_IMPORT_INVALID_TRAINING_TYPE_ID, TrainingExcelValidator.validateImportExcelInputParams(INVALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_INVALID_YEAR''", VALIDATION_EXCEL_IMPORT_INVALID_YEAR, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, INVALID_NUMBER, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should return: VALIDATION_EXCEL_IMPORT_INVALID_YEAR''", VALIDATION_EXCEL_IMPORT_INVALID_YEAR, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, INVALID_YEAR, VALID_MONTH3, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_MONTH'", VALIDATION_EXCEL_IMPORT_INVALID_MONTH, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, INVALID_NUMBER, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_MONTH'", VALIDATION_EXCEL_IMPORT_INVALID_MONTH, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, INVALID_MONTH1, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_MONTH'", VALIDATION_EXCEL_IMPORT_INVALID_MONTH, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, INVALID_MONTH2, VALID_DAY3, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_DAY'", VALIDATION_EXCEL_IMPORT_INVALID_DAY, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH1, INVALID_NUMBER, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_DAY'", VALIDATION_EXCEL_IMPORT_INVALID_DAY, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH1, INVALID_DAY1, importFile));
		assertEquals("Validation result should return: 'VALIDATION_EXCEL_IMPORT_INVALID_DAY'", VALIDATION_EXCEL_IMPORT_INVALID_DAY, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH1, INVALID_DAY2, importFile));
		assertEquals("Validation result should return 'VALIDATION_EXCEL_IMPORT_INVALID_FILE'", VALIDATION_EXCEL_IMPORT_INVALID_FILE, TrainingExcelValidator.validateImportExcelInputParams(VALID_TRAINING_TYPE, VALID_YEAR, VALID_MONTH1, VALID_DAY1, null));
	}
}
