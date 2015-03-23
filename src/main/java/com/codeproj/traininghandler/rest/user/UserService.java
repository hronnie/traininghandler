package com.codeproj.traininghandler.rest.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.codeproj.traininghandler.util.ThStringUtils;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/user")
public class UserService {
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	AddressService addressService;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody UserDto user) throws ValidationException {
		return createUser(user, true, false);
	}
	
	@RequestMapping(value="/createUserWithAddress", method = RequestMethod.POST,headers="Accept=application/json")
	public Long createUserWithAddress(@RequestBody TrainingExcelDto item) throws ValidationException {
		Long userId = getUserIdIfExist(item);
		if (userId != -1L) {
			return userId;
		}
		
		GeneralIdResponse addressIdResp = addressService.createFromForm(new AddressDto(item.getPostCode(), item.getAddress()));
		GeneralIdResponse userIdResp = createFromForm(new UserDto(item.getName(), item.getPhoneNo(), item.getEmail(), addressIdResp.getValue()));
		return userIdResp.getValue();
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
	
	private Long getUserIdIfExist(TrainingExcelDto item) {
        String email = item.getEmail();
        if (StringUtils.isEmpty(email)) {
                String cleanedPhoneNo = ThStringUtils.cleanPhoneNumber(item.getPhoneNo());
                email = cleanedPhoneNo + Constants.EXCEL_TRAINING_MISSING_EMAIL_DOMAIN;
        }
        GeneralIdResponse userId = getUserIdByEmail(email);
        return userId.getValue();
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
}
