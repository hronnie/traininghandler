package com.codeproj.traininghandler.address.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.model.Address;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;

@RunWith(MockitoJUnitRunner.class)
public class AddressManagerTest {
	
	public AddressManager manager = null;
	
	public static final String VALID_POST_CODE = "SDF33";
	public static final String VALID_CITY = "London";
	public static final String VALID_STREET = "Test Street name 1";
	public static final String VALID_HOUSE_NUMBER = "18/a";
	public static final String VALID_COUNTRY = "Magyarorszag";
	public static final String VALID_ONE_LINE_ADDRESS = "Street name 5, London";
	static final String TEST_POST_CODE = "EC12 7LB";
	static final String TEST_ADDRESS = "12 Test Street, London";
	static final Long TEST_ADDRESS_ID = 2L;

	static final TrainingType TEST_TRAINING_TYPE;
	static final User TEST_USER;
	
	static final Address TEST_ADDRESS_MODEL;
	
	static {
		TEST_USER = new User(1L);
		TEST_TRAINING_TYPE = new TrainingType(10L);
		TEST_ADDRESS_MODEL = new Address(TEST_ADDRESS_ID, TEST_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY, TEST_ADDRESS, false, false, null);
	}
	
	@Mock
	public AddressDAO addressDAO;
	
	@Before
	public void setUp() throws Exception {
		manager = new AddressManager();
		manager.setAddressDAO(addressDAO);
		when(addressDAO.create(new Address(VALID_CITY, VALID_COUNTRY, VALID_HOUSE_NUMBER, VALID_POST_CODE, VALID_STREET, null))).thenReturn(1L);
		when(addressDAO.create(new Address(null, null, null, VALID_POST_CODE, null, VALID_ONE_LINE_ADDRESS))).thenReturn(3L);
		when(addressDAO.getAddressByAddressId(TEST_ADDRESS_ID)).thenReturn(TEST_ADDRESS_MODEL);
		when(addressDAO.edit(TEST_ADDRESS_MODEL)).thenReturn(true);
	}

	@Test
	public void testCreateUser() {
		Long result = manager.create(VALID_CITY, VALID_COUNTRY, VALID_HOUSE_NUMBER, VALID_POST_CODE, VALID_STREET, null);
		assertEquals("Failed to call DAO method", new Long(1L), result);
	}
	
	@Test
	public void testCreateUserFromForm() {
		Long result = manager.create(null, null, null, VALID_POST_CODE, null, VALID_ONE_LINE_ADDRESS);
		assertEquals("Failed to call DAO method", new Long(3L), result);
	}
	
	@Test
	public void testGetAddressByAddressId() {
		Address result = manager.getAddressByAddressId(TEST_ADDRESS_ID);
		assertNotNull("Failed to call DAO method", result);
		assertEquals("addressId is not as expected", TEST_ADDRESS_ID, result.getAddressId());
		assertEquals("Postal code is not as expected", TEST_POST_CODE, result.getPostalCode());
		assertEquals("One line address is not as expected", TEST_ADDRESS, result.getOneLineAddress());
	}

	@Test
	public void testEditAddress() {
		boolean result = manager.edit(TEST_ADDRESS_MODEL);
		assertTrue("Edit should not fail", result);
	}
}

