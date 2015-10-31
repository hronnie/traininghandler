package com.codeproj.traininghandler.rest.user;

import static com.codeproj.traininghandler.util.Constants.*;

import org.apache.commons.lang3.StringUtils;

import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class UserServiceValidator extends ValidatorBaseUtility {

	public static void create(UserDto user, boolean isUseBasicFields) throws ValidationException {
		validateBasicUserData(user, isUseBasicFields);
		entityIdValidator(user.getAddressId());
	}

	private static void validateBasicUserData(UserDto user,
			boolean isUseBasicFields) throws ValidationException {
		if (!isUseBasicFields) {
			mandatoryParameter(VALIDATION_PARAMETER_LAST_NAME, user.getLastName());
			mandatoryParameter(VALIDATION_PARAMETER_FIRST_NAME, user.getFirstName());
			mandatoryParameter(VALIDATION_PARAMETER_DISPLAY_NAME, user.getDisplayName());
			mandatoryParameter(VALIDATION_PARAMETER_DOB, user.getDob());

			validateStringLength(VALIDATION_PARAMETER_LAST_NAME, user.getLastName(), TABLE_FIELD_SIZE_LAST_NAME);
			validateStringLength(VALIDATION_PARAMETER_FIRST_NAME, user.getFirstName(), TABLE_FIELD_SIZE_FIRST_NAME);
			validateStringLength(VALIDATION_PARAMETER_DISPLAY_NAME, user.getDisplayName(), TABLE_FIELD_SIZE_DISPLAY_NAME);
			isDateLater(user.getDob(), Constants.MIN_AGE_YEAR);
		} else {
			mandatoryParameter(VALIDATION_PARAMETER_NAME, user.getName());
			validateStringLength(VALIDATION_PARAMETER_NAME, user.getName(), TABLE_FIELD_SIZE_NAME);
		}

		validateStringLength(VALIDATION_PARAMETER_PHONE, user.getPhoneNo(), TABLE_FIELD_SIZE_PHONE);
		validateStringLength(VALIDATION_PARAMETER_EMAIL, user.getEmail(), TABLE_FIELD_SIZE_EMAIL);
		
		if (!StringUtils.isEmpty(user.getEmail())) {
			emailValidator(user.getEmail());
		}
	}
	
	public static void createUserFromExcel(TrainingExcelDto trainingExcelItem) throws ValidationException {
		UserDto user = new UserDto();
		user.setName(trainingExcelItem.getName());
		user.setPhoneNo(trainingExcelItem.getPhoneNo());
		user.setEmail(trainingExcelItem.getEmail());
		validateBasicUserData(user, true);
	}

}
