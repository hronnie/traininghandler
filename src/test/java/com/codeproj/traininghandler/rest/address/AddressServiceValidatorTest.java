package com.codeproj.traininghandler.rest.address;

import static com.codeproj.traininghandler.util.Constants.PARAMETER_STRING_SIZE_MORE_THEN_100;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.exceptions.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceValidatorTest {
	
	public static final String VALID_POST_CODE = "SDF33";
	public static final String VALID_ONE_LINE_ADDRESS = "Street name 5, London";


	@Test
	public void testCreateAddressFromExcelNullPostCode() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, null, VALID_ONE_LINE_ADDRESS, null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}

	@Test
	public void testCreateAddressFromExcelNullAddress() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, VALID_POST_CODE, null, null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}
	
	@Test
	public void testCreateAddressFromExcelEmptyPostCode() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, "", VALID_ONE_LINE_ADDRESS, null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}
	
	@Test
	public void testCreateAddressFromExcelEmptyAddress() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, VALID_POST_CODE, "", null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressFromExcelTooLondPostCode() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, PARAMETER_STRING_SIZE_MORE_THEN_100, VALID_ONE_LINE_ADDRESS, null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}
	
	@Test(expected = ValidationException.class)
	public void testCreateAddressFromExcelTooLongAddress() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, VALID_POST_CODE, PARAMETER_STRING_SIZE_MORE_THEN_100, null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}

	@Test
	public void testCreateAddressFromExcelValidData() throws ValidationException {
		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(null, VALID_POST_CODE, VALID_ONE_LINE_ADDRESS, null, null);
		AddressServiceValidator.createAddressFromExcel(trainingExcelDto);
	}
	
}
