package com.codeproj.traininghandler.user.manager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.dao.UserRoleDAO;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {
	
	public UserManager manager = null;
	public static final Date VALID_DATE;
	public static final String VALID_EMAIL = "test@test.com";
	public static final User TEST_USER_MODEL;
	public static final Long TEST_USER_ID = 3L;
	public static final Long TEST_ADDRESS_ID = 5L;
	public static final String TEST_NAME = "Test Name";
	public static final String TEST_EMAIL = "test@example.com";
	public static final String TEST_MOBILE_NO = "235435-345";
	public static final String VALID_NAME = "Valid Name";
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DATE = dt.toDate();
		TEST_USER_MODEL = new User(TEST_USER_ID, TEST_NAME, TEST_EMAIL, TEST_MOBILE_NO);
	}
	
	@Mock
	public UserDAO userDAO;
	
	@Mock
	public AddressDAO addressDAO;
	
	@Mock
	public CompletedTrainingDAO completedTrainingDAO;
	
	@Mock
	public UserRoleDAO userRoleDAO;
	
	@Before
	public void setUp() throws Exception {
		manager = new UserManager();
		manager.setUserDAO(userDAO);
		manager.setAddressDAO(addressDAO);
		manager.setCompletedTrainingDAO(completedTrainingDAO);
		manager.setUserRoleDAO(userRoleDAO);
		
		when(userDAO.create(new User("name", "displayName", VALID_DATE, "324234315", "a@b.com", 1L))).thenReturn(1L);
		when(userDAO.getUserIdByEmailAndName(VALID_EMAIL, VALID_NAME)).thenReturn(1L);
		when(userDAO.getUserByUserId(TEST_USER_ID)).thenReturn(TEST_USER_MODEL);
		when(userDAO.edit(TEST_USER_MODEL)).thenReturn(true);
		
		when(userDAO.delete(TEST_USER_ID)).thenReturn(true);
		when(addressDAO.deleteByUserId(TEST_ADDRESS_ID)).thenReturn(true);
		when(completedTrainingDAO.deleteByUserId(TEST_USER_ID)).thenReturn(true);
		when(userRoleDAO.deleteByUserId(TEST_USER_ID)).thenReturn(true);
	}

	@Test
	public void testCreateUser() {
		Long result = manager.create("name", "displayName", VALID_DATE, "324234315", "a@b.com", 1L);
		assertEquals("Failed to call DAO method", new Long(1L), result);
	}
	
	@Test
	public void testGetUserIdByEmailAndName() {
		Long result = manager.getUserIdByEmailAndName(VALID_EMAIL, VALID_NAME);
		assertEquals("Getting user id failed", new Long(1L), result);
	}
	
	@Test
	public void testGetUserIdByUserId() {
		User result = manager.getUserByUserId(TEST_USER_ID);
		assertNotNull("User shouldn't be null", result);
		assertEquals("User id doesn't match", TEST_USER_ID, result.getUserId());
		assertEquals("Name doesn't match", TEST_NAME, result.getName());
		assertEquals("Phone number doesn't match", TEST_MOBILE_NO, result.getMobileNo());
		assertEquals("Email doesn't match", TEST_EMAIL, result.getEmail());
	}
	
	@Test
	public void testEditUser() {
		boolean result = manager.edit(TEST_USER_MODEL);
		assertTrue("Result should be true", result);
	}
	
	@Test
	public void testDeleteUser() {
		boolean result = manager.deleteTrainee(TEST_USER_ID, TEST_ADDRESS_ID);
		assertTrue("delete should've been successful", result);
	}

}
