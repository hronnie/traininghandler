package com.codeproj.traininghandler.rest.trainee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.codeproj.traininghandler.rest.common.BooleanResponse;

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
	public BooleanResponse editTrainee(@RequestBody TraineeDto traineeDto) throws ValidationException {
		if (traineeDto == null) {
			throw new ValidationException("Trainee dto is null");
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

		return new BooleanResponse(result);
	}
	
	@RequestMapping(value="/delete/userId/{userId}/addressId/{addressId}", method = RequestMethod.DELETE,headers="Accept=application/json")
	public BooleanResponse deleteTrainee(@PathVariable Long userId, @PathVariable Long addressId) throws ValidationException {
		TraineeServiceValidator.delete(userId, addressId);
		return new BooleanResponse(userManager.deleteTrainee(userId, addressId));
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
