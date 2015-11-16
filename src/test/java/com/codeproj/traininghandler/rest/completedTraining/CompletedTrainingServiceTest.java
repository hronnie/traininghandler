package com.codeproj.traininghandler.rest.completedTraining;

import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULD_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.WRONG_VALIDATION_MESSAGE;
import static com.codeproj.traininghandler.util.Constants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdListResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class CompletedTrainingServiceTest {
	
	@InjectMocks
	public CompletedTrainingService service;
	public static final Long VALID_USER_ID = 5L;
	public static final Long VALID_TRAINING_TYPE_ID = 5L;
	public static final Date VALID_COMPLETED_DATE;
	public static final Date INVALID_FUTURE_COMPLETED_DATE;
	public static final Long INVALID_ID = -34L;
	public static final CompletedUserTrainingDto VALID_COMPLETED_TRAINING;
	public static final CompletedUserTrainingDto VALID_COMPLETED_TRAINING_DONT_EXIST;
	public static final List<CompletedUserTrainingDto> VALID_COMPLETED_TRAINING_LIST;
	
	public static final Long USER_ID_EXIST = 10L;
	public static final Long TRAINING_TYPE_ID_EXIST = 10L;
	
	public static final Long USER_ID_NOT_ELIGIBLE = 15L;
	public static final Long TRAINING_TYPE_ID_NOT_ELIGIBLE = 15L;
	
	public DateTime TRAINING_COMPL_DATE = new DateTime(2015, 1, 1, 0, 0);
	
	@Mock
	public CompletedTrainingManager manager;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_COMPLETED_DATE = dt.toDate();
		DateTime dt_future = new DateTime(2100, 3, 26, 12, 0, 0, 0);
		INVALID_FUTURE_COMPLETED_DATE = dt_future.toDate();
		VALID_COMPLETED_TRAINING = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		VALID_COMPLETED_TRAINING_DONT_EXIST = new CompletedUserTrainingDto(33L, 11L, VALID_COMPLETED_DATE);
		VALID_COMPLETED_TRAINING_LIST = new ArrayList<>();
		VALID_COMPLETED_TRAINING_LIST.add(VALID_COMPLETED_TRAINING);
	}
	
	@Before
	public void setUp() {
		service = new CompletedTrainingService();
		service.setCompletedTrainingManager(manager);
		List<Long> complServResult = new ArrayList<>();
		complServResult.add(1L);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(1L);
		when(manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING)).thenReturn(true);
		when(manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING_DONT_EXIST)).thenReturn(false);
	}
	
	// isCompletedTrainingExist
	
	@Test
	public void testIsCompletedTrainingExist() {
		BooleanResponse result1 = service.isCompletedTrainingExist(VALID_COMPLETED_TRAINING);
		BooleanResponse result2 = service.isCompletedTrainingExist(VALID_COMPLETED_TRAINING_DONT_EXIST);
		
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result1.getSuccess());
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result2.getSuccess());
		
		assertTrue("The completed training object exist", result1.getBooleanValue());
		assertFalse("The completed training object exist", result2.getBooleanValue());
	}
	
	// create

	@Test
	public void testCreateComplatedTrainingServiceWithValidObject() {
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(5L);
		when(manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING)).thenReturn(false);
		when(manager.isUserEligibleToAddTraining(VALID_COMPLETED_TRAINING)).thenReturn(true);
		service.create(VALID_COMPLETED_TRAINING_LIST);
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithEmptyList() {
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithNullUserId() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(null, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_USER_ID, result.getMessage());
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithNullTrainingTypeId() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, null, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_ID, result.getMessage());
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithNullCompletedDate() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, null);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_COMPLETED_DATE, result.getMessage());
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithInvalidUserId() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(INVALID_ID, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithInvalidTrainingTypeId() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, INVALID_ID, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testCreateComplatedTrainingServiceWithInvalidCompletedDate() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, INVALID_FUTURE_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		GeneralIdListResponse result = service.create(complatedUserTrainingDtoList);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = Constants.VALIDATION_DATE_IN_THE_FUTURE_1 + INVALID_FUTURE_COMPLETED_DATE + Constants.VALIDATION_DATE_IN_THE_FUTURE_2;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	// createOne
	
	@Test
	public void testCreateOneAlreadyExist() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(USER_ID_EXIST, TRAINING_TYPE_ID_EXIST, VALID_COMPLETED_DATE);
		when(manager.isCompletedTrainingExist(complatedUserTrainingDto)).thenReturn(true);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(-1L);
		when(manager.isUserEligibleToAddTraining(VALID_COMPLETED_TRAINING)).thenReturn(true);
		GeneralIdResponse result = service.createOne(complatedUserTrainingDto);
		assertFalse("If completed training already exist then it should then it shouldn't be success", result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_USER_TRAINING_COMPL_ALREADY_EXISTS, result.getMessage());
	}
	
	@Test
	public void testCreateOneUserNotEligible() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(USER_ID_NOT_ELIGIBLE, TRAINING_TYPE_ID_NOT_ELIGIBLE, VALID_COMPLETED_DATE);
		when(manager.isCompletedTrainingExist(complatedUserTrainingDto)).thenReturn(false);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(-1L);
		when(manager.isUserEligibleToAddTraining(complatedUserTrainingDto)).thenReturn(false);
		GeneralIdResponse result = service.createOne(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MISSING_PREREQUISITE, result.getMessage());
	}
	
	@Test
	public void testCreateOneUserNotEligibleAndSkipPrereq() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(USER_ID_NOT_ELIGIBLE, TRAINING_TYPE_ID_NOT_ELIGIBLE, VALID_COMPLETED_DATE, true);
		when(manager.isCompletedTrainingExist(complatedUserTrainingDto)).thenReturn(false);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(5L);
		when(manager.isUserEligibleToAddTraining(complatedUserTrainingDto)).thenReturn(false);
		GeneralIdResponse result = service.createOne(complatedUserTrainingDto);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
	}
	
	@Test
	public void testCreateOneUserNotEligibleAndSkipPrereqAlreadyExist() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(USER_ID_NOT_ELIGIBLE, TRAINING_TYPE_ID_NOT_ELIGIBLE, VALID_COMPLETED_DATE);
		when(manager.isCompletedTrainingExist(complatedUserTrainingDto)).thenReturn(true);
		when(manager.isUserEligibleToAddTraining(complatedUserTrainingDto)).thenReturn(false);
		GeneralIdResponse result = service.createOne(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_USER_TRAINING_COMPL_ALREADY_EXISTS, result.getMessage());
	}
	
	// update
	
	@Test
	public void testUpdateWithInvalidInputs() {
		// null userId 
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(null, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		GeneralIdResponse result = service.update(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_USER_ID, result.getMessage());
		// null tt id
		complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, null, VALID_COMPLETED_DATE);
		result = service.update(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_ID, result.getMessage());
		// null compl date
		complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, null);
		result = service.update(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_COMPLETED_DATE, result.getMessage());

		// invalid userId 
		complatedUserTrainingDto = new CompletedUserTrainingDto(-1L, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		result = service.update(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
		// invalid tt id
		complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, -1L, VALID_COMPLETED_DATE);
		result = service.update(complatedUserTrainingDto);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testUpdateWithValidInputs() {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		when(manager.update(complatedUserTrainingDto)).thenReturn(5L);
		GeneralIdResponse result = service.update(complatedUserTrainingDto);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		
	}
	

}
