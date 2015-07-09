package com.codeproj.traininghandler.rest.showEligibles;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
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
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws DatabaseEntityNotFoundException, ValidationException {
		service = new ShowTraineesEligibleForTrainingService();
		
		service.setTrainingTypeService(trainingTypeService);
		service.setShowTraineesEligibleForTrainingManager(manager);
		
		when(trainingTypeService.getTrainingTypeById(NOT_EXISTING_TRAINING_TYPE_ID)).thenThrow(DatabaseEntityNotFoundException.class);

		UserDto hasEmailUser = new UserDto("Test Name 1", "1111111111111", "email1@email.com", 1L);
		UserDto onlyPhoneUser = new UserDto("Test Name 1", "2222222222", Constants.EXCEL_TRAINING_MISSING_EMAIL, 2L);
		
		List<UserDto> hasEmailUserList = new ArrayList<>();
		hasEmailUserList.add(hasEmailUser);
		List<UserDto> onlyPhoneUserList = new ArrayList<>();
		onlyPhoneUserList.add(onlyPhoneUser);
		
		REF_ELIGIBLE_DTO = new TraineesEligibleForTrainingDto(hasEmailUserList, onlyPhoneUserList);
		when(manager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(EXISTING_TRAINING_TYPE_ID, TRAINING_COMPL_DATE)).thenReturn(REF_ELIGIBLE_DTO);
	}

	@Test(expected = ValidationException.class)
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateTrTypeDoesntExist() throws ValidationException {
		service.getEligibleTraineesByTrainingTypeIdAndComplDate(NOT_EXISTING_TRAINING_TYPE_ID, TRAINING_COMPL_DATE_STR);
	}

	@Test(expected = ValidationException.class)
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateTrTypeIdIsNull() throws ValidationException {
		service.getEligibleTraineesByTrainingTypeIdAndComplDate(null, TRAINING_COMPL_DATE_STR);
	}
	
	@Test(expected = ValidationException.class)
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateComplDateIsNull() throws ValidationException {
		service.getEligibleTraineesByTrainingTypeIdAndComplDate(EXISTING_TRAINING_TYPE_ID, null);
	}
	
	@Test(expected = ValidationException.class)
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateComplDateIsInInvalidFormat1() throws ValidationException {
		service.getEligibleTraineesByTrainingTypeIdAndComplDate(EXISTING_TRAINING_TYPE_ID, "notvaliddate");
	}
	
	@Test(expected = ValidationException.class)
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateComplDateIsInInvalidFormat2() throws ValidationException {
		service.getEligibleTraineesByTrainingTypeIdAndComplDate(EXISTING_TRAINING_TYPE_ID, "2015-99-99");
	}
	
	@Test(expected = ValidationException.class)
	public void testGetEligibleTraineesByTrainingTypeIdAndComplDateComplDateIsInInvalidFormat3() throws ValidationException {
		service.getEligibleTraineesByTrainingTypeIdAndComplDate(EXISTING_TRAINING_TYPE_ID, "01-2012-99");
	}
	
	@Test
	public void testGetEligibleTraineesByTrainingTypeId() throws ValidationException {
		TraineesEligibleForTrainingDto result = service.getEligibleTraineesByTrainingTypeIdAndComplDate(EXISTING_TRAINING_TYPE_ID, TRAINING_COMPL_DATE_STR);
		assertEquals("Service result is not as expected", REF_ELIGIBLE_DTO, result);
	}

}
