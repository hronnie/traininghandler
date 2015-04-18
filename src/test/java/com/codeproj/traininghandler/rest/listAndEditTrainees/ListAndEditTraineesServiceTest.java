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
	
	static {
 		TRAINEE_DTO = new TraineeDto("Test Name", "EC12 7LB", "12 Test Street, London", "2323-2323/23", "mail@example.com", 1L, 2L, "1st training");
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
		assertEquals("User id doesn't match", TRAINEE_DTO.getUserId(), resultTrainee.getUserId());
		assertEquals("The content of the completed list is not correct", TRAINEE_DTO.getCompletedTrainings(), resultTrainee.getCompletedTrainings());
		assertEquals("Address id doesn't match", TRAINEE_DTO.getAddressId(), resultTrainee.getAddressId());
	}
}
