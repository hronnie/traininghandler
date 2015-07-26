package com.codeproj.traininghandler.rest.showEligibles;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class ShowTraineesEligibleForTrainingValidator extends ValidatorBaseUtility {

	public static void getEligibleTraineesByTrainingTypeIdAndComplDate(
			Long trainingTypeId, String trainingComplDate) throws ValidationException {
		mandatoryParameter("trainingTypeId", trainingTypeId);
		mandatoryParameter("trainingComplDate", trainingComplDate);
		boolean trainingComplDateValid = trainingComplDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})");
		if (!trainingComplDateValid) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_TRAINING_COMPLETED_DATE_INVALID);
		}
	}

}
