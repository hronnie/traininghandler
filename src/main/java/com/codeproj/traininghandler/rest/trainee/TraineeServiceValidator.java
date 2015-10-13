package com.codeproj.traininghandler.rest.trainee;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class TraineeServiceValidator extends ValidatorBaseUtility {

	public static void edit(TraineeDto traineeDto) throws ValidationException {
		mandatoryParameter("Name", traineeDto.getName());
		mandatoryParameter("Postal code", traineeDto.getPostCode());
		mandatoryParameter("Address", traineeDto.getAddress());
		mandatoryParameter("Phone", traineeDto.getPhone());
		mandatoryParameter("Email", traineeDto.getEmail());
		mandatoryParameter("UserId", traineeDto.getUserId());
		mandatoryParameter("AddressId", traineeDto.getAddressId());
		
		validateStringLength("Name", traineeDto.getName(), 100);		
		validateStringLength("postCode", traineeDto.getPostCode(), 15);		
		validateStringLength("Address", traineeDto.getAddress(), 100);		
		validateStringLength("Phone", traineeDto.getPhone(), 50);		
		validateStringLength("Email", traineeDto.getEmail(), 80);
		
		entityIdValidator(traineeDto.getUserId());
		entityIdValidator(traineeDto.getAddressId());
	}

	public static void delete(Long userId, Long addressId) throws ValidationException {
		mandatoryParameter("userId", userId);
		mandatoryParameter("addressId", addressId);
		entityIdValidator(userId);
		entityIdValidator(addressId);
	}

}
