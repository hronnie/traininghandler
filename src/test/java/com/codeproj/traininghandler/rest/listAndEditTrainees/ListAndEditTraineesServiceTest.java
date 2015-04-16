package com.codeproj.traininghandler.rest.listAndEditTrainees;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.dto.TraineeDtos;
import com.codeproj.traininghandler.manager.listAndEditTrainees.ListAndEditTraineesManager;

@RunWith(MockitoJUnitRunner.class)
public class ListAndEditTraineesServiceTest {
	
	ListAndEditTraineesService service;
	static final TraineeDtos TRAINEE_DTOS;
	static final TraineeDto TRAINEE_DTO;
	static final List<TraineeDto> TRAINEE_LIST;
	static final List<String> COMPLETED_TRAINING_LIST;
	static final String TEST_COMPLETED_TRAINING = "1st training";
	
	static {
		TRAINEE_DTO = new TraineeDto("Test name", "RG1 444", "18 Test road, London", "09034-34343434", "test@example.com");
		COMPLETED_TRAINING_LIST = new ArrayList<>();
		COMPLETED_TRAINING_LIST.add(TEST_COMPLETED_TRAINING);
		TRAINEE_DTO.setCompletedTrainings(COMPLETED_TRAINING_LIST);
		TRAINEE_LIST = new ArrayList<>();
		TRAINEE_LIST.add(TRAINEE_DTO);
		TRAINEE_DTOS = new TraineeDtos(TRAINEE_LIST);
	}
	
	@Mock
	ListAndEditTraineesManager manager; 
	
	@Before
	public void setUp() {
		service = new ListAndEditTraineesService();
		service.setListAndEditTraineesManager(manager);
		when(manager.getAllTrainees()).thenReturn(TRAINEE_LIST);
	}

	@Test
	public void testGetAllTraineese() {
		TraineeDtos trainee = service.getAllTrainees();
		assertNotNull("TraineeDtos is empty", trainee);
		assertNotNull("TraineeDtos traineeList is empty", trainee.getTrainees());
		assertTrue("TraineeDtos traineeList is empty", trainee.getTrainees().size() == 1);
		TraineeDto resultTrainee = trainee.getTrainees().get(0);
		assertEquals("Name doesn't match", TRAINEE_DTO.getName(), resultTrainee.getName());
		assertEquals("Post code doesn't match", TRAINEE_DTO.getPostCode(), resultTrainee.getPostCode());
		assertEquals("Address doesn't match", TRAINEE_DTO.getAddress(), resultTrainee.getAddress());
		assertEquals("Phone doesn't match", TRAINEE_DTO.getPhone(), resultTrainee.getPhone());
		assertEquals("Email doesn't match", TRAINEE_DTO.getEmail(), resultTrainee.getEmail());
		assertNotNull("Completed training list shouldn't be null", resultTrainee.getCompletedTrainings());
		assertFalse("Completed training list shouldn't be empty", resultTrainee.getCompletedTrainings().isEmpty());
		assertTrue("Completed training list shouldn't be empty", resultTrainee.getCompletedTrainings().size() == 1);
		assertEquals("The content of the completed list is not correct", TEST_COMPLETED_TRAINING, resultTrainee.getCompletedTrainings().get(0));
	}
}
