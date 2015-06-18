package com.codeproj.traininghandler.rest.address;

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
	
	@Autowired
	AddressManager addressManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody AddressDto addressDto) throws ValidationException {
		return createAddress(addressDto, true);
	}

	private GeneralIdResponse createAddress(AddressDto addressDto, boolean isNeedValidation)
			throws ValidationException {
		if (addressDto == null) {
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
		Long result = addressManager.create(city, country, houseNo, postCode, street, fullAddress);
		return new GeneralIdResponse(result);
	}
	
	public GeneralIdResponse createFromForm(AddressDto addressDto) throws ValidationException {
		return createAddress(addressDto, false);
	}

	public void setAddressManager(AddressManager addressManager) {
		this.addressManager = addressManager;
	}

}
