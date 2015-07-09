package com.codeproj.traininghandler.rest.completedTraining;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RunWith(MockitoJUnitRunner.class)
public class CompletedTrainingServiceTest {
	
	@InjectMocks
	public CompletedTrainingService service;
	public static final Long VALID_USER_ID = 5L;
	public static final Long VALID_TRAINING_TYPE_ID = 5L;
	public static final Date VALID_COMPLETED_DATE;
	public static final Date INVALID_FUTURE_COMPLETED_DATE;
	public static final Long INVALID_ID = -34L;
	public static final CompletedUserTrainingDto VALID_COMPLETED_TRAINING;
	public static final CompletedUserTrainingDto VALID_COMPLETED_TRAINING_DONT_EXIST;
	public static final List<CompletedUserTrainingDto> VALID_COMPLETED_TRAINING_LIST;
	
	public static final Long USER_ID_EXIST = 10L;
	public static final Long TRAINING_TYPE_ID_EXIST = 10L;
	
	public static final Long USER_ID_NOT_ELIGIBLE = 15L;
	public static final Long TRAINING_TYPE_ID_NOT_ELIGIBLE = 15L;
	
	public DateTime TRAINING_COMPL_DATE = new DateTime(2015, 1, 1, 0, 0);
	
	@Mock
	public CompletedTrainingManager manager;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		VALID_COMPLETED_DATE = dt.toDate();
		DateTime dt_future = new DateTime(2100, 3, 26, 12, 0, 0, 0);
		INVALID_FUTURE_COMPLETED_DATE = dt_future.toDate();
		VALID_COMPLETED_TRAINING = new CompletedUserTrainingDto(VALID_USER_ID, VALID_TRAINING_TYPE_ID, VALID_COMPLETED_DATE);
		VALID_COMPLETED_TRAINING_DONT_EXIST = new CompletedUserTrainingDto(33L, 11L, VALID_COMPLETED_DATE);
		VALID_COMPLETED_TRAINING_LIST = new ArrayList<>();
		VALID_COMPLETED_TRAINING_LIST.add(VALID_COMPLETED_TRAINING);
	}
	
	@Before
	public void setUp() throws ValidationException {
		service = new CompletedTrainingService();
		service.setCompletedTrainingManager(manager);
		List<Long> complServResult = new ArrayList<>();
		complServResult.add(1L);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(1L);
		when(manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING)).thenReturn(true);
		when(manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING_DONT_EXIST)).thenReturn(false);
	}
	
	// isCompletedTrainingExist
	
	@Test
	public void testIsCompletedTrainingExist() {
		assertTrue("The completed training object exist", service.isCompletedTrainingExist(VALID_COMPLETED_TRAINING).getPrimitiveBooleanValue());
		assertFalse("The completed training object exist", service.isCompletedTrainingExist(VALID_COMPLETED_TRAINING_DONT_EXIST).getPrimitiveBooleanValue());
	}
	
	// create

	@Test
	public void testCreateComplatedTrainingServiceWithValidObject() throws ValidationException {
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(5L);
		when(manager.isCompletedTrainingExist(VALID_COMPLETED_TRAINING)).thenReturn(false);
		when(manager.isUserEligibleToAddTraining(VALID_COMPLETED_TRAINING, TRAINING_COMPL_DATE)).thenReturn(true);
		service.create(VALID_COMPLETED_TRAINING_LIST);
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
	
	// createOne
	
	@Test(expected = ValidationException.class)
	public void testCreateOneAlreadyExist() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(USER_ID_EXIST, TRAINING_TYPE_ID_EXIST, VALID_COMPLETED_DATE);
		when(manager.isCompletedTrainingExist(complatedUserTrainingDto)).thenReturn(true);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(-1L);
		when(manager.isUserEligibleToAddTraining(VALID_COMPLETED_TRAINING, TRAINING_COMPL_DATE)).thenReturn(true);

		service.createOne(complatedUserTrainingDto);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateOneUserNotEligible() throws ValidationException {
		CompletedUserTrainingDto complatedUserTrainingDto = new CompletedUserTrainingDto(USER_ID_NOT_ELIGIBLE, TRAINING_TYPE_ID_NOT_ELIGIBLE, VALID_COMPLETED_DATE);
		when(manager.isCompletedTrainingExist(complatedUserTrainingDto)).thenReturn(false);
		when(manager.create(VALID_COMPLETED_TRAINING)).thenReturn(-1L);
		when(manager.isUserEligibleToAddTraining(complatedUserTrainingDto, TRAINING_COMPL_DATE)).thenReturn(false);
		service.createOne(complatedUserTrainingDto);
	}

}
