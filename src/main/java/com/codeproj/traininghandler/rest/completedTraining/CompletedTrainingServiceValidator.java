package com.codeproj.traininghandler.rest.completedTraining;

import java.util.List;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class CompletedTrainingServiceValidator extends ValidatorBaseUtility {

	public static void create(
			List<CompletedUserTrainingDto> complatedUserTrainingDtoList) throws ValidationException {
		
		if (complatedUserTrainingDtoList == null || complatedUserTrainingDtoList.size() == 0) {
			throw new ValidationException("Input object is null or empty");
		}
		
		for (CompletedUserTrainingDto compUsrTr : complatedUserTrainingDtoList) {
			mandatoryParameter("userId", compUsrTr.getUserId());
			mandatoryParameter("traingTypeId", compUsrTr.getTrainingTypeId());
			mandatoryParameter("completedDate", compUsrTr.getCompletedDate());
			
			entityIdValidator(compUsrTr.getUserId());
			entityIdValidator(compUsrTr.getTrainingTypeId());
			
			isDateInTheFuture(compUsrTr.getCompletedDate());
		}
	}

}
