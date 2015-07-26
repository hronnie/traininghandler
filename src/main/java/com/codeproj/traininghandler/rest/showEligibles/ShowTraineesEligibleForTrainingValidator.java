package com.codeproj.traininghandler.rest.showEligibles;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class ShowTraineesEligibleForTrainingValidator extends ValidatorBaseUtility {

	public static void getEligibleTraineesByTrainingTypeIdAndComplDate(
			Long trainingTypeId) throws ValidationException {
		mandatoryParameter("trainingTypeId", trainingTypeId);
	}

}
