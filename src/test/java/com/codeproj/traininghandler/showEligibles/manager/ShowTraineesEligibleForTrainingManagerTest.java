package com.codeproj.traininghandler.showEligibles.manager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.User;
import com.codeproj.traininghandler.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class ShowTraineesEligibleForTrainingManagerTest {
	
	@Mock
	ShowTraineesEligibleForTrainingDAO dao;
	
	TraineesEligibleForTrainingDto REF_ELIGIBLE_DTO = null;
	List<TrainingTypePrerequisite> REF_TRAINING_TYPE_PREREQ = new ArrayList<>();
	List<TrainingPrerequisite> REF_TRAINING_PREREQ = new ArrayList<>();
	List<User> REF_USER_LIST = new ArrayList<>();
	User HAS_EMAIL_USER;
	User PHONE_EMAIL_USER;
	User COMPLETED_USER;
	List<Long> REF_COMPL_USER_LIST = new ArrayList<>();;
	
	public static final String VALID_EMAIL = "email1@email.com";
	public static final String FAKE_EMAIL = Constants.EXCEL_TRAINING_MISSING_EMAIL;
	public static final String PHONE_NO = "2222222222";
	public static final String TEST_NAME1 = "Test Name 1";
	public static final String TEST_NAME2 = "Test Name 2";
	
	public Long EXISTING_TRAINING_TYPE_ID = 2L;
	public DateTime TRAINING_COMPL_DATE = new DateTime(2015, 1, 1, 0, 0);
	
	ShowTraineesEligibleForTrainingManager manager = null;
	
	@Mock
	CompletedTrainingManager completedTrainingManager;
	
	@Before
	public void setUp() throws Exception {
		manager = new ShowTraineesEligibleForTrainingManager();
		manager.setShowTraineesEligibleForTrainingDAO(dao);
		manager.setCompletedTrainingManager(completedTrainingManager);
		
		TrainingPrerequisite firstTrPrereq = new TrainingPrerequisite(1L, 1L, 3L, 3);
		TrainingPrerequisite secondTrPrereq = new TrainingPrerequisite(2L, 1L, 4L, 6);
		
		REF_TRAINING_PREREQ.add(firstTrPrereq);
		REF_TRAINING_PREREQ.add(secondTrPrereq);
		
		when(dao.getPrerequisitesByTrainingTypeId(EXISTING_TRAINING_TYPE_ID)).thenReturn(REF_TRAINING_PREREQ);
		
		TrainingTypePrerequisite firstTrTypePrereq = new TrainingTypePrerequisite(3L, TRAINING_COMPL_DATE.minusMonths(3).toDate());
		TrainingTypePrerequisite secondTrTypePrereq = new TrainingTypePrerequisite(4L, TRAINING_COMPL_DATE.minusMonths(6).toDate());
		
		REF_TRAINING_TYPE_PREREQ.add(firstTrTypePrereq);
		REF_TRAINING_TYPE_PREREQ.add(secondTrTypePrereq);
		
		HAS_EMAIL_USER = new User(1L, TEST_NAME1, VALID_EMAIL, PHONE_NO);
		PHONE_EMAIL_USER = new User(2L, TEST_NAME1, FAKE_EMAIL, PHONE_NO);
		COMPLETED_USER = new User(3L, TEST_NAME1, FAKE_EMAIL, PHONE_NO);
		REF_USER_LIST.add(HAS_EMAIL_USER);
		REF_USER_LIST.add(PHONE_EMAIL_USER);
		REF_USER_LIST.add(COMPLETED_USER);
		
		REF_COMPL_USER_LIST.add(3L);
		
		when(dao.getEligibleTrainees(REF_TRAINING_TYPE_PREREQ)).thenReturn(REF_USER_LIST);
		when(completedTrainingManager.getUsersWhoCompletedTrainingType(EXISTING_TRAINING_TYPE_ID)).thenReturn(REF_COMPL_USER_LIST);
		
		UserDto hasEmailUser = new UserDto(TEST_NAME1, PHONE_NO, VALID_EMAIL, null);
		hasEmailUser.setUserId(1L);
		UserDto onlyPhoneUser = new UserDto(TEST_NAME1, PHONE_NO, FAKE_EMAIL, null);
		onlyPhoneUser.setUserId(2L);
		
		List<UserDto> hasEmailUserList = new ArrayList<>();
		hasEmailUserList.add(hasEmailUser);
		List<UserDto> onlyPhoneUserList = new ArrayList<>();
		onlyPhoneUserList.add(onlyPhoneUser);
		
		REF_ELIGIBLE_DTO = new TraineesEligibleForTrainingDto(hasEmailUserList, onlyPhoneUserList);
	}

	@Test
	public void testGetEligibleTraineesByTrainingTypeId() throws ValidationException {
		TraineesEligibleForTrainingDto result = manager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(EXISTING_TRAINING_TYPE_ID, TRAINING_COMPL_DATE);
		assertEquals("Service result is not as expected", REF_ELIGIBLE_DTO, result);
	}

	@Test
	public void testGetPrerequisitesByTrainingId() {
		when(dao.getPrerequisitesByTrainingTypeId(3L)).thenReturn(REF_TRAINING_PREREQ);
		List<TrainingPrerequisite> prerequisites = manager.getPrerequisitesByTrainingTypeId(3L);
		assertNotNull("Training prereq shouldn't be null", prerequisites);
		assertEquals("The number of prerequisites should be 2", 2, prerequisites.size());
		assertEquals("The first prerequisite doesn't match", REF_TRAINING_PREREQ.get(0), prerequisites.get(0));
		assertEquals("The second prerequisite doesn't match", REF_TRAINING_PREREQ.get(1), prerequisites.get(1));
	}

}
