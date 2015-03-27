package com.codeproj.traininghandler.rest.user;

import static com.codeproj.traininghandler.util.Constants.PARAMETER_STRING_SIZE_MORE_THEN_100;
import static org.junit.Assert.assertEquals;
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
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;
import com.mchange.util.AssertException;

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
	public void setUp() throws ValidationException {
		service = new UserService();
		service.setUserManager(userManager);
		service.setAddressService(addressService);
		when(userManager.create(VALID_LAST_NAME + VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID)).thenReturn(1L);
		when(userManager.getUserIdByEmail(EMAIL_WHICH_DOESNT_EXIST)).thenReturn(new Long(-1));
		when(userManager.getUserIdByEmail(EMAIL_WHICH_EXISTS)).thenReturn(new Long(1));
		when(addressService.createFromForm(new AddressDto(VALID_POSTCODE, VALID_ADDRESS))).thenReturn(new GeneralIdResponse(1L));
	}

	@Test(expected = ValidationException.class)
	public void testCreateUserWithEmptyDto() throws ValidationException {
		service.create(null);
	}
	
	@Test
	public void testCreateUserWithValidData() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	// null check
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithNullLastName() throws ValidationException {
		UserDto user = new UserDto(null, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}

	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithNullFirstName() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, null, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithNullDisplayName() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, null, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithNullDob() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, null, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithNullPhoneNumber() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, null, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test
	public void testCreateUserWithNullEmail() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, null, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithNullAddressId() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, null);
		service.create(user);
	}
	
	// empty check
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithEmptyLastName() throws ValidationException {
		UserDto user = new UserDto("", VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithEmptyFirstName() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, "", VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithEmptyDisplayName() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, "", VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}

	@Test(expected = ValidationException.class)
	public void testCreateUserWithEmptyPhoneNumber() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, "", VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test
	public void testCreateUserWithEmptyEmail() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, "", VALID_ADDRESS_ID);
		service.create(user);
	}
	
	// too long check
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithTooLongLastName() throws ValidationException {
		UserDto user = new UserDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithTooLongFirstName() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithTooLongDisplayName() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithTooLongPhoneNumber() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithTooLongEmail() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	// dob and email additional checks
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithDobYoungerThen6Years() throws ValidationException {
		DateTime dobDt = new DateTime(2014, 3, 26, 12, 0, 0, 0);
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, dobDt.toDate(), VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithInvalidEmail() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, INVALID_EMAIL, VALID_ADDRESS_ID);
		service.create(user);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithInvalidAddressId() throws ValidationException {
		UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, -5L);
		service.create(user);
	}
	
	// get user by email
	
	@Test
	public void testGetUserIdByEmail() {
		GeneralIdResponse serviceResult = service.getUserIdByEmail(EMAIL_WHICH_DOESNT_EXIST);
		assertEquals("user id should be -1 for non existing user", new Long(-1L), serviceResult.getValue());
		serviceResult = service.getUserIdByEmail(EMAIL_WHICH_EXISTS);
		assertEquals("user id should be 1 for existing user", new Long(1L), serviceResult.getValue());
	}
	
	// createUserWithAddress
	
	@Test
	public void testCreateUserWithAddress() throws ValidationException {
		when(userManager.create(VALID_NAME, null, null, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST, 1L)).thenReturn(5L);
		Long result = service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST));
		assertEquals("user id should be 5 ", new Long(5L), result);
		
		String phoneEmail = VALID_NUMERIC_PHONE_NO + Constants.EXCEL_TRAINING_MISSING_EMAIL_DOMAIN;
		when(userManager.create(VALID_NAME, null, null, VALID_NUMERIC_PHONE_NO, phoneEmail, 1L)).thenReturn(6L);
		when(userManager.getUserIdByEmail(phoneEmail)).thenReturn(-1L);
		result = service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, VALID_NUMERIC_PHONE_NO, null));
		assertEquals("user id should be 6 ", new Long(6L), result);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithAddressWithNullDto() throws ValidationException {
		service.createUserWithAddress(null);
	}
	
	@Test
	public void testCreateUserWithAddressExistingUser() throws ValidationException {
		Long result = service.createUserWithAddress(VALID_TRAINING_EXCEL_ITEM);
		assertEquals("user id should be 1 for existing user", new Long(1L), result);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithAddressEmptyName() throws ValidationException {
		service.createUserWithAddress(new TrainingExcelDto("", VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithAddressNullName() throws ValidationException {
		service.createUserWithAddress(new TrainingExcelDto(null, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, EMAIL_WHICH_DOESNT_EXIST));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithAddressEmptyPhoneNo() throws ValidationException {
		service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, "", EMAIL_WHICH_DOESNT_EXIST));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithAddressNullPhoneNo() throws ValidationException {
		service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, "", EMAIL_WHICH_DOESNT_EXIST));
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateUserWithAddressInvalidEmail() throws ValidationException {
		when(userManager.getUserIdByEmail(INVALID_EMAIL)).thenReturn(-1L);
		service.createUserWithAddress(new TrainingExcelDto(VALID_NAME, VALID_POSTCODE, VALID_ADDRESS, VALID_PHONE_NO, INVALID_EMAIL));
	}
}
