package com.codeproj.traininghandler.manager.listAndEditTrainees;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dao.ListAndEditTraineesDAO;
import com.codeproj.traininghandler.dto.TraineeDto;

@RunWith(MockitoJUnitRunner.class)
public class ListAndEditTraineesManagerTest {
	
	ListAndEditTraineesManager manager;
	static final TraineeDto TRAINEE_DTO;
	static final List<TraineeDto> TRAINEE_LIST;

	static {
 		TRAINEE_DTO = new TraineeDto("Test Name", "EC12 7LB", "12 Test Street, London", "2323-2323/23", "mail@example.com", 1L, 2L, "1st training");
 		TRAINEE_LIST = new ArrayList<>();
 		TRAINEE_LIST.add(TRAINEE_DTO);
	}
	
	@Mock
	public ListAndEditTraineesDAO listAndEditTraineesDAO;
	
	@Before
	public void setUp() {
		manager = new ListAndEditTraineesManager();
		manager.setListAndEditTraineesDAO(listAndEditTraineesDAO);
		when(listAndEditTraineesDAO.getAllTrainees()).thenReturn(TRAINEE_LIST);
	}
	
	@Test
	public void testGetAllTraineese() {
		List<TraineeDto> traineeList = manager.getAllTrainees();
		assertNotNull("TraineeList is empty", traineeList);
		assertTrue("traineeList is empty", traineeList.size() == 1);
		TraineeDto resultTrainee = traineeList.get(0);
		assertEquals("Name doesn't match", TRAINEE_DTO.getName(), resultTrainee.getName());
		assertEquals("Post code doesn't match", TRAINEE_DTO.getPostCode(), resultTrainee.getPostCode());
		assertEquals("Address doesn't match", TRAINEE_DTO.getAddress(), resultTrainee.getAddress());
		assertEquals("Phone doesn't match", TRAINEE_DTO.getPhone(), resultTrainee.getPhone());
		assertEquals("Email doesn't match", TRAINEE_DTO.getEmail(), resultTrainee.getEmail());
		assertEquals("User id doesn't match", TRAINEE_DTO.getUserId(), resultTrainee.getUserId());
		assertEquals("Address id doesn't match", TRAINEE_DTO.getAddressId(), resultTrainee.getAddressId());
		assertEquals("The content of the completed list is not correct", TRAINEE_DTO.getCompletedTrainings(), resultTrainee.getCompletedTrainings());
		assertNotNull("Completed training list shouldn't be null", resultTrainee.getCompletedTrainings());
		assertFalse("Completed training list shouldn't be empty", resultTrainee.getCompletedTrainings().isEmpty());
	}
}
