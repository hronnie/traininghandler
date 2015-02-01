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
	
	@Mock
	public AddressDAO addressDAO;
	
	@Before
	public void setUp() throws Exception {
		manager = new AddressManager();
		manager.setAddressDAO(addressDAO);
		when(addressDAO.create(new Address("city", "country", "houseNo", "postCode", "street"))).thenReturn(1L);
	}

	@Test
	public void testCreateUser() {
		Long result = manager.create("city", "country", "houseNo", "postCode", "street");
		assertEquals("Failed to call DAO method", new Long(1L), result);
	}
}
