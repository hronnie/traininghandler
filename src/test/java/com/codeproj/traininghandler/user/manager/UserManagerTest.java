package com.codeproj.traininghandler.user.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserManagerTest {
	
	public UserManager manager = null;
	public static final Date VALID_DATE;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DATE = dt.toDate();
	}
	
	@Mock
	public UserDAO userDAO;
	
	@Before
	public void setUp() throws Exception {
		manager = new UserManager();
		manager.setUserDAO(userDAO);
		when(userDAO.create(new User("name", "displayName", VALID_DATE, "324234315", "a@b.com", 1L))).thenReturn(1L);
	}

	@Test
	public void testCreateUser() {
		Long result = manager.create("name", "displayName", VALID_DATE, "324234315", "a@b.com", 1L);
		assertEquals("Failed to call DAO method", new Long(1L), result);
	}

}
