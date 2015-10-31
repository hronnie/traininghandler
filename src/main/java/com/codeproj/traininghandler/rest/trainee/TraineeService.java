package com.codeproj.traininghandler.rest.trainee;

import static com.codeproj.traininghandler.util.Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.dto.TraineeDtos;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.manager.trainee.TraineeManager;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.model.Address;
import com.codeproj.traininghandler.model.User;
import com.codeproj.traininghandler.rest.common.BaseResponse;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.util.Constants;

@RestController
@RequestMapping("/trainee")
public class TraineeService {
	
	@Autowired
	private TraineeManager traineeManager;
	
	@Autowired
	private AddressManager addressManager;
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value="/getAll", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TraineeDtos getAllTrainees() {
		List<TraineeDto> traineeList = traineeManager.getAllTrainees();
		TraineeDtos result = new TraineeDtos(traineeList);
		return result;
	}

	@RequestMapping(value="/edit", method = RequestMethod.POST,headers="Accept=application/json")
	public BaseResponse editTrainee(@RequestBody TraineeDto traineeDto) {
		try {
			if (traineeDto == null) {
				throw new ValidationException(VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
			}
			TraineeServiceValidator.edit(traineeDto);
			
			Address newAddress = addressManager.getAddressByAddressId(traineeDto.getAddressId());
			newAddress.setPostalCode(traineeDto.getPostCode());
			newAddress.setOneLineAddress(traineeDto.getAddress());
			boolean addressEditResult = addressManager.edit(newAddress);
			
			User newUser = userManager.getUserByUserId(traineeDto.getUserId());
			newUser.setName(traineeDto.getName());
			newUser.setMobileNo(traineeDto.getPhone());
			newUser.setEmail(traineeDto.getEmail());
			boolean userEditResult = userManager.edit(newUser);
			
			boolean result = addressEditResult && userEditResult;
			String errMsg = null;
			if (!result) {
				errMsg = Constants.VALIDATION_ERR_MSG_GENERAL_ERROR;
			}
			
			return new BaseResponse(result, errMsg);
			
		} catch (ValidationException ve) {
			return new BaseResponse(ve.getMessage());
		}
	}
	
	@RequestMapping(value="/delete/userId/{userId}/addressId/{addressId}", method = RequestMethod.DELETE,headers="Accept=application/json")
	public BaseResponse deleteTrainee(@PathVariable Long userId, @PathVariable Long addressId) {
		try {
			TraineeServiceValidator.delete(userId, addressId);
			return new BaseResponse(userManager.deleteTrainee(userId, addressId));
		} catch (ValidationException ve) {
			return new BaseResponse(ve.getMessage()); 
		}
	}

	
	public void setTraineeManager(
			TraineeManager traineeManager) {
		this.traineeManager = traineeManager;
	}

	public void setAddressManager(AddressManager addressManager) {
		this.addressManager = addressManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
