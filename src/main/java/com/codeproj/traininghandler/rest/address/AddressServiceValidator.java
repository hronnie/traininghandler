package com.codeproj.traininghandler.rest.address;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class AddressServiceValidator extends ValidatorBaseUtility {
	public static void create(
			AddressDto addressDto) throws ValidationException {

		entityIdValidator(addressDto.getUserId());
		
		mandatoryParameter("userId", addressDto.getUserId());
		mandatoryParameter("postCode", addressDto.getPostCode());
		mandatoryParameter("city", addressDto.getCity());
		mandatoryParameter("street", addressDto.getStreet());
		mandatoryParameter("houseNo", addressDto.getHouseNo());
		mandatoryParameter("country", addressDto.getCountry());
		
		validateStringLength("postCode", addressDto.getPostCode(), 15);
		validateStringLength("city", addressDto.getCity(), 50);
		validateStringLength("street", addressDto.getStreet(), 70);
		validateStringLength("houseNo", addressDto.getHouseNo(), 10);
		validateStringLength("country", addressDto.getCountry(), 50);
	}

}
