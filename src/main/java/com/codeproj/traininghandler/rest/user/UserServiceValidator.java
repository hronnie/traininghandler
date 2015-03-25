package com.codeproj.traininghandler.rest.user;

import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class UserServiceValidator extends ValidatorBaseUtility {

	public static void create(UserDto user, boolean isUseBasicFields) throws ValidationException {
		
		if (!isUseBasicFields) {
			mandatoryParameter("lastName", user.getLastName());
			mandatoryParameter("firstName", user.getFirstName());
			mandatoryParameter("displayName", user.getDisplayName());
			mandatoryParameter("dob", user.getDob());

			validateStringLength("lastName", user.getLastName(), 50);
			validateStringLength("firstName", user.getFirstName(), 50);
			validateStringLength("displayName", user.getDisplayName(), 50);
			isDateLater(user.getDob(), 6);
		} else {
			mandatoryParameter("fullName", user.getName());
			validateStringLength("fullName", user.getName(), 100);
		}
		mandatoryParameter("phoneNumber", user.getPhoneNo());
		mandatoryParameter("email", user.getEmail());
		mandatoryParameter("addressId", user.getAddressId());
		
		validateStringLength("phoneNumber", user.getPhoneNo(), 50);
		validateStringLength("email", user.getEmail(), 80);
		
		emailValidator(user.getEmail());
		entityIdValidator(user.getAddressId());
	}
}
