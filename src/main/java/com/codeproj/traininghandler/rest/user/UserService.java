package com.codeproj.traininghandler.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.user.UserManager;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/user")
public class UserService {
	
	@Autowired
	UserManager userManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody UserDto user) throws ValidationException {
		return createUser(user, true, false);
	}

	public GeneralIdResponse createFromForm(UserDto userDto) throws ValidationException {
		return createUser(userDto, false, true);
	}
	
	public GeneralIdResponse getUserIdByEmail(String email) {
		Long result = userManager.getUserIdByEmail(email);
		return new GeneralIdResponse(result);
	}

	private GeneralIdResponse createUser(UserDto user, boolean isNeedValidate, boolean isUseFullName)
			throws ValidationException {
		if (user == null) {
			throw new ValidationException("Input User dto is null");
		}
		
		stripXssUserDto(user);
		if (isNeedValidate) {user.getName();
			UserServiceValidator.create(user.getLastName(), user.getFirstName(), user.getDisplayName(), user.getDob(), user.getPhoneNo(), user.getEmail(), user.getAddressId());
		}
		
		Long result = null;
		if (isUseFullName) {
			result = userManager.create(user.getName(), user.getDisplayName(), user.getDob(), user.getPhoneNo(), user.getEmail(), user.getAddressId());
		} else {
			result = userManager.create(user.getLastName() + user.getFirstName(), user.getDisplayName(), user.getDob(), user.getPhoneNo(), user.getEmail(), user.getAddressId());
		}
		return new GeneralIdResponse(result);
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
}
