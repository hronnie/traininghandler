package com.codeproj.traininghandler.rest.address;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
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
	public AddressService service;
	
	@Mock
	public AddressManager manager;

	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
			+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";

	static {
		VALID_ADDRESS = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
	}
	
	@Before
	public void setUp() throws Exception {
		service = new AddressService();
		service.setAddressManager(manager);
		when(manager.create(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY)).thenReturn(1L);
	}
	
	// valid inputs
	
	
	public void testGatherTraineeInfoWithValidData() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		
		GeneralIdResponse result = service.create(addressDto);
		assertEquals("Wrong id when creating address", new Long(1L), result.getValue());
	}
	
	// null check 
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullObject() throws ValidationException {
		service.create(null);  
	}

	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullPostCode() throws ValidationException {
		AddressDto addressDto = new AddressDto(null, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullCity() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, null, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullStreet() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, null, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullHouseNumber() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, null, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullCountry() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, null);
		service.create(addressDto);  
	}
	// empty check 
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyPostCode() throws ValidationException {
		AddressDto addressDto = new AddressDto("", VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyCity() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, "", VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyStreet() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, "", VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyHouseNumber() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, "", VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyCountry() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, "");
		service.create(addressDto);  
	}
	
	// too long check 
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongPostCode() throws ValidationException {
		AddressDto addressDto = new AddressDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongCity() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongStreet() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongHouseNumber() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_COUNTRY);
		service.create(addressDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongCountry() throws ValidationException {
		AddressDto addressDto = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100);
		service.create(addressDto);  
	}
}
