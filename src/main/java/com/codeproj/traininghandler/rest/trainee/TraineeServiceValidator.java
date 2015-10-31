package com.codeproj.traininghandler.rest.trainee;

import static com.codeproj.traininghandler.util.Constants.*;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class TraineeServiceValidator extends ValidatorBaseUtility {

	public static void edit(TraineeDto traineeDto) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_NAME, traineeDto.getName());
		mandatoryParameter(VALIDATION_PARAMETER_POSTCODE, traineeDto.getPostCode());
		mandatoryParameter(VALIDATION_PARAMETER_ADDRESS, traineeDto.getAddress());
		mandatoryParameter(VALIDATION_PARAMETER_PHONE, traineeDto.getPhone());
		mandatoryParameter(VALIDATION_PARAMETER_EMAIL, traineeDto.getEmail());
		mandatoryParameter(VALIDATION_PARAMETER_USER_ID, traineeDto.getUserId());
		mandatoryParameter(VALIDATION_PARAMETER_ADDRESS_ID, traineeDto.getAddressId());
		
		validateStringLength(VALIDATION_PARAMETER_NAME, traineeDto.getName(), TABLE_FIELD_SIZE_NAME);		
		validateStringLength(VALIDATION_PARAMETER_POSTCODE, traineeDto.getPostCode(), TABLE_FIELD_SIZE_POSTCODE);		
		validateStringLength(VALIDATION_PARAMETER_ADDRESS, traineeDto.getAddress(), TABLE_FIELD_SIZE_ADDRESS);		
		validateStringLength(VALIDATION_PARAMETER_PHONE, traineeDto.getPhone(), TABLE_FIELD_SIZE_PHONE);		
		validateStringLength(VALIDATION_PARAMETER_EMAIL, traineeDto.getEmail(), TABLE_FIELD_SIZE_EMAIL);
		
		entityIdValidator(traineeDto.getUserId());
		entityIdValidator(traineeDto.getAddressId());
	}

	public static void delete(Long userId, Long addressId) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_USER_ID, userId);
		mandatoryParameter(VALIDATION_PARAMETER_ADDRESS_ID, addressId);
		entityIdValidator(userId);
		entityIdValidator(addressId);
	}

}
