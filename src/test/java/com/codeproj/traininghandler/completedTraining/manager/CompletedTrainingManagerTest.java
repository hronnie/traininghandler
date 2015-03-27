package com.codeproj.traininghandler.completedTraining.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.CompletedUserTraining;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;

@RunWith(MockitoJUnitRunner.class)
public class CompletedTrainingManagerTest {

	public CompletedTrainingManager manager;
	
	public static CompletedUserTrainingDto VALID_COMPLETED_TRAINING_DTO;
	public static CompletedUserTrainingDto INVALID_COMPLETED_TRAINING_DTO;
	public static CompletedUserTraining VALID_COMPL_TRAINING;
	public static CompletedUserTraining INVALID_COMPL_TRAINING;
	public static final Date VALID_DATE;
	public static Long RESULT_REFERENCE;
	
	public static final Long USER_ELIGIBLE = 10L;
	public static final Long TRAINING_TYPE_ID_ELIGIBLE = 10L;
	public static final Long USER_NOT_ELIGIBLE = 15L;
	public static final Long TRAINING_TYPE_ID_NOT_ELIGIBLE = 15L;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DATE = dt.toDate();
		VALID_COMPLETED_TRAINING_DTO = new CompletedUserTrainingDto(1L, 1L, VALID_DATE);
		INVALID_COMPLETED_TRAINING_DTO = new CompletedUserTrainingDto(5L, 5L, VALID_DATE);
		VALID_COMPL_TRAINING = new CompletedUserTraining(null, new TrainingType(1L), VALID_DATE, new User(1L));
		INVALID_COMPL_TRAINING = new CompletedUserTraining(null, new TrainingType(5L), VALID_DATE, new User(5L));
		RESULT_REFERENCE = 1L;
	}
	
	@Mock
	public CompletedTrainingDAO completedTrainingDAO;
	
	@Mock
	public ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;
	
	@Before
	public void setUp() throws Exception {
		manager = new CompletedTrainingManager();
		manager.setCompletedTrainingDAO(completedTrainingDAO);
		manager.setShowTraineesEligibleForTrainingManager(showTraineesEligibleForTrainingManager);
		when(completedTrainingDAO.create(VALID_COMPL_TRAINING)).thenReturn(RESULT_REFERENCE);
	}
	
	@Test
	public void testCreateCompletedTrainingList() {
		Long result = manager.create(VALID_COMPLETED_TRAINING_DTO);
		assertEquals("Failed to call DAO method", RESULT_REFERENCE, result);
	}
	
	@Test 
	public void testIsCompletedTrainingExist() {
		when(completedTrainingDAO.isCompletedTrainingExist(VALID_COMPL_TRAINING)).thenReturn(true);
		when(completedTrainingDAO.isCompletedTrainingExist(INVALID_COMPL_TRAINING)).thenReturn(false);
		assertTrue("Completed training should exist", manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING_DTO));
		assertFalse("Completed training should exist", manager.isCompletedTrainingExist(INVALID_COMPLETED_TRAINING_DTO));
	}
	
	@Test
	public void testGetCompletedListByTrainingTypeId() {
		List<CompletedUserTraining> userIds = new ArrayList<>();
		userIds.add(VALID_COMPL_TRAINING);
		List<Long> result_ref = new ArrayList<>();
		result_ref.add(1L);
		when(completedTrainingDAO.getCompletedListByTrainingTypeId(1L)).thenReturn(userIds);
		assertEquals("Wrong user id list", manager.getUsersWhoCompletedTrainingType(1L), result_ref);
	}
	
	@Test
	public void testIsUserEligibleToAddTraining() {
		List<UserDto> hasEmailUsers = new ArrayList<>();
		UserDto curUsr = new UserDto("anything", "anything", "anything", 3L);
		curUsr.setUserId(USER_ELIGIBLE);
		hasEmailUsers.add(curUsr);
		
		TraineesEligibleForTrainingDto traineesEligibleForTrainingDto = new TraineesEligibleForTrainingDto(hasEmailUsers, new ArrayList<UserDto>());
		when(showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeId(TRAINING_TYPE_ID_ELIGIBLE)).thenReturn(traineesEligibleForTrainingDto);
		boolean result = manager.isUserEligibleToAddTraining(new CompletedUserTrainingDto(USER_ELIGIBLE, TRAINING_TYPE_ID_ELIGIBLE, VALID_DATE));
		assertTrue("User sould be eligible", result);
		
		when(showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeId(TRAINING_TYPE_ID_NOT_ELIGIBLE)).thenReturn(new TraineesEligibleForTrainingDto(new ArrayList<UserDto>(), new ArrayList<UserDto>()));
		result = manager.isUserEligibleToAddTraining(new CompletedUserTrainingDto(USER_NOT_ELIGIBLE, TRAINING_TYPE_ID_NOT_ELIGIBLE, VALID_DATE));
		assertFalse("User souldn't be eligible", result);
	}
}
