package com.codeproj.traininghandler.completedTraining.manager;

import static org.junit.Assert.*;
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
	
	public static final Long VALID_COMPLETED_USER_TRAINING_ID_EXIST = 7L;
	public static final Long VALID_USER_ID_EXIST = 1L;
	public static final Long VALID_TRAINING_TYPE_ID_EXIST = 2L;
	public static final String VALID_TRAINING_TYPE_NAME = "1-es tanfolyam";
	
	public DateTime TRAINING_COMPL_DATE = new DateTime(2015, 1, 1, 0, 0);

	
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
		when(showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(TRAINING_TYPE_ID_ELIGIBLE, TRAINING_COMPL_DATE))
				.thenReturn(traineesEligibleForTrainingDto);
		boolean result = manager.isUserEligibleToAddTraining(new CompletedUserTrainingDto(USER_ELIGIBLE, TRAINING_TYPE_ID_ELIGIBLE, TRAINING_COMPL_DATE.toDate()));
		assertTrue("User sould be eligible", result);
		
		when(showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(TRAINING_TYPE_ID_NOT_ELIGIBLE, TRAINING_COMPL_DATE))
				.thenReturn(new TraineesEligibleForTrainingDto(new ArrayList<UserDto>(), new ArrayList<UserDto>()));
		result = manager.isUserEligibleToAddTraining(new CompletedUserTrainingDto(USER_NOT_ELIGIBLE, TRAINING_TYPE_ID_NOT_ELIGIBLE, TRAINING_COMPL_DATE.toDate()));
		assertFalse("User souldn't be eligible", result);
	}
	
	@Test
	public void testUpdateCompletedTraining() {
		when(completedTrainingDAO.update(new CompletedUserTrainingDto(VALID_COMPLETED_USER_TRAINING_ID_EXIST, VALID_USER_ID_EXIST, VALID_TRAINING_TYPE_ID_EXIST, VALID_DATE))).thenReturn(true);
		boolean result = manager.update(new CompletedUserTrainingDto(VALID_COMPLETED_USER_TRAINING_ID_EXIST, VALID_USER_ID_EXIST, VALID_TRAINING_TYPE_ID_EXIST, VALID_DATE));
		assertTrue("Update should be successful ", result);
	}
	
	@Test
	public void testDeleteCompletedTraining() {
		when(completedTrainingDAO.delete(VALID_COMPLETED_USER_TRAINING_ID_EXIST)).thenReturn(true);
		boolean result = manager.delete(VALID_COMPLETED_USER_TRAINING_ID_EXIST);
		assertTrue("Delete should be successful", result);
	}
	
	@Test
	public void testListByUserId() {
		List<CompletedUserTraining> mockComplTrList = new ArrayList<>();
		mockComplTrList.add(new CompletedUserTraining(1L, new TrainingType(VALID_TRAINING_TYPE_ID_EXIST), VALID_DATE, new User(VALID_USER_ID_EXIST)));
		when(completedTrainingDAO.listByUserId(VALID_USER_ID_EXIST)).thenReturn(mockComplTrList);
		
		List<CompletedUserTraining> result = manager.listByUserId(VALID_USER_ID_EXIST);
		assertNotNull("compl user training list shouldn't be null", result);
		assertTrue("compl user training list shouldn't be empty", result.size() > 0);
		CompletedUserTraining resultComplTr = result.get(0);
		assertEquals("User is not as expected", VALID_USER_ID_EXIST, resultComplTr.getUser().getUserId());
		assertEquals("TrainingType is not as expected", VALID_TRAINING_TYPE_ID_EXIST, resultComplTr.getTrainingType().getTrainingTypeId());
		assertEquals("User is not as expected", VALID_DATE, resultComplTr.getCompletedDate());
	}

	@Test
	public void testListViewableByUserId() {
		List<CompletedUserTrainingDto> mockComplTrList = new ArrayList<>();
		mockComplTrList.add(new CompletedUserTrainingDto(VALID_USER_ID_EXIST, VALID_TRAINING_TYPE_ID_EXIST, VALID_DATE, VALID_TRAINING_TYPE_NAME));
		when(completedTrainingDAO.listViewableByUserId(VALID_USER_ID_EXIST)).thenReturn(mockComplTrList);
		
		List<CompletedUserTrainingDto> result = manager.listViewableByUserId(VALID_USER_ID_EXIST);
		assertNotNull("compl user training list shouldn't be null", result);
		assertTrue("compl user training list shouldn't be empty", result.size() > 0);
		CompletedUserTrainingDto resultComplTr = result.get(0);
		assertEquals("User is not as expected", VALID_USER_ID_EXIST, resultComplTr.getUserId());
		assertEquals("TrainingType is not as expected", VALID_TRAINING_TYPE_ID_EXIST, resultComplTr.getTrainingTypeId());
		assertEquals("User is not as expected", VALID_DATE, resultComplTr.getCompletedDate());
		assertEquals("training type name is not as expected", VALID_TRAINING_TYPE_NAME, resultComplTr.getTrainingTypeName());
	}
}
