package com.codeproj.traininghandler.rest.address;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static com.codeproj.traininghandler.common.MessageConstants.*;
import static com.codeproj.traininghandler.util.Constants.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {
	
	public static final AddressDto VALID_ADDRESS;
	public static final Long VALID_USER_ID = 1L;
	public static final String VALID_POST_CODE = "SDF33";
	public static final String VALID_CITY = "London";
	public static final String VALID_STREET = "Test Street name 1";
	public static final String VALID_HOUSE_NUMBER = "18/a";
	public static final String VALID_COUNTRY = "Magyarorszag";
	public static final String VALID_ONE_LINE_ADDRESS = "Street name 5, London";
	public AddressService service;
	
	@Mock
	public AddressManager manager;


	static {
		VALID_ADDRESS = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
	}
	
	@Before
	public void setUp() throws Exception {
		service = new AddressService();
		service.setAddressManager(manager);
		when(manager.create(VALID_CITY, VALID_COUNTRY, VALID_HOUSE_NUMBER, VALID_POST_CODE, VALID_STREET, null)).thenReturn(1L);
		when(manager.create(null, null, null, VALID_POST_CODE, null, VALID_ONE_LINE_ADDRESS)).thenReturn(2L);
	}
	
	// valid inputs
	
	@Test
	public void testCreateAddressValidData() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		
		GeneralIdResponse result = service.create(addressDto);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		assertEquals("Wrong id when creating address", new Long(1L), result.getValue());
	}
	
	// null check 
	
	@Test
	public void testCreateAddressWithNullObject() {
		GeneralIdResponse result = service.create(null);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}

	@Test
	public void testCreateAddressWithNullPostCode() {
		AddressDto addressDto = new AddressDto(null, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_POSTCODE, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithNullCity() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, null, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_CITY, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithNullStreet() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, null, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_STREET, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithNullHouseNumber() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, null, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_HOUSE_NO, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithNullCountry() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, null);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_COUNTRY, result.getMessage());
	}
	// empty check 
	
	@Test
	public void testCreateAddressWithEmptyPostCode() {
		AddressDto addressDto = new AddressDto("", VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_POSTCODE, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithEmptyCity() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, "", VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_CITY, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithEmptyStreet() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, "", VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_STREET, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithEmptyHouseNumber() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, "", VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_HOUSE_NO, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithEmptyCountry() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, "");
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_COUNTRY, result.getMessage());
	}
	
	// too long check 
	
	@Test
	public void testCreateAddressWithTooLongPostCode() {
		AddressDto addressDto = new AddressDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_POSTCODE + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_POSTCODE;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithTooLongCity() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_CITY + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_CITY;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithTooLongStreet() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_STREET + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_STREET;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithTooLongHouseNumber() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_COUNTRY);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_HOUSE_NO + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_HOUSE_NO;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateAddressWithTooLongCountry() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100);
		GeneralIdResponse result = service.create(addressDto);  
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_COUNTRY + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_COUNTRY;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateFormForm() {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_ONE_LINE_ADDRESS);
		GeneralIdResponse result = service.createFromForm(addressDto);
		assertEquals("Result should be 2L ", new Long(2L), result.getValue());
	}
}