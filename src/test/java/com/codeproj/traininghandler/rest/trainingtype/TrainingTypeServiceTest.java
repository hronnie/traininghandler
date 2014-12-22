package com.codeproj.traininghandler.rest.trainingtype;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.common.TrainingTypeTestBase;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RunWith(MockitoJUnitRunner.class)
public class TrainingTypeServiceTest extends TrainingTypeTestBase {
	
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_10 = "Lorem ipsum";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_100 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Proin quis sem eget erat pharetra mattis sed. ";
	public static final String PARAMETER_STRING_SIZE_MORE_THEN_300 = "Lorem ipsum dolor sit amet, consectetur "
							+ "adipiscing elit. Aliquam nec blandit dolor. Aenean at volutpat ipsum, quis dignissim nisi. Ut "
							+ "sed arcu elementum, dignissim nisl a, adipiscing tortor. Nullam sit amet risus faucibus, luctus turpis ut, rhoncus massa. Sed mollis justo dapibus faucibus turpis duis. ";
	
	public TrainingTypeService service;
	
	@Mock
	public TrainingTypeManager trainingTypeManager;
	
	public static final Long validId = 1L;
	public static final String validName = "Aron";
	public static final String validLevelNo = "8/a";
	public static final String validDescription = "test description";

	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		service = new TrainingTypeService();
		service.setTrainingTypeManager(trainingTypeManager);
		when(trainingTypeManager.create(validName, validLevelNo, validDescription)).thenReturn(1L);
		when(trainingTypeManager.getTrainingTypeById(99L)).thenReturn(null);
		when(trainingTypeManager.update(100L, validName, validLevelNo, validDescription)).thenThrow(DatabaseEntityNotFoundException.class);
		
		when(trainingTypeManager.getTrainingTypeById(1L)).thenReturn(new TrainingType(1L, "name", "levelNo", "description"));

	}

	// create
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullObject() throws ValidationException {
		service.create(null);
	}

	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullName() throws ValidationException {
		service.create(new TrainingTypeDto(0L, null, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyName() throws ValidationException {
		service.create(new TrainingTypeDto(0L, "", validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullLevelNo() throws ValidationException {
		service.create(new TrainingTypeDto(0L, validName, null, validDescription));
	}
	
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyLevelNo() throws ValidationException {
		service.create(new TrainingTypeDto(0L, validName, "", validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNullDesc() throws ValidationException {
		service.create(new TrainingTypeDto(0L, validName, validLevelNo, null));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithEmptyDesc() throws ValidationException {
		service.create(new TrainingTypeDto(0L, validName, validLevelNo, ""));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithNameTooLong() throws ValidationException {
		service.create(new TrainingTypeDto(0L, PARAMETER_STRING_SIZE_MORE_THEN_100, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithlevelNoTooLong() throws ValidationException {
		service.create(new TrainingTypeDto(0L, validName, PARAMETER_STRING_SIZE_MORE_THEN_10, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateTrainingTypeWithDescTooLong() throws ValidationException {
		service.create(new TrainingTypeDto(0L, validName, validLevelNo, PARAMETER_STRING_SIZE_MORE_THEN_300));
	}
	
	@Test
	public void testCreateTrainingTypeWithValidValues() throws ValidationException {
		GeneralIdResponse trainingTypesResponse = service.create(new TrainingTypeDto(0L, validName, validLevelNo, validDescription));
		assertEquals("Create failed", new Long(1L), trainingTypesResponse.getValue()); 
	}
	
	// getById
	
	@Test(expected = DatabaseEntityNotFoundException.class)
	public void testGetTrainingTypeByIdWithValidButNotExistId() throws DatabaseEntityNotFoundException, ValidationException {
		service.getTrainingTypeById(99l);
	}

	@Test(expected = ValidationException.class)
	public void testGetTrainingTypeByIdWithMinusId() throws DatabaseEntityNotFoundException, ValidationException {
		service.getTrainingTypeById(-5l);
	}

	@Test(expected = ValidationException.class)
	public void testGetTrainingTypeByIdWithNullId() throws DatabaseEntityNotFoundException, ValidationException {
		service.getTrainingTypeById(null);
	}
	
	@Test
	public void testGetTrainingTypeByIdWithValidId() throws DatabaseEntityNotFoundException, ValidationException {
		TrainingTypeDto trainingType = service.getTrainingTypeById(1l);
		TrainingTypeDto expected = new TrainingTypeDto(1l, "name", "levelNo", "description");
		assertEquals("The result training type is not as expected", expected, trainingType);
	}
	
	// getAll
	
	@Test
	public void getAllTrainingTypeWithEmptyResult() throws DatabaseEntityNotFoundException, ValidationException {
		when(trainingTypeManager.getAllTrainingType()).thenReturn(null);
		assertNull("The ResultSet is empty hence the result should be null", service.getAllTrainingType());
	}
	
	@Test
	public void getAllTrainingTypeWithOneResult() throws DatabaseEntityNotFoundException, ValidationException {
		when(trainingTypeManager.getAllTrainingType()).thenReturn(oneTraingType);
		List<TrainingTypeDto> resultFromService = service.getAllTrainingType();
		assertNotNull("The result list size should be 1, but it's null", resultFromService);
		assertEquals("The result list size should be 1, but it's " + resultFromService.size(), 1, resultFromService.size());
		assertTrainingType(resultFromService.get(0), 1l, "name1", "levelNo1", "description1");
	}
	
	@Test
	public void getAllTrainingTypeWithThreeResult() throws DatabaseEntityNotFoundException, ValidationException {
		when(trainingTypeManager.getAllTrainingType()).thenReturn(threeTraingType);
		
		List<TrainingTypeDto> resultFromService = service.getAllTrainingType();
		
		assertNotNull("The result list size should be 3, but it's null", resultFromService);
		assertEquals("The result list size should be 3, but it's " + resultFromService.size(), 3, resultFromService.size());
		
		assertTrainingType(resultFromService.get(0), 1l, "name1", "levelNo1", "description1");
		assertTrainingType(resultFromService.get(1), 2l, "name2", "levelNo2", "description2");
		assertTrainingType(resultFromService.get(2), 3l, "name3", "levelNo3", "description3");
	}

	private void assertTrainingType(TrainingTypeDto trainingTypeDto, long id, String name, String levelNo, String description) {
		assertTrue("The content of the result has changed in the service", id == trainingTypeDto.getTrainingTypeId() 
				&& name.equals(trainingTypeDto.getName())
				&& levelNo.equals(trainingTypeDto.getLevelNo())
				&& description.equals(trainingTypeDto.getDescription()));
	}
	
	// update

	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithNullObject() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(null, validName, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithNullId() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(null, validName, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithInvalidId() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(-2L, validName, validLevelNo, validDescription));
	}
	
	@Test(expected = DatabaseEntityNotFoundException.class)
	public void updateTrainingTypeWithValidIdNotFoundObject() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(100L, validName, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithNullName() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, null, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithEmptyName() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, "", validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithNullLevelNo() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, validName, null, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithEmptyLevelNo() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, validName, "", validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithNullDescription() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, validName, validLevelNo, null));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithEmptyDescription() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, validName, validLevelNo, ""));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithTooLongName() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, PARAMETER_STRING_SIZE_MORE_THEN_100, validLevelNo, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithTooLongLevelNo() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, validName, PARAMETER_STRING_SIZE_MORE_THEN_10, validDescription));
	}
	
	@Test(expected = ValidationException.class)
	public void updateTrainingTypeWithTooLongDescription() throws DatabaseEntityNotFoundException, ValidationException {
		service.update(new TrainingTypeDto(validId, validName, validLevelNo, PARAMETER_STRING_SIZE_MORE_THEN_300));
	}
	
	@Test
	public void updateTrainingTypeWithValidValues() throws DatabaseEntityNotFoundException, ValidationException {
		BooleanResponse boolResp = service.update(new TrainingTypeDto(validId, validName, validLevelNo, validDescription));
		assertTrue("Update wasn't successful", boolResp.getPrimitiveBooleanValue());
	}
	
	// delete
	
	@Test(expected = ValidationException.class)
	public void deleteTrainingTypeWithNullObject() throws DatabaseEntityNotFoundException, ValidationException {
		service.delete(null);
	}
	
	@Test(expected = ValidationException.class)
	public void deleteTrainingTypeWithInvalidId() throws DatabaseEntityNotFoundException, ValidationException {
		service.delete(new TrainingTypeDto(-1L, null, null, null));
	}
	
	@Test(expected = ValidationException.class)
	public void deleteTrainingTypeWithNullId() throws DatabaseEntityNotFoundException, ValidationException {
		service.delete(new TrainingTypeDto(null, null, null, null));
	}
	
	@SuppressWarnings("unchecked")
	@Test(expected = DatabaseEntityNotFoundException.class)
	public void deleteTrainingTypeWithValidIdButNotExistingObject() throws DatabaseEntityNotFoundException, ValidationException {
		when(trainingTypeManager.delete(5L)).thenThrow(DatabaseEntityNotFoundException.class);
		service.delete(new TrainingTypeDto(5L, null, null, null));
	}
	
	@Test
	public void deleteTrainingTypeWithValidId() throws DatabaseEntityNotFoundException, ValidationException {
		when(trainingTypeManager.delete(3L)).thenReturn(true);
		assertTrue("Delete wasn't successful", service.delete(new TrainingTypeDto(3L, null, null, null)).getPrimitiveBooleanValue());
	}
	
}
