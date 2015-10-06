package com.codeproj.traininghandler.rest.trainees;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static com.codeproj.traininghandler.util.Constants.PARAMETER_STRING_SIZE_MORE_THEN_100;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.dto.TraineeDtos;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.manager.trainee.TraineeManager;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.model.Address;
import com.codeproj.traininghandler.model.User;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.trainee.TraineeService;

@RunWith(MockitoJUnitRunner.class)
public class TraineeServiceTest {
	
	TraineeService service;
	static final TraineeDtos TRAINEE_DTOS;
	static final TraineeDto TRAINEE_DTO;
	static final List<TraineeDto> TRAINEE_LIST;
	static final String TEST_NAME = "Test Name";
	static final String TEST_POST_CODE = "EC12 7LB";
	static final String TEST_ADDRESS = "12 Test Street, London";
	static final String TEST_PHONE_NO = "2323-2323/23";
	static final String TEST_EMAIL = "mail@example.com";
	static final String TEST_OLD_NAME = "OLD Test Name";
	static final String TEST_OLD_POST_CODE = "OLD EC12 7LB";
	static final String TEST_OLD_ADDRESS = "OLD 12 Test Street, London";
	static final String TEST_OLD_PHONE_NO = "OLD 2323-2323/23";
	static final String TEST_OLD_EMAIL = "OLDmail@example.com";
	static final Long TEST_USER_ID = 1L;
	static final Long TEST_ADDRESS_ID = 2L;
	static final Address TEST_OLD_ADDRESS_MODEL;
	static final User TEST_OLD_USER_MODEL;
	
	static {
 		TRAINEE_DTO = new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, "1st training");
 		TRAINEE_LIST = new ArrayList<>();
 		TRAINEE_LIST.add(TRAINEE_DTO);
 		TRAINEE_DTOS = new TraineeDtos(TRAINEE_LIST);
 		TEST_OLD_ADDRESS_MODEL = new Address(TEST_ADDRESS_ID, TEST_OLD_POST_CODE, null, null, null, null, TEST_OLD_ADDRESS, false, false, null);
 		TEST_OLD_USER_MODEL = new User(TEST_USER_ID, TEST_NAME, TEST_OLD_EMAIL, TEST_OLD_PHONE_NO);
	}
	
	@Mock
	TraineeManager manager; 
	
	@Mock
	AddressManager addressManager;
	
	@Mock 
	UserManager userManager;
	
	@Before
	public void setUp() {
		service = new TraineeService();
		service.setTraineeManager(manager);
		service.setAddressManager(addressManager);
		service.setUserManager(userManager);
		when(manager.getAllTrainees()).thenReturn(TRAINEE_LIST);
		when(addressManager.getAddressByAddressId(TEST_ADDRESS_ID)).thenReturn(TEST_OLD_ADDRESS_MODEL);
		when(userManager.getUserByUserId(TEST_USER_ID)).thenReturn(TEST_OLD_USER_MODEL);
		when(userManager.deleteTrainee(TEST_USER_ID, TEST_ADDRESS_ID)).thenReturn(true);
	}

	@Test
	public void testGetAllTraineese() {
		TraineeDtos trainee = service.getAllTrainees();
		assertNotNull("TraineeDtos is empty", trainee);
		assertNotNull("TraineeDtos traineeList is empty", trainee.getTrainees());
		assertTrue("TraineeDtos traineeList is empty", trainee.getTrainees().size() == 1);
		TraineeDto resultTrainee = trainee.getTrainees().get(0);
		assertEquals("Name doesn't match", TRAINEE_DTO.getName(), resultTrainee.getName());
		assertEquals("Post code doesn't match", TRAINEE_DTO.getPostCode(), resultTrainee.getPostCode());
		assertEquals("Address doesn't match", TRAINEE_DTO.getAddress(), resultTrainee.getAddress());
		assertEquals("Phone doesn't match", TRAINEE_DTO.getPhone(), resultTrainee.getPhone());
		assertEquals("Email doesn't match", TRAINEE_DTO.getEmail(), resultTrainee.getEmail());
		assertEquals("User id doesn't match", TRAINEE_DTO.getUserId(), resultTrainee.getUserId());
		assertEquals("The content of the completed list is not correct", TRAINEE_DTO.getCompletedTrainings(), resultTrainee.getCompletedTrainings());
		assertEquals("Address id doesn't match", TRAINEE_DTO.getAddressId(), resultTrainee.getAddressId());
	}
	
	@Test
	public void testEditTraineeWithValidDto() throws ValidationException {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullDto() throws ValidationException {
		service.editTrainee(null);
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullName() throws ValidationException  {
		service.editTrainee(new TraineeDto(null, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullPostCode() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, null, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullAddress() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, null, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullPhoneNo() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, null, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullEmail() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, null, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullUserId() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, null, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithNullAddressId() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, null, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithEmptyName() throws ValidationException  {
		service.editTrainee(new TraineeDto("", TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithEmptyPostCode() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, "", TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithEmptyAddress() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, "", TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithEmptyPhoneNo() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, "", TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithEmptyEmail() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, "", TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithInvalidUserId() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, -1L, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithInvalidAddressId() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, -1L, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithTooLongName() throws ValidationException  {
		service.editTrainee(new TraineeDto(PARAMETER_STRING_SIZE_MORE_THEN_100, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithTooLongPostCode() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, PARAMETER_STRING_SIZE_MORE_THEN_100, TEST_ADDRESS, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithTooLongAddress() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_100, TEST_PHONE_NO, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithTooLongPhoneNo() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, PARAMETER_STRING_SIZE_MORE_THEN_100, TEST_EMAIL, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testEditTraineeWithTooLongEmail() throws ValidationException  {
		service.editTrainee(new TraineeDto(TEST_NAME, TEST_POST_CODE, TEST_ADDRESS, TEST_PHONE_NO, PARAMETER_STRING_SIZE_MORE_THEN_100, TEST_USER_ID, TEST_ADDRESS_ID, null));
	}
	
	// *************** delete method ***************
	
	@Test(expected = ValidationException.class)
	public void testDeleteTraineeWithNullUserId() throws ValidationException {
		service.deleteTrainee(null, TEST_ADDRESS_ID);
	}
	
	@Test(expected = ValidationException.class)
	public void testDeleteTraineeWithInvalidUserId() throws ValidationException {
		service.deleteTrainee(-1L, TEST_ADDRESS_ID);
	}
	
	@Test(expected = ValidationException.class)
	public void testDeleteTraineeWithNullAddressId() throws ValidationException {
		service.deleteTrainee(TEST_USER_ID, null);
	}
	
	@Test(expected = ValidationException.class)
	public void testDeleteTraineeWithInvalidAddressId() throws ValidationException {
		service.deleteTrainee(TEST_USER_ID, -1L);
	}
	
	@Test
	public void testDeleteTraineeWithValidUserId() throws ValidationException {
		BooleanResponse result = service.deleteTrainee(TEST_USER_ID, TEST_ADDRESS_ID);
		assertTrue("Delete should've been successful", result.getPrimitiveBooleanValue());
	}
	
}
