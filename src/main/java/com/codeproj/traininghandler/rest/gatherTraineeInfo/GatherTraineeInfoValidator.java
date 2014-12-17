package com.codeproj.traininghandler.rest.gatherTraineeInfo;

import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class GatherTraineeInfoValidator extends ValidatorBaseUtility {
	
	public static void saveTraineePersonalAndTrainingInfo(
			TraineePersonalAndTrainingInfoDto traineeInfoDto) throws ValidationException {
		
		if (traineeInfoDto == null) {
			throw new ValidationException("traineeInfoDto is null");
		}
		
		if (traineeInfoDto.getAddress() == null ||  traineeInfoDto.getCompletedUserTrainingList() == null 
				|| traineeInfoDto.getCompletedUserTrainingList().size() < 1)
		if (traineeInfoDto.getAddress() == null) {
			throw new ValidationException("Address is emtpy or null");
		}
		
		if (traineeInfoDto.getCompletedUserTrainingList() == null 
			|| traineeInfoDto.getCompletedUserTrainingList().size() < 1) {
			throw new ValidationException("You need to have at least one completed training");
		}
		
		mandatoryParameter("postCode", traineeInfoDto.getAddress().getPostCode());
		mandatoryParameter("city", traineeInfoDto.getAddress().getCity());
		mandatoryParameter("street", traineeInfoDto.getAddress().getStreet());
		mandatoryParameter("houseNo", traineeInfoDto.getAddress().getHouseNo());
		mandatoryParameter("country", traineeInfoDto.getAddress().getCountry());
		mandatoryParameter("lastName", traineeInfoDto.getUser().getLastName());
		mandatoryParameter("firstName", traineeInfoDto.getUser().getFirstName());
		mandatoryParameter("displayName", traineeInfoDto.getUser().getDisplayName());
		mandatoryParameter("phoneNo", traineeInfoDto.getUser().getPhoneNo());
		mandatoryParameter("email", traineeInfoDto.getUser().getEmail());
		mandatoryParameter("dob", traineeInfoDto.getUser().getDob());
		
		validateStringLength("postCode", traineeInfoDto.getAddress().getPostCode(), 15);
		validateStringLength("city", traineeInfoDto.getAddress().getCity(), 50);
		validateStringLength("street", traineeInfoDto.getAddress().getStreet(), 70);
		validateStringLength("houseNo", traineeInfoDto.getAddress().getHouseNo(), 10);
		validateStringLength("country", traineeInfoDto.getAddress().getCountry(), 50);
		validateStringLength("lastName", traineeInfoDto.getUser().getLastName(), 50);
		validateStringLength("firstName", traineeInfoDto.getUser().getFirstName(), 50);
		validateStringLength("displayName", traineeInfoDto.getUser().getDisplayName(), 50);
		validateStringLength("phoneNo", traineeInfoDto.getUser().getPhoneNo(), 50);
		validateStringLength("email", traineeInfoDto.getUser().getEmail(), 80);
	}

}
