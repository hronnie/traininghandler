package com.codeproj.traininghandler.rest.showEligibles;

import static com.codeproj.traininghandler.util.Constants.*;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class ShowTraineesEligibleForTrainingValidator extends ValidatorBaseUtility {

	public static void getEligibleTraineesByTrainingTypeIdAndComplDate(
			Long trainingTypeId) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_TRAINING_TYPE_ID, trainingTypeId);
	}

}
