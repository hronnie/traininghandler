package com.codeproj.traininghandler.rest.trainingtype;

import static com.codeproj.traininghandler.util.Constants.*;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;


public class TrainingTypeServiceValidator extends ValidatorBaseUtility {
	
	public static void create(String name, String levelNo, String description) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_NAME, name);
		mandatoryParameter(VALIDATION_PARAMETER_LEVEL_NO, levelNo);
		mandatoryParameter(VALIDATION_PARAMETER_TRAINING_TYPE_DESC, description);
		
		validateStringLength(VALIDATION_PARAMETER_NAME, name, TABLE_FIELD_SIZE_NAME);
		validateStringLength(VALIDATION_PARAMETER_LEVEL_NO, levelNo, TABLE_FIELD_SIZE_LEVEL_NO);
		validateStringLength(VALIDATION_PARAMETER_TRAINING_TYPE_DESC, description, TABLE_FIELD_SIZE_TRAINING_TYPE_DESC);
	}

	public static void update(Long trainingTypeId, String name, String levelNo, String description) throws ValidationException {
		entityIdValidator(trainingTypeId);
		mandatoryParameter(VALIDATION_PARAMETER_NAME, name);
		mandatoryParameter(VALIDATION_PARAMETER_LEVEL_NO, levelNo);
		mandatoryParameter(VALIDATION_PARAMETER_TRAINING_TYPE_DESC, description);
		
		validateStringLength(VALIDATION_PARAMETER_NAME, name, TABLE_FIELD_SIZE_NAME);
		validateStringLength(VALIDATION_PARAMETER_LEVEL_NO, levelNo, TABLE_FIELD_SIZE_LEVEL_NO);
		validateStringLength(VALIDATION_PARAMETER_TRAINING_TYPE_DESC, description, TABLE_FIELD_SIZE_TRAINING_TYPE_DESC);
	}

	public static void delete(Long trainingTypeId) throws ValidationException {
		entityIdValidator(trainingTypeId);
	}

	public static void getTrainingTypeById(Long id) throws ValidationException {
		entityIdValidator(id);
	}
}
