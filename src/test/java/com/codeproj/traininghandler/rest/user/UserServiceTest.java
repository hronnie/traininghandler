package com.codeproj.traininghandler.rest.user;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.gatherTraineeInfo.GatherTraineeInfoService;

public class UserServiceTest {
	
	public UserService service;
	public static final String VALID_LAST_NAME = "Lastname";
	public static final String VALID_FIRST_NAME = "Firstname";
	public static final String VALID_DISPLAY_NAME = "mycustomDisplay name";
	public static final String VALID_TELEPHONE_NUMBER = "077-6637-3338";
	public static final String VALID_EMAIL = "asdf@asf.com";
	public static final String INVALID_EMAIL = "simpletext";
	public static final Date VALID_DOB;
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
			+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DOB = dt.toDate();
	}
	
	@Before
	public void setUp() {
		service = new UserService();
	}

	@Test(expected = ValidationException.class)
	public void testCreateUserWithEmptyDto() throws ValidationException {
		//UserDto user = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL);
		service.create(null);
	}

}
