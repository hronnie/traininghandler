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
		if (user == null) {
			throw new ValidationException("Input User dto is null");
		}

		String lastName = ValidatorBaseUtility.stripXSS(user.getLastName());
		String firstName = ValidatorBaseUtility.stripXSS(user.getFirstName());
		String displayName = ValidatorBaseUtility.stripXSS(user.getDisplayName());
		String email = ValidatorBaseUtility.stripXSS(user.getEmail());
		String phoneNumber = ValidatorBaseUtility.stripXSS(user.getPhoneNo());

		UserServiceValidator.create(lastName, firstName, displayName, user.getDob(), phoneNumber, email, user.getAddressId());
		
		Long result = userManager.create(lastName + firstName, displayName, user.getDob(), phoneNumber, email, user.getAddressId());
		return new GeneralIdResponse(result);
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * 
	 * @return userId if user exists, -1 otherwise
	 */
	public GeneralIdResponse getUserIdByEmail(String email) {
		// TODO implement method
		return null;
	}

	public GeneralIdResponse createFromForm(UserDto userDto) {
		// TODO implement method
		return null;
	}

}
