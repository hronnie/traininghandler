package com.codeproj.traininghandler.rest.user;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.rest.address.AddressService;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/user")
public class UserService {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	AddressService addressService;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody UserDto user) {
		try {
			logger.debug("create with user >> " + user);
			return createUser(user, false);
		} catch (ValidationException ve) {
			logger.debug("validation failed: " + ve.getMessage());
			return new GeneralIdResponse(ve.getMessage());
		}
	}
	
	@RequestMapping(value="/createUserWithAddress", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse createUserWithAddress(@RequestBody TrainingExcelDto trainingExcelDto) {
		logger.debug("create with trainingExcelDto >> " + trainingExcelDto);
		if (trainingExcelDto == null) {
			logger.debug("trainingExcelDto is null");
			return new GeneralIdResponse(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		Long userId = getUserIdIfExist(trainingExcelDto);
		if (userId != -1L) {
			logger.debug("user already exist and returning with the user id");
			return new GeneralIdResponse(userId);
		}
		
		GeneralIdResponse addressIdResp = addressService.createFromForm(new AddressDto(trainingExcelDto.getPostCode(), trainingExcelDto.getAddress()));
		GeneralIdResponse userIdResp;
		try {
			userIdResp = createUser(new UserDto(trainingExcelDto.getName(), trainingExcelDto.getPhoneNo(), trainingExcelDto.getEmail(), addressIdResp.getValue()), true);
		} catch (ValidationException ve) {
			logger.debug("create a user failed with validation error: " + ve.getMessage());
			return new GeneralIdResponse(ve.getMessage());
		}
		logger.debug("user creation was successful and returned with valid user id: " + userIdResp.getValue());
		return new GeneralIdResponse(userIdResp.getValue());
	}
	
	@RequestMapping(value="/getUserIdByEmailAndName/{email}/{name}", method = RequestMethod.GET,headers="Accept=application/json")
	public GeneralIdResponse getUserIdByEmailAndName(@PathVariable("email") String email, @PathVariable("name") String name) {
		logger.debug("getUserIdByEmailAndName with email and name >> " + email + ", " + name);
		if (StringUtils.isEmpty(email)) {
			email = Constants.EXCEL_TRAINING_MISSING_EMAIL;
		}
		Long serviceResult = userManager.getUserIdByEmailAndName(email, name);
		GeneralIdResponse result = new GeneralIdResponse(serviceResult);
		if (result != null && result.getValue().equals(-1L)) {
			logger.debug("it couldn't find a matching user");
			result.setSuccess(false);
		} 
		logger.debug("get was successful, the id is " + result.getValue());
		return result;
	}

	// private methods

	private Long getUserIdIfExist(TrainingExcelDto item) {
		GeneralIdResponse userId = getUserIdByEmailAndName(item.getEmail(), item.getName());
		return userId.getValue();
	}

	private GeneralIdResponse createUser(UserDto user, boolean isUseBasicFields)
			throws ValidationException {
		if (user == null) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		
		stripXssUserDto(user);
		UserServiceValidator.create(user, isUseBasicFields);
		
		addEmailIfEmpty(user);
		
		Long result = null;
		if (isUseBasicFields) {
			result = userManager.create(user.getName(), user.getDisplayName(), user.getDob(), user.getPhoneNo(), user.getEmail(), user.getAddressId());
		} else {
			result = userManager.create(user.getLastName() + user.getFirstName(), user.getDisplayName(), user.getDob(), user.getPhoneNo(), user.getEmail(), user.getAddressId());
		}
		return new GeneralIdResponse(result);
	}
	
	private void addEmailIfEmpty(UserDto user) {
		if (StringUtils.isEmpty(user.getEmail())) {
			String newEmail = Constants.EXCEL_TRAINING_MISSING_EMAIL;
			user.setEmail(newEmail);
		}
	}

	private static void stripXssUserDto(UserDto user) {
		user.setLastName(ValidatorBaseUtility.stripXSS(user.getLastName()));
		user.setFirstName(ValidatorBaseUtility.stripXSS(user.getFirstName()));
		user.setDisplayName(ValidatorBaseUtility.stripXSS(user.getDisplayName()));
		user.setEmail(ValidatorBaseUtility.stripXSS(user.getEmail()));
		user.setPhoneNo(ValidatorBaseUtility.stripXSS(user.getPhoneNo()));
	}
	
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
}
