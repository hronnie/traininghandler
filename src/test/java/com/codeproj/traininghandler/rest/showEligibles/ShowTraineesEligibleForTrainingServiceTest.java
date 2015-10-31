package com.codeproj.traininghandler.rest.showEligibles;

import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.WRONG_VALIDATION_MESSAGE;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_MANDATORY_PARAMETER;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST;
import static com.codeproj.traininghandler.util.Constants.VALIDATION_PARAMETER_TRAINING_TYPE_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.rest.trainingtype.TrainingTypeService;
import com.codeproj.traininghandler.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ShowTraineesEligibleForTrainingServiceTest {
	
	@Mock
	TrainingTypeService trainingTypeService;
	
	@Mock
	ShowTraineesEligibleForTrainingManager manager;
	
	TraineesEligibleForTrainingDto REF_ELIGIBLE_DTO = null;
	
	ShowTraineesEligibleForTrainingService service = null;
	public Long EXISTING_TRAINING_TYPE_ID = 2L;
	public Long NOT_EXISTING_TRAINING_TYPE_ID = -1L;
	public String TRAINING_COMPL_DATE_STR = "2015-01-01";
	public DateTime TRAINING_COMPL_DATE = new DateTime(2015, 1, 1, 0, 0);
	
	@Before
	public void setUp() throws DatabaseEntityNotFoundException, ValidationException {
		service = new ShowTraineesEligibleForTrainingService();
		
		service.setTrainingTypeService(trainingTypeService);
		service.setShowTraineesEligibleForTrainingManager(manager);
		
		when(trainingTypeService.getTrainingTypeById(NOT_EXISTING_TRAINING_TYPE_ID)).thenReturn(new TrainingTypeDto(VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST));

		UserDto hasEmailUser = new UserDto("Test Name 1", "1111111111111", "email1@email.com", 1L);
		UserDto onlyPhoneUser = new UserDto("Test Name 1", "2222222222", Constants.EXCEL_TRAINING_MISSING_EMAIL, 2L);
		
		List<UserDto> hasEmailUserList = new ArrayList<>();
		hasEmailUserList.add(hasEmailUser);
		List<UserDto> onlyPhoneUserList = new ArrayList<>();
		onlyPhoneUserList.add(onlyPhoneUser);
		
		REF_ELIGIBLE_DTO = new TraineesEligibleForTrainingDto(hasEmailUserList, onlyPhoneUserList);
		DateTime complDateTime = new DateTime().withTimeAtStartOfDay();
		when(manager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(EXISTING_TRAINING_TYPE_ID, complDateTime)).thenReturn(REF_ELIGIBLE_DTO);
	}

	@Test
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateTrTypeDoesntExist() throws ValidationException {
		TraineesEligibleForTrainingDto result = service.getEligibleTraineesByTrainingTypeId(NOT_EXISTING_TRAINING_TYPE_ID);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST, result.getMessage());
	}

	@Test
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateTrTypeIdIsNull() throws ValidationException {
		TraineesEligibleForTrainingDto result = service.getEligibleTraineesByTrainingTypeId(null);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_ID, result.getMessage());
	}
		
	@Test
	public void testGetEligibleTraineesByTrainingTypeId() {
		TrainingTypeDto trainingTypeDto = new TrainingTypeDto();
		trainingTypeDto.setSuccess(true);
		when(trainingTypeService.getTrainingTypeById(EXISTING_TRAINING_TYPE_ID)).thenReturn(trainingTypeDto);
		TraineesEligibleForTrainingDto result = service.getEligibleTraineesByTrainingTypeId(EXISTING_TRAINING_TYPE_ID);
		assertTrue(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
	}

}
