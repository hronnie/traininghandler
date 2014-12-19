package com.codeproj.traininghandler.rest.user;

import java.util.Date;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class UserServiceValidator extends ValidatorBaseUtility {

	public static void create(String lastName, String firstName,
			String displayName, Date dob, String email) throws ValidationException {
		
		mandatoryParameter("lastName", lastName);
		mandatoryParameter("firstName", firstName);
		mandatoryParameter("displayName", displayName);
		mandatoryParameter("dob", dob);
		mandatoryParameter("email", email);
		
		emailValidator(email);
		isDateLater(dob, 6);
	}
}
