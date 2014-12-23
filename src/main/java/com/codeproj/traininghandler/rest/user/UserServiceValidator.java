package com.codeproj.traininghandler.rest.user;

import java.util.Date;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class UserServiceValidator extends ValidatorBaseUtility {

	public static void create(String lastName, String firstName,
			String displayName, Date dob, String phoneNumber, String email) throws ValidationException {
		
		mandatoryParameter("lastName", lastName);
		mandatoryParameter("firstName", firstName);
		mandatoryParameter("displayName", displayName);
		mandatoryParameter("dob", dob);
		mandatoryParameter("phoneNumber", phoneNumber);
		mandatoryParameter("email", email);
		
		validateStringLength("lastName", lastName, 50);
		validateStringLength("firstName", firstName, 50);
		validateStringLength("displayName", displayName, 50);
		validateStringLength("phoneNumber", phoneNumber, 50);
		validateStringLength("email", email, 80);
		
		emailValidator(email);
		isDateLater(dob, 6);
	}
}
