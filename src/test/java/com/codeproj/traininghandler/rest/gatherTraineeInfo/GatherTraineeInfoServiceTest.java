package com.codeproj.traininghandler.rest.gatherTraineeInfo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.exceptions.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class GatherTraineeInfoServiceTest {
	
	public GatherTraineeInfoService service;
	
	@Before
	public void setUp() throws Exception {
		service = new GatherTraineeInfoService();
	}
	
	@Test
	public void test() {
		
	}

	@Test(expected = ValidationException.class)
	public void testGatherTraineeInfoWithEmptyInputObject() throws ValidationException {
		TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto();
		service.saveTraineePersonalAndTrainingInfo(traineeInfoDto);  
	}

}
