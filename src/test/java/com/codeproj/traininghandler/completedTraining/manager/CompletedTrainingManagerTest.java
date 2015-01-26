package com.codeproj.traininghandler.completedTraining.manager;

import static org.junit.Assert.assertEquals;
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
	public static CompletedUserTrainingDto complTrDto;
	public static CompletedUserTraining complTr;
	public static final Date VALID_DATE;
	public static Long result_ref;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DATE = dt.toDate();
		complTrDto = new CompletedUserTrainingDto(1L, 1L, VALID_DATE);
		complTr = new CompletedUserTraining(null, new TrainingType(1L), VALID_DATE, new User(1L));
		result_ref = 1L;
	}
	
	@Mock
	public CompletedTrainingDAO completedTrainingDAO;
	
	@Before
	public void setUp() throws Exception {
		manager = new CompletedTrainingManager();
		manager.setCompletedTrainingDAO(completedTrainingDAO);
		when(completedTrainingDAO.create(complTr)).thenReturn(result_ref);
	}
	
	@Test
	public void testCreateCompletedTrainingList() {
		Long result = manager.create(complTrDto);
		assertEquals("Failed to call DAO method", result_ref, result);
	}
}
