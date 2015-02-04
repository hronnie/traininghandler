package com.codeproj.traininghandler.showEligibles.manager;

import static org.junit.Assert.assertEquals;
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
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;

@RunWith(MockitoJUnitRunner.class)
public class ShowTraineesEligibleForTrainingManagerTest {
	
	
	@Mock
	ShowTraineesEligibleForTrainingDAO dao = null;
	
	TraineesEligibleForTrainingDto REF_ELIGIBLE_DTO = null;
	List<TrainingTypePrerequisite> REF_TRAINING_TYPE_PREREQ = new ArrayList<>();
	List<TrainingPrerequisite> REF_TRAINING_PREREQ = new ArrayList<>();
	List<User> REF_USER_LIST = new ArrayList<>();
	User HAS_EMAIL_USER;
	User PHONE_EMAIL_USER;
	
	public static final String VALID_EMAIL = "email1@email.com";
	public static final String PHONE_EMAIL = "2222222222@nincs.com";
	public static final String PHONE_NO = "2222222222";
	public static final String TEST_NAME1 = "Test Name 1";
	public static final String TEST_NAME2 = "Test Name 2";
	
	public Long EXISTING_TRAINING_TYPE_ID = 2L;
	
	ShowTraineesEligibleForTrainingManager manager = null;
	

	@Before
	public void setUp() throws Exception {
		manager = new ShowTraineesEligibleForTrainingManager();
		manager.setShowTraineesEligibleForTrainingDAO(dao);
		
		TrainingPrerequisite firstTrPrereq = new TrainingPrerequisite(1L, new TrainingType(1L), new TrainingType(3L), 3);
		TrainingPrerequisite secondTrPrereq = new TrainingPrerequisite(2L, new TrainingType(1L), new TrainingType(4L), 6);
		
		REF_TRAINING_PREREQ.add(firstTrPrereq);
		REF_TRAINING_PREREQ.add(secondTrPrereq);
		
		when(dao.getPrerequisitesByTrainingId(EXISTING_TRAINING_TYPE_ID)).thenReturn(REF_TRAINING_PREREQ);
		
		DateTime dt1 = new DateTime();
		DateTime dt2 = new DateTime();
		
		TrainingTypePrerequisite firstTrTypePrereq = new TrainingTypePrerequisite(3L, dt1.minusMonths(3).toDate());
		TrainingTypePrerequisite secondTrTypePrereq = new TrainingTypePrerequisite(4L, dt2.minusMonths(6).toDate());
		
		REF_TRAINING_TYPE_PREREQ.add(firstTrTypePrereq);
		REF_TRAINING_TYPE_PREREQ.add(secondTrTypePrereq);
		
		HAS_EMAIL_USER = new User(1L, TEST_NAME1, VALID_EMAIL, null);
		PHONE_EMAIL_USER = new User(2L, TEST_NAME1, PHONE_EMAIL, PHONE_NO);
		REF_USER_LIST.add(HAS_EMAIL_USER);
		REF_USER_LIST.add(PHONE_EMAIL_USER);
		
		when(dao.getEligibleTrainees(REF_TRAINING_TYPE_PREREQ)).thenReturn(REF_USER_LIST);

		
		UserDto hasEmailUser = new UserDto(TEST_NAME1, null, VALID_EMAIL, null);
		UserDto onlyPhoneUser = new UserDto(TEST_NAME1, PHONE_NO, PHONE_EMAIL, null);
		
		List<UserDto> hasEmailUserList = new ArrayList<>();
		hasEmailUserList.add(hasEmailUser);
		List<UserDto> onlyPhoneUserList = new ArrayList<>();
		onlyPhoneUserList.add(onlyPhoneUser);
		
		REF_ELIGIBLE_DTO = new TraineesEligibleForTrainingDto(hasEmailUserList, onlyPhoneUserList);
	}

	@Test
	public void testGetEligibleTraineesByTrainingTypeId() throws ValidationException {
		TraineesEligibleForTrainingDto result = manager.getEligibleTraineesByTrainingTypeId(EXISTING_TRAINING_TYPE_ID);
		assertEquals("Service result is not as expected", REF_ELIGIBLE_DTO, result);
	}

}
