package com.codeproj.traininghandler.rest.completedTraining;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.rest.common.GeneralIdListResponse;

@RunWith(MockitoJUnitRunner.class)
public class CompletedTrainingServiceTest {
	
	public CompletedTrainingService service;
	public static final Long VALID_USER_ID = 5L;
	public static final Long VALID_TRAINING_TYPE_ID = 5L;
	public static final Date VALID_COMPLETED_DATE;
	public static final Date INVALID_FUTURE_COMPLETED_DATE;
	public static final Long INVALID_ID = -34L;
	public static final CompletedUserTrainingDto VALID_COMPLETED_TRAINING;
	public static final List<CompletedUserTrainingDto> VALID_COMPLETED_TRAINING_LIST;
	
	@Mock
	public CompletedTrainingManager manager;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_COMPLETED_DATE = dt.toDate();
		DateTime dt_future = new DateTime(2100, 3, 26, 12, 0, 0, 0);
		INVALID_FUTURE_COMPLETED_DATE = dt_future.toDate();
		VALID_COMPLETED_TRAINING = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		VALID_COMPLETED_TRAINING_LIST = new ArrayList<>();
		VALID_COMPLETED_TRAINING_LIST.add(VALID_COMPLETED_TRAINING);
	}
	
	@Before
	public void setUp() {
		service = new CompletedTrainingService();
		service.setCompletedTrainingManager(manager);
		List<Long> complServResult = new ArrayList<>();
		complServResult.add(1L);
		when(manager.create(VALID_COMPLETED_TRAINING_LIST)).thenReturn(complServResult);
	}

	@Test
	public void testCreateComplatedTrainingServiceWithValidObject() throws ValidationException {
		service.create(VALID_COMPLETED_TRAINING_LIST);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithNullObject() throws ValidationException {
		service.create(null);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithEmptyList() throws ValidationException {
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		service.create(complatedUserTrainingDtoList);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithNullUserId() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(null, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		service.create(complatedUserTrainingDtoList);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithNullTrainingTypeId() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, null, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		service.create(complatedUserTrainingDtoList);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithNullCompletedDate() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, null);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		service.create(complatedUserTrainingDtoList);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithInvalidUserId() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(INVALID_ID, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		service.create(complatedUserTrainingDtoList);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithInvalidTrainingTypeId() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, INVALID_ID, VALID_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		service.create(complatedUserTrainingDtoList);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateComplatedTrainingServiceWithInvalidCompletedDate() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, INVALID_FUTURE_COMPLETED_DATE);
		List<CompletedUserTrainingDto> complatedUserTrainingDtoList = new ArrayList<>();
		complatedUserTrainingDtoList.add(complatedUserTrainingDto);
		service.create(complatedUserTrainingDtoList);
	}

}
