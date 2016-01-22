package com.codeproj.traininghandler.rest.completedTraining;

import static com.codeproj.traininghandler.util.Constants.*;

import java.util.List;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class CompletedTrainingServiceValidator extends ValidatorBaseUtility {

	public static void create(
			List<CompletedUserTrainingDto> complatedUserTrainingDtoList) throws ValidationException {
		
		if (complatedUserTrainingDtoList == null || complatedUserTrainingDtoList.size() == 0) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		
		for (CompletedUserTrainingDto compUsrTr : complatedUserTrainingDtoList) {
			createOne(compUsrTr);
		}
	}

	public static void createOne(CompletedUserTrainingDto compUsrTr)
			throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_USER_ID, compUsrTr.getUserId());
		mandatoryParameter(VALIDATION_PARAMETER_TRAINING_TYPE_ID, compUsrTr.getTrainingTypeId());
		mandatoryParameter(VALIDATION_PARAMETER_COMPLETED_DATE, compUsrTr.getCompletedDate());
		
		entityIdValidator(compUsrTr.getUserId());
		entityIdValidator(compUsrTr.getTrainingTypeId());
		
		isDateInTheFuture(compUsrTr.getCompletedDate());
	}

	public static void update(CompletedUserTrainingDto complatedUserTrainingDto) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_COMPLETED_TRAINING_ID, complatedUserTrainingDto.getCompletedUserTrainingId());
		mandatoryParameter(VALIDATION_PARAMETER_USER_ID, complatedUserTrainingDto.getUserId());
		mandatoryParameter(VALIDATION_PARAMETER_TRAINING_TYPE_ID, complatedUserTrainingDto.getTrainingTypeId());
		mandatoryParameter(VALIDATION_PARAMETER_COMPLETED_DATE, complatedUserTrainingDto.getCompletedDate());
		
		entityIdValidator(complatedUserTrainingDto.getCompletedUserTrainingId());
		entityIdValidator(complatedUserTrainingDto.getUserId());
		entityIdValidator(complatedUserTrainingDto.getTrainingTypeId());
	}

	public static void delete(Long completedUserTrainingId) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_COMPLETED_TRAINING_ID, completedUserTrainingId);
		
		entityIdValidator(completedUserTrainingId);
	}

	public static void listByUserId(Long userId) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_USER_ID, userId);
		
		entityIdValidator(userId);
	}
	
	public static void listViewableByUserId(Long userId) throws ValidationException {
		mandatoryParameter(VALIDATION_PARAMETER_USER_ID, userId);
		
		entityIdValidator(userId);
	}

}
