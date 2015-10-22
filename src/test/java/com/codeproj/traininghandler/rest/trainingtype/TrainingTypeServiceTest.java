package com.codeproj.traininghandler.rest.trainingtype;

import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.SERVICE_CALL_SHOULD_BE_SUCCESSFUL;
import static com.codeproj.traininghandler.common.MessageConstants.WRONG_VALIDATION_MESSAGE;
import static com.codeproj.traininghandler.util.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.codeproj.traininghandler.dto.TrainingTypeDtos;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.rest.common.BaseResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RunWith(MockitoJUnitRunner.class)
public class TrainingTypeServiceTest extends TrainingTypeTestBase {
	
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
		when(trainingTypeManager.update(100L, validName, validLevelNo, validDescription)).thenThrow(new DatabaseEntityNotFoundException(VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST));
		
		when(trainingTypeManager.getTrainingTypeById(1L)).thenReturn(new TrainingType(1L, "name", "levelNo", "description"));

	}

	// create
	
	@Test
	public void testCreateTrainingTypeWithNullObject() {
		GeneralIdResponse result = service.create(null);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}

	@Test
	public void testCreateTrainingTypeWithNullName() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, null, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_NAME, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithEmptyName() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, "", validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_NAME, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithNullLevelNo() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, validName, null, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_LEVEL_NO, result.getMessage());
	}
	
	
	@Test
	public void testCreateTrainingTypeWithEmptyLevelNo() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, validName, "", validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_LEVEL_NO, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithNullDesc() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, validName, validLevelNo, null));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_DESC, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithEmptyDesc() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, validName, validLevelNo, ""));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_DESC, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithNameTooLong() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, PARAMETER_STRING_SIZE_MORE_THEN_100, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_NAME + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_NAME;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithlevelNoTooLong() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, validName, PARAMETER_STRING_SIZE_MORE_THEN_10, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_LEVEL_NO + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_LEVEL_NO;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithDescTooLong() {
		GeneralIdResponse result = service.create(new TrainingTypeDto(0L, validName, validLevelNo, PARAMETER_STRING_SIZE_MORE_THEN_300));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_TRAINING_TYPE_DESC + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_TRAINING_TYPE_DESC;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void testCreateTrainingTypeWithValidValues() {
		GeneralIdResponse trainingTypesResponse = service.create(new TrainingTypeDto(0L, validName, validLevelNo, validDescription));
		assertEquals("Create failed", new Long(1L), trainingTypesResponse.getValue()); 
	}
	
	// getById
	
	@Test
	public void testGetTrainingTypeByIdWithValidButNotExistId() {
		TrainingTypeDto result = service.getTrainingTypeById(99l);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST, result.getMessage());
	}

	@Test
	public void testGetTrainingTypeByIdWithMinusId() {
		TrainingTypeDto result = service.getTrainingTypeById(-5l);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}

	@Test
	public void testGetTrainingTypeByIdWithNullId() {
		TrainingTypeDto result = service.getTrainingTypeById(null);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void testGetTrainingTypeByIdWithValidId() {
		TrainingTypeDto result = service.getTrainingTypeById(1l);
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		TrainingTypeDto expected = new TrainingTypeDto(1l, "name", "levelNo", "description");
		assertEquals("The result training type is not as expected", expected, result);
	}
	
	// getAll
	
	@Test
	public void getAllTrainingTypeWithEmptyResult() throws DatabaseEntityNotFoundException {
		when(trainingTypeManager.getAllTrainingType()).thenThrow(new DatabaseEntityNotFoundException(VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND));
		TrainingTypeDtos result = service.getAllTrainingType();
		assertFalse(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND, result.getMessage());
	}
	
	@Test
	public void getAllTrainingTypeWithOneResult() throws DatabaseEntityNotFoundException {
		when(trainingTypeManager.getAllTrainingType()).thenReturn(oneTraingType);
		TrainingTypeDtos result = service.getAllTrainingType();
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		List<TrainingTypeDto> trainingTypeList = result.getTrainingTypeDtoList();
		assertNotNull("The result list size should be 1, but it's null", trainingTypeList);
		assertEquals("The result list size should be 1, but it's " + trainingTypeList.size(), 1, trainingTypeList.size());
		assertTrainingType(trainingTypeList.get(0), 1l, "name1", "levelNo1", "description1");
	}
	
	@Test
	public void getAllTrainingTypeWithThreeResult() throws DatabaseEntityNotFoundException {
		when(trainingTypeManager.getAllTrainingType()).thenReturn(threeTraingType);
		
		TrainingTypeDtos result = service.getAllTrainingType();
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
		List<TrainingTypeDto> trainingTypeList = result.getTrainingTypeDtoList();
		
		assertNotNull("The result list size should be 3, but it's null", trainingTypeList);
		assertEquals("The result list size should be 3, but it's " + trainingTypeList.size(), 3, trainingTypeList.size());
		
		assertTrainingType(trainingTypeList.get(0), 1l, "name1", "levelNo1", "description1");
		assertTrainingType(trainingTypeList.get(1), 2l, "name2", "levelNo2", "description2");
		assertTrainingType(trainingTypeList.get(2), 3l, "name3", "levelNo3", "description3");
	}

	private void assertTrainingType(TrainingTypeDto trainingTypeDto, long id, String name, String levelNo, String description) {
		assertTrue("The content of the result has changed in the service", id == trainingTypeDto.getTrainingTypeId() 
				&& name.equals(trainingTypeDto.getName())
				&& levelNo.equals(trainingTypeDto.getLevelNo())
				&& description.equals(trainingTypeDto.getDescription()));
	}
	
	// update

	
	@Test
	public void updateTrainingTypeWithNullObject() {
		BaseResponse result = service.update(new TrainingTypeDto(null, validName, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithNullId() {
		BaseResponse result = service.update(new TrainingTypeDto(null, validName, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithInvalidId() {
		BaseResponse result = service.update(new TrainingTypeDto(-2L, validName, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithValidIdNotFoundObject() {
		//when(trainingTypeManager.update(100L, validName, validLevelNo, validDescription)).thenThrow(new DatabaseEntityNotFoundException(VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST));
		BaseResponse result = service.update(new TrainingTypeDto(100L, validName, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithNullName() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, null, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_NAME, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithEmptyName() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, "", validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_NAME, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithNullLevelNo() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, validName, null, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_LEVEL_NO, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithEmptyLevelNo() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, validName, "", validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_LEVEL_NO, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithNullDescription() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, validName, validLevelNo, null));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_DESC, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithEmptyDescription() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, validName, validLevelNo, ""));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_MANDATORY_PARAMETER + VALIDATION_PARAMETER_TRAINING_TYPE_DESC, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithTooLongName() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, PARAMETER_STRING_SIZE_MORE_THEN_100, validLevelNo, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_NAME + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_TT_NAME;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithTooLongLevelNo() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, validName, PARAMETER_STRING_SIZE_MORE_THEN_10, validDescription));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_LEVEL_NO + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_LEVEL_NO;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithTooLongDescription() {
		BaseResponse result = service.update(new TrainingTypeDto(validId, validName, validLevelNo, PARAMETER_STRING_SIZE_MORE_THEN_300));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		String expectedErrMsg = VALIDATION_PARAMETER_TRAINING_TYPE_DESC + VALIDATION_ERR_MSG_PARAMETER_TOO_LONG + TABLE_FIELD_SIZE_TRAINING_TYPE_DESC;
		assertEquals(WRONG_VALIDATION_MESSAGE, expectedErrMsg, result.getMessage());
	}
	
	@Test
	public void updateTrainingTypeWithValidValues() {
		BaseResponse boolResp = service.update(new TrainingTypeDto(validId, validName, validLevelNo, validDescription));
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, boolResp.getSuccess());
		assertTrue("Update wasn't successful", boolResp.getSuccess());
	}
	
	// delete
	
	@Test
	public void deleteTrainingTypeWithNullObject() {
		BaseResponse result = service.delete(null);
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void deleteTrainingTypeWithInvalidId() {
		BaseResponse result = service.delete(new TrainingTypeDto(-1L, null, null, null));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void deleteTrainingTypeWithNullId() {
		BaseResponse result = service.delete(new TrainingTypeDto(null, null, null, null));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST, result.getMessage());
	}
	
	@Test
	public void deleteTrainingTypeWithValidIdButNotExistingObject() throws DatabaseEntityNotFoundException {
		when(trainingTypeManager.delete(5L)).thenThrow(new DatabaseEntityNotFoundException(VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST));
		BaseResponse result = service.delete(new TrainingTypeDto(5L, null, null, null));
		assertFalse(SERVICE_CALL_SHOULDNT_BE_SUCCESSFUL, result.getSuccess());
		assertEquals(WRONG_VALIDATION_MESSAGE, VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST, result.getMessage());
	}
	
	@Test
	public void deleteTrainingTypeWithValidId() throws DatabaseEntityNotFoundException {
		when(trainingTypeManager.delete(3L)).thenReturn(true);
		BaseResponse result = service.delete(new TrainingTypeDto(3L, null, null, null));
		assertTrue(SERVICE_CALL_SHOULD_BE_SUCCESSFUL, result.getSuccess());
	}
	
}
