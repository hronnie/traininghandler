package com.codeproj.traininghandler.showEligibles.manager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;

@RunWith(MockitoJUnitRunner.class)
public class ShowTraineesEligibleForTrainingManagerTest {
	
	TraineesEligibleForTrainingDto REF_ELIGIBLE_DTO = null;
	
	@Mock
	ShowTraineesEligibleForTrainingDAO dao = null;
	
	
	public Long EXISTING_TRAINING_TYPE_ID = 2L;
	
	ShowTraineesEligibleForTrainingManager manager = null;

	@Before
	public void setUp() throws Exception {
		manager = new ShowTraineesEligibleForTrainingManager();
		manager.setShowTraineesEligibleForTrainingDAO(dao);
		UserDto hasEmailUser = new UserDto("Test Name 1", "1111111111111", "email1@email.com", 1L);
		UserDto onlyPhoneUser = new UserDto("Test Name 1", "2222222222", "2222222222@nincs.com", 2L);
		
		List<UserDto> hasEmailUserList = new ArrayList<>();
		hasEmailUserList.add(hasEmailUser);
		List<UserDto> onlyPhoneUserList = new ArrayList<>();
		onlyPhoneUserList.add(onlyPhoneUser);
		
		REF_ELIGIBLE_DTO = new TraineesEligibleForTrainingDto(hasEmailUserList, onlyPhoneUserList);
		
		when(dao.getEligibleTraineesByTrainingTypeId(EXISTING_TRAINING_TYPE_ID)).thenReturn(REF_ELIGIBLE_DTO);
	}

	@Test
	public void testGetEligibleTraineesByTrainingTypeId() throws ValidationException {
		TraineesEligibleForTrainingDto result = manager.getEligibleTraineesByTrainingTypeId(EXISTING_TRAINING_TYPE_ID);
		assertEquals("Service result is not as expected", REF_ELIGIBLE_DTO, result);
	}

}
