package com.codeproj.traininghandler.rest.user;

import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULD_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.WRONG_VALIDATION_MESSAGE;
import static com.codeproj.traininghandler.util.Constants.MIN_AGE_YEAR;
import static com.codeproj.traininghandler.util.Constants.PARAMETER_STRING_SIZE_MORE_THEN_100;
import static com.codeproj.traininghandler.util.Constants.TABLE_FIELD_SIZE_DISPLAY_NAME;
import static com.codeproj.traininghandler.util.Constants.TABLE_FIELD_SIZE_EMAIL;
import static com.codeproj.traininghandler.util.Constants.TABLE_FIELD_SIZE_FIRST_NAME;
import static com.codeproj.traininghandler.util.Constants.TABLE_FIELD_SIZE_LAST_NAME;
import static com.codeproj.traininghandler.util.Constants.TABLE_FIELD_SIZE_PHONE;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_DATE_NOT_VALID_1;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_DATE_NOT_VALID_2;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_EMAIL_INVALID;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_MANDATORY_PARAMETER;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_PARAMETER_TOO_LONG;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_DISPLAY_NAME;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_EMAIL;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_FIRST_NAME;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_LAST_NAME;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_NAME;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_PHONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	public UserService service;
	public static final String VALID_LAST_NAME = "Lastname ";
	public static final String VALID_FIRST_NAME = "Firstname";
	public static final String VALID_DISPLAY_NAME = "mycustomDisplay name";
	public static final String VALID_TELEPHONE_NUMBER = "077-6637-3338";
	public static final String VALID_EMAIL = "asdf@asf.com";
	public static final String INVALID_EMAIL = "simpletext";
	public static final String EMAIL_WHICH_DOESNT_EXIST = "idontexist@nonexist.com";
	public static final String EMAIL_WHICH_EXISTS = "idoexist@exist.com";
	public static final String NAME_WHICH_DOESNT_EXIST = "Name Doesnt Exist";
	public static final String NAME_WHICH_EXISTS = "Name Exist";
	public static final Date VALID_DOB;
	public static final Long VALID_ADDRESS_ID = 1L;
	
	public static final String VALID_NAME = "Valid Name";
	public static final String VALID_POSTCODE = "nei 93d";
	public static final String VALID_ADDRESS = "ad dafdf  dasfdsf df";
	public static final String VALID_PHONE_NO = "54-54454-4545/7";
	public static final String VALID_NUMERIC_PHONE_NO = "545445445457";
	public static TrainingExcelDto VALID_TRAINING_EXCEL_ITEM;
	
	@Mock
	public UserManager userManager;
	
	@Mock
	public AddressService addressService;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DOB = dt.toDate();
		VALID_TRAINING_EXCEL_ITEM = new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_EXISTS);
	}
	
	@Before
	public void setUp() {
		service = new UserService();
		service.setUserManager(userManager);
		service.setAddressService(addressService);
		when(userManager.create(VALID_LAST_NAME + VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID)).thenReturn(1L);
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, NAME_WHICH_EXISTS)).thenReturn(new Long(-1));
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, NAME_WHICH_DOESNT_EXIST)).thenReturn(new Long(-1));
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_EXISTS, NAME_WHICH_DOESNT_EXIST)).thenReturn(new Long(-1));
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_EXISTS, NAME_WHICH_EXISTS)).thenReturn(new Long(1));
		when(addressService.createFromForm(new AddressDto(VALID_POSTCODE, VALID_ADDRESS))).thenReturn(new GeneralIdResponse(1L));
	}

	@Test
	public void testCreateUserWithEmptyDto() {
		GeneralIdResponse result = service.create(null);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithValidData() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
	}
	
	// null check
	
	@Test
	public void testCreateUserWithNullLastName() {
		UserDto user = new UserDto(null, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_LAST_NAME, result.getMessage());
	}

	
	@Test
	public void testCreateUserWithNullFirstName() {
		UserDto user = new UserDto(VALID_LAST_NAME, null, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_FIRST_NAME, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithNullAddressId() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, null);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	// empty check
	
	@Test
	public void testCreateUserWithEmptyLastName() {
		UserDto user = new UserDto("", VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_LAST_NAME, result.getMessage());
	}
	
	
	@Test
	public void testCreateUserWithEmptyFirstName() {
		UserDto user = new UserDto(VALID_LAST_NAME, "", VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_FIRST_NAME, result.getMessage());
	}
	
	// too long check
	
	@Test
	public void testCreateUserWithTooLongLastName() {
		UserDto user = new UserDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_LAST_NAME + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_LAST_NAME;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	
	@Test
	public void testCreateUserWithTooLongFirstName() {
		UserDto user = new UserDto(VALID_LAST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_FIRST_NAME + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_FIRST_NAME;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithTooLongDisplayName() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_DISPLAY_NAME + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_DISPLAY_NAME;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithTooLongPhoneNumber() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_PHONE + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_PHONE;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithTooLongEmail() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_EMAIL + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_EMAIL;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	// dob and email additional checks
	
	@Test
	public void testGatherTraineeInfoWithDobYoungerThen6Years() {
		DateTime dobDt = new DateTime(2014, 3, 26, 12, 0, 0, 0);
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, dobDt.toDate(), VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_DATE_NOT_VALID_1 + dobDt.toDate() + VALIDATION_DATE_NOT_VALID_2 + MIN_AGE_YEAR;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testGatherTraineeInfoWithInvalidEmail() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, INVALID_EMAIL, VALID_ADDRESS_ID);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_EMAIL_INVALID, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithInvalidAddressId() {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, -5L);
		GeneralIdResponse result = service.create(user);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	// get user by email and name

	@Test
	public void testGetUserIdByEmail() {
		GeneralIdResponse serviceResult = service.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, NAME_WHICH_EXISTS);
		assertFalse(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, serviceResult.getSuccess());
		assertEquals("user id should be -1", new Long(-1L), serviceResult.getValue());
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, serviceResult.getSuccess());
		serviceResult = service.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, NAME_WHICH_DOESNT_EXIST);
		assertEquals("user id should be -1 ", new Long(-1L), serviceResult.getValue());
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, serviceResult.getSuccess());
		serviceResult = service.getUserIdByEmailAndName(EMAIL_WHICH_EXISTS, NAME_WHICH_DOESNT_EXIST);
		assertEquals("user id should be -1 ", new Long(-1L), serviceResult.getValue());
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, serviceResult.getSuccess());
		serviceResult = service.getUserIdByEmailAndName(EMAIL_WHICH_EXISTS, NAME_WHICH_EXISTS);
		assertEquals("user id should be 1 for existing user", new Long(1L), serviceResult.getValue());
	}
	
	// createUserWithAddress
	
	@Test
	public void testCreateUserWithAddress() {
		when(userManager.create(VALID_NAME, null, null, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST, 1L)).thenReturn(5L);
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, VALID_NAME)).thenReturn(-1L);
		GeneralIdResponse result = service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST));
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		assertEquals("user id should be 5 ", new GeneralIdResponse(5L), result);
	}
	
	@Test
	public void testCreateUserWithAddressWithNullDto() {
		GeneralIdResponse result = service.createUserWithAddress(null);
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithAddressExistingUser() {
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_EXISTS, VALID_NAME)).thenReturn(1L);
		GeneralIdResponse result = service.createUserWithAddress(VALID_TRAINING_EXCEL_ITEM);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		assertEquals("user id should be 1 for existing user", new GeneralIdResponse(1L), result);
	}
	
	@Test
	public void testCreateUserWithAddressEmptyName() {
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, "")).thenReturn(-1L);
		GeneralIdResponse result = service.createUserWithAddress(new TrainingExcelDto("", VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_NAME, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithAddressNullName() {
		when(userManager.getUserIdByEmailAndName(EMAIL_WHICH_DOESNT_EXIST, null)).thenReturn(-1L);
		GeneralIdResponse result = service.createUserWithAddress(new TrainingExcelDto(null, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_NAME, result.getMessage());
	}
	
	@Test
	public void testCreateUserWithAddressInvalidEmail() {
		when(userManager.getUserIdByEmailAndName(INVALID_EMAIL, VALID_NAME)).thenReturn(-1L);
		GeneralIdResponse result = service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, INVALID_EMAIL));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_EMAIL_INVALID, result.getMessage());
	}
}
