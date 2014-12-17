package com.codeproj.traininghandler.rest.gatherTraineeInfo;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/gatherTraineeInfo")
public class GatherTraineeInfoService {

	@RequestMapping(value="/saveTraineePersonalAndTrainingInfo", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse saveTraineePersonalAndTrainingInfo(
			@RequestBody TraineePersonalAndTrainingInfoDto traineeInfoDto) throws ValidationException {
		GatherTraineeInfoValidator.saveTraineePersonalAndTrainingInfo(traineeInfoDto);
		
		String city = ValidatorBaseUtility.stripXSS(traineeInfoDto.getAddress().getCity());
		String country = ValidatorBaseUtility.stripXSS(traineeInfoDto.getAddress().getCountry());
		String houseNo = ValidatorBaseUtility.stripXSS(traineeInfoDto.getAddress().getHouseNo());
		String postCode = ValidatorBaseUtility.stripXSS(traineeInfoDto.getAddress().getPostCode());
		String street = ValidatorBaseUtility.stripXSS(traineeInfoDto.getAddress().getStreet());

		String displayName = ValidatorBaseUtility.stripXSS(traineeInfoDto.getUser().getDisplayName());
		String email = ValidatorBaseUtility.stripXSS(traineeInfoDto.getUser().getEmail());
		String firstName = ValidatorBaseUtility.stripXSS(traineeInfoDto.getUser().getFirstName());
		String lastName = ValidatorBaseUtility.stripXSS(traineeInfoDto.getUser().getLastName());
		String phoneNo = ValidatorBaseUtility.stripXSS(traineeInfoDto.getUser().getPhoneNo());
		
		Date dob = traineeInfoDto.getUser().getDob();
		
		List<CompletedUserTrainingDto> completedTrainingList = traineeInfoDto.getCompletedUserTrainingList();
		
		//		String name = ValidatorBaseUtility.stripXSS(trainingType.getName());

		return new BooleanResponse(true);
	}
}
