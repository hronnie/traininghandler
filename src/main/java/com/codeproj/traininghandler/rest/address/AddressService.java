package com.codeproj.traininghandler.rest.address;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.address.AddressManager;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/address")
public class AddressService {
	
	private static final Logger logger = Logger.getLogger(AddressService.class);
	
	@Autowired
	AddressManager addressManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody AddressDto addressDto) {
		logger.debug("create with >>" + addressDto);
		Long newAddressId = null;
		try {
			newAddressId = createAddress(addressDto, true);
		} catch (ValidationException ve) {
			return new GeneralIdResponse(ve.getMessage()); 
		}
		return new GeneralIdResponse(newAddressId);
	}

	private Long createAddress(AddressDto addressDto, boolean isNeedValidation)
			throws ValidationException {
		logger.debug("createAddress with >>" + addressDto + ", isNeedValidation>> " + isNeedValidation);
		if (addressDto == null) {
			logger.error("createAddress, addressDto is null");
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		if (isNeedValidation) {
			AddressServiceValidator.create(addressDto);
		}
		String city = ValidatorBaseUtility.stripXSS(addressDto.getCity());
		String country = ValidatorBaseUtility.stripXSS(addressDto.getCountry());
		String houseNo = ValidatorBaseUtility.stripXSS(addressDto.getHouseNo());
		String postCode = ValidatorBaseUtility.stripXSS(addressDto.getPostCode());
		String street = ValidatorBaseUtility.stripXSS(addressDto.getStreet());
		String fullAddress = ValidatorBaseUtility.stripXSS(addressDto.getAddress());
		return addressManager.create(city, country, houseNo, postCode, street, fullAddress);
	}
	
	public GeneralIdResponse createFromForm(AddressDto addressDto) {
		logger.debug("createFromForm with >> " + addressDto);
		Long newAddressId = null;
		try {
			newAddressId = createAddress(addressDto, false);
		} catch (ValidationException ve) {
			return new GeneralIdResponse(ve.getMessage()); 
		}
		return new GeneralIdResponse(newAddressId);
	}

	public void setAddressManager(AddressManager addressManager) {
		this.addressManager = addressManager;
	}

}
