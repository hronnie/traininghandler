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
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/address")
public class AddressService {
	
	@Autowired
	AddressManager addressManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody AddressDto addressDto) throws ValidationException {
		AddressServiceValidator.create(addressDto);
		String city = ValidatorBaseUtility.stripXSS(addressDto.getCity());
		String country = ValidatorBaseUtility.stripXSS(addressDto.getCountry());
		String houseNo = ValidatorBaseUtility.stripXSS(addressDto.getHouseNo());
		String postCode = ValidatorBaseUtility.stripXSS(addressDto.getPostCode());
		String street = ValidatorBaseUtility.stripXSS(addressDto.getStreet());

		Long result = addressManager.create(city, country, houseNo, postCode, street);
		return new GeneralIdResponse(result);
	}

	public AddressManager getAddressManager() {
		return addressManager;
	}

	public void setAddressManager(AddressManager addressManager) {
		this.addressManager = addressManager;
	}

}
