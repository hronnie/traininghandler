package com.codeproj.traininghandler.rest.address;

import static com.codeproj.traininghandler.util.Constants.PARAMETER_STRING_SIZE_MORE_THEN_100;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
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
	public void testCreateAddressValidData() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		
		GeneralIdResponse result = service.create(addressDto);
		assertEquals("Wrong id when creating address", new Long(1L), result.getValue());
	}
	
	// null check 
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithNullObject() throws ValidationException {
		service.create(null);  
	}

	@Test(expected = ValidationException.class)
	public void testCreateAddressWithNullPostCode() throws ValidationException {
		AddressDto addressDto = new AddressDto(null, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithNullCity() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, null, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithNullStreet() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, null, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithNullHouseNumber() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, null, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithNullCountry() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, null);
		service.create(addressDto);  
	}
	// empty check 
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithEmptyPostCode() throws ValidationException {
		AddressDto addressDto = new AddressDto("", VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithEmptyCity() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, "", VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithEmptyStreet() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, "", VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithEmptyHouseNumber() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, "", VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithEmptyCountry() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, "");
		service.create(addressDto);  
	}
	
	// too long check 
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithTooLongPostCode() throws ValidationException {
		AddressDto addressDto = new AddressDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithTooLongCity() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithTooLongStreet() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithTooLongHouseNumber() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressWithTooLongCountry() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100);
		service.create(addressDto);  
	}
	
	
	@Test
	public void testCreateFormForm() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_ONE_LINE_ADDRESS);
		GeneralIdResponse result = service.createFromForm(addressDto);
		assertEquals("Result should be 2L ", new Long(2L), result.getValue());
	}
}
