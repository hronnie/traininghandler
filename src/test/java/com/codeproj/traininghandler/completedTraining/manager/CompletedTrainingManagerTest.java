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
	public static List<CompletedUserTrainingDto> complTrDto;
	public static List<CompletedUserTraining> complTr;
	public static final Date VALID_DATE;
	public static List<Long> result_ref;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_DATE = dt.toDate();
		complTrDto = new ArrayList<>();
		complTr = new ArrayList<>();
		CompletedUserTrainingDto first = new CompletedUserTrainingDto(1L, 1L, VALID_DATE);
		complTrDto.add(first);
		CompletedUserTraining firstModel = new CompletedUserTraining(null, new TrainingType(1L), VALID_DATE, new User(1L));
		complTr.add(firstModel);
		result_ref = new ArrayList<>();
		result_ref.add(1L);
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
		List<Long> result = manager.create(complTrDto);
		assertEquals("Failed to call DAO method", result_ref, result);
	}
}
