package com.codeproj.traininghandler.rest.gatherTraineeInfo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import static com.codeproj.traininghandler.util.Constants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.GeneralIdListResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class GatherTraineeInfoServiceTest {
	
	public GatherTraineeInfoService service;
	public static final AddressDto VALID_ADDRESS;
	public static final UserDto VALID_USER;
	public static final List<CompletedUserTrainingDto> VALID_COMPLETED_USER_TRAINING_LIST;
	public List<CompletedUserTrainingDto> changableCompletedUserTrList = new ArrayList<>();
	public static final String VALID_LAST_NAME = "Lastname";
	public static final String VALID_FIRST_NAME = "Firstname";
	public static final String VALID_DISPLAY_NAME = "mycustomDisplay name";
	public static final Long VALID_ADDRESS_ID = 1L;
	public static final String VALID_POST_CODE = "SDF33";
	public static final String VALID_CITY = "London";
	public static final String VALID_STREET = "Test Street name 1";
	public static final String VALID_HOUSE_NUMBER = "18/a";
	public static final String VALID_COUNTRY = "Magyarorszag";
	public static final String VALID_TELEPHONE_NUMBER = "077-6637-3338";
	public static final String VALID_EMAIL = "asdf@asf.com";
	public static final String INVALID_EMAIL = "simpletext";
	public static final Date VALID_DOB;
	
	@Mock
	public AddressService addressService;
	
	@Mock
	public UserService userService;
	
	@Mock 
	public CompletedTrainingService completedTrainingService;
	
	static {
		VALID_ADDRESS = new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY);
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DOB = dt.toDate();
		VALID_USER = new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID);
		VALID_COMPLETED_USER_TRAINING_LIST = new ArrayList<>();
		VALID_COMPLETED_USER_TRAINING_LIST.add(new CompletedUserTrainingDto(1L, 1L, dt.toDate()));
	}
	
	@Before
	public void setUp() throws Exception {
		service = new GatherTraineeInfoService();
		service.setAddressService(addressService);
		service.setUserService(userService);
		service.setCompletedTrainingService(completedTrainingService);
		when(addressService.create(VALID_ADDRESS)).thenReturn(new GeneralIdResponse(1L));
		when(userService.create(VALID_USER)).thenReturn(new GeneralIdResponse(1L));
		List<Long> complServResult = new ArrayList<>();
		complServResult.add(1L);
		when(completedTrainingService.create(VALID_COMPLETED_USER_TRAINING_LIST)).thenReturn(new GeneralIdListResponse(complServResult));
	}
	
	// ***************** Tests VALID input object **************
	
	@Test
	public void testGatherTraineeInfoWithValidObject() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullObject() throws ValidationException {
		service.saveTraineePersonalAndTrainingInfo(null);  
	}

	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyInputObject() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyAddressObject() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithUserObject() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithCompletedUserTrainingObject() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	// ***************** Tests for address **************
	
	// Null check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullPostCode() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(null, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullCity() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, null, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullStreet() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, null, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullHouseNumber() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, null, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullCountry() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, null));
		traineeInfoDto.setUser(VALID_USER);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	// Empty check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyPostCode() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto("", VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyCity() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, "", VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyStreet() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, "", VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyHouseNumber() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, "", VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyCountry() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, ""));
		traineeInfoDto.setUser(VALID_USER);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	// Too long check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongPostCode() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongCity() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_STREET, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongStreet() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_HOUSE_NUMBER, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongHouseNumber() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setUser(VALID_USER);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_COUNTRY));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongCountry() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(new AddressDto(VALID_POST_CODE, VALID_CITY, VALID_STREET, VALID_HOUSE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100));
		traineeInfoDto.setUser(VALID_USER);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	// ***************** Tests for user info **************
	
	// Null check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullLastName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(null, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullFirstName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, null, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullDisplayName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, null, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullDob() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, null, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullPhoneNo() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, null, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullEmail() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, null, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	// Empty check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyLastName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto("", VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyFirstName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, "", VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyDisplayName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, "", VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyPhoneNo() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, "", VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyEmail() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, "", VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	// Too long check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongLastName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongFirstName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongDisplayName() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_DOB, VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongPhoneNo() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithTooLongEmail() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	// Dob and email additional check
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithDobYoungerThen6Years() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		DateTime dobDt = new DateTime(2014, 3, 26, 12, 0, 0, 0);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, dobDt.toDate(), VALID_TELEPHONE_NUMBER, VALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithInvalidEmail() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		traineeInfoDto.setCompletedUserTrainingList(VALID_COMPLETED_USER_TRAINING_LIST);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(new UserDto(VALID_LAST_NAME, VALID_FIRST_NAME, VALID_DISPLAY_NAME, VALID_DOB, VALID_TELEPHONE_NUMBER, INVALID_EMAIL, VALID_ADDRESS_ID));
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}

	// ***************** Tests for user info **************
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithInvalidTrainingTypeId() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		changableCompletedUserTrList.clear();
		changableCompletedUserTrList.add(new CompletedUserTrainingDto(null, -1L, new Date()));
		traineeInfoDto.setCompletedUserTrainingList(changableCompletedUserTrList);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(VALID_USER);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
	
	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithNullCompletedDate() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		changableCompletedUserTrList.clear();
		changableCompletedUserTrList.add(new CompletedUserTrainingDto(null, 1L, null));
		traineeInfoDto.setCompletedUserTrainingList(changableCompletedUserTrList);
		traineeInfoDto.setAddress(VALID_ADDRESS);
		traineeInfoDto.setUser(VALID_USER);
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}
}

