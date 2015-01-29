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
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.model.Address;
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
	
	@Before
	public void setUp() throws Exception {
		manager = new CompletedTrainingManager();
		manager.setCompletedTrainingDAO(completedTrainingDAO);
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
}
