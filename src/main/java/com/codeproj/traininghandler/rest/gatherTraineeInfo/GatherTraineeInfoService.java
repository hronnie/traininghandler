package com.codeproj.traininghandler.rest.gatherTraineeInfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.rest.user.UserService;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/gatherTraineeInfo")
public class GatherTraineeInfoService {

	@Autowired
	AddressService addressService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/saveTraineePersonalAndTrainingInfo", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse saveTraineePersonalAndTrainingInfo(
			@RequestBody TraineePersonalAndTrainingInfoDto traineeInfoDto) throws ValidationException {
		
		if (traineeInfoDto == null) {
			throw new ValidationException("trainee info dto is null");
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
		
		List<CompletedUserTrainingDto> completedTrainingList = traineeInfoDto.getCompletedUserTrainingList();
		
//		GeneralIdResponse addressResult = addressService.create(new AddressDto(postCode, city, street, houseNo, country));
		//userService.create(user)

		return new BooleanResponse(true);
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
