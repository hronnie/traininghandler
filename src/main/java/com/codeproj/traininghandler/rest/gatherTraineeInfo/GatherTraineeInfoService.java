package com.codeproj.traininghandler.rest.gatherTraineeInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.completedTraining.CompletedTrainingService;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

//@RestController
//@RequestMapping("/gatherTraineeInfo")
public class GatherTraineeInfoService {

	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CompletedTrainingService completedTrainingService;
	
	//@RequestMapping(value="/saveTraineePersonalAndTrainingInfo", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse saveTraineePersonalAndTrainingInfo(
			@RequestBody TraineePersonalAndTrainingInfoDto traineeInfoDto) throws ValidationException {
		
		if (traineeInfoDto == null) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		
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
		
		GeneralIdResponse addressCreateResult = addressService.create(new AddressDto(postCode, city, street, houseNo, country));
		GeneralIdResponse userCreateResult = userService.create(new UserDto(lastName, firstName, displayName, dob, phoneNo, email, addressCreateResult.getValue()));
		List<CompletedUserTrainingDto> enrichedComplUsrTrList = enrichCompletedUserTrainingListWithUserId(traineeInfoDto.getCompletedUserTrainingList(), userCreateResult.getValue());
		completedTrainingService.create(enrichedComplUsrTrList);
		
		return new BooleanResponse(true, true);
	}

	private List<CompletedUserTrainingDto> enrichCompletedUserTrainingListWithUserId(
			List<CompletedUserTrainingDto> completedUserTrainingList, Long userId) {
		List<CompletedUserTrainingDto> result = new ArrayList<>();
		for (CompletedUserTrainingDto item : completedUserTrainingList) {
			item.setUserId(userId);
			result.add(item);
		}
		return result;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setCompletedTrainingService(
			CompletedTrainingService completedTrainingService) {
		this.completedTrainingService = completedTrainingService;
	}
}
