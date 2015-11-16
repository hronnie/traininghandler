package com.codeproj.traininghandler.rest.address;

import static com.codeproj.traininghandler.util.Constants.*;

import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

public class AddressServiceValidator extends ValidatorBaseUtility {
	public static void create(
			AddressDto addressDto) throws ValidationException {

		//TODO: refactor: check wether it's needed or not
		mandatoryParameter(VALIDATION_PARAMETER_POSTCODE, addressDto.getPostCode());
		mandatoryParameter(VALIDATION_PARAMETER_CITY, addressDto.getCity());
		mandatoryParameter(VALIDATION_PARAMETER_STREET, addressDto.getStreet());
		mandatoryParameter(VALIDATION_PARAMETER_HOUSE_NO, addressDto.getHouseNo());
		mandatoryParameter(VALIDATION_PARAMETER_COUNTRY, addressDto.getCountry());
		
		validateStringLength(VALIDATION_PARAMETER_POSTCODE, addressDto.getPostCode(), TABLE_FIELD_SIZE_POSTCODE);
		validateStringLength(VALIDATION_PARAMETER_CITY, addressDto.getCity(), TABLE_FIELD_SIZE_CITY);
		validateStringLength(VALIDATION_PARAMETER_STREET, addressDto.getStreet(), TABLE_FIELD_SIZE_STREET);
		validateStringLength(VALIDATION_PARAMETER_HOUSE_NO, addressDto.getHouseNo(), TABLE_FIELD_SIZE_HOUSE_NO);
		validateStringLength(VALIDATION_PARAMETER_COUNTRY, addressDto.getCountry(), TABLE_FIELD_SIZE_COUNTRY);
	}

	public static void createAddressFromExcel(TrainingExcelDto item) throws ValidationException {
		validateStringLength(VALIDATION_PARAMETER_POSTCODE, item.getPostCode(), TABLE_FIELD_SIZE_POSTCODE);
		validateStringLength(VALIDATION_PARAMETER_ADDRESS, item.getAddress(), TABLE_FIELD_SIZE_ADDRESS);
	}

}
