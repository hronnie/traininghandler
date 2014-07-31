package com.codeproj.traininghandler.rest.trainingtype;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;


public class TrainingTypeServiceValidator extends ValidatorBaseUtility {
	
	public static void create(String name, String levelNo, String description) throws ValidationException {
		mandatoryParameter("name", name);
		mandatoryParameter("levelNo", levelNo);
		mandatoryParameter("description", description);
		
		validateStringLength("name", name, 100);
		validateStringLength("levelNo", levelNo, 10);
		validateStringLength("description", description, 300);
		
		
	}

}
