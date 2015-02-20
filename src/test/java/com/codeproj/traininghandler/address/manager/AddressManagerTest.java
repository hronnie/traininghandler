package com.codeproj.traininghandler.address.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.model.Address;

@RunWith(MockitoJUnitRunner.class)
public class AddressManagerTest {
	
	public AddressManager manager = null;
	
	public static final String VALID_POST_CODE = "SDF33";
	public static final String VALID_CITY = "London";
	public static final String VALID_STREET = "Test Street name 1";
	public static final String VALID_HOUSE_NUMBER = "18/a";
	public static final String VALID_COUNTRY = "Magyarorszag";
	public static final String VALID_ONE_LINE_ADDRESS = "Street name 5, London";
	
	@Mock
	public AddressDAO addressDAO;
	
	@Before
	public void setUp() throws Exception {
		manager = new AddressManager();
		manager.setAddressDAO(addressDAO);
		when(addressDAO.create(new Address(VALID_CITY, VALID_COUNTRY, VALID_HOUSE_NUMBER, VALID_POST_CODE, VALID_STREET, null))).thenReturn(1L);
		when(addressDAO.create(new Address(null, null, null, VALID_POST_CODE, null, VALID_ONE_LINE_ADDRESS))).thenReturn(3L);
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
}
