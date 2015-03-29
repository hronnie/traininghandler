package com.codeproj.traininghandler.integration;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeproj.traininghandler.common.GenericAPITest;
import com.codeproj.traininghandler.dto.TrainingExcelDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

public class UserServiceIT extends GenericAPITest {
	
	RestTemplate restTemplate = null;

	public UserServiceIT(String name) {
		super(name);
		restTemplate = new RestTemplate();
	}
	
	//@Test()
//	public void testCreate() {
//        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started creating User");
//        restTemplate = new RestTemplate();
//        UserDto userDto = new UserDto(getResource("userService.user.name"), getResource("userService.user.phoneNo"), getResource("userService.user.email"), new Long(getResource("userService.user.addressId")));
//
//        GeneralIdResponse response = restTemplate.postForObject(getResource("userService.create.url"), userDto, GeneralIdResponse.class);
//
//        Assert.assertTrue(validateGenerateIdResponse(response), "Create wasn't successful");
//        Logger.getLogger(UserServiceIT.class.getName()).log(Level.INFO, "Finished create user" + response.toString());
//	}
	
	@Test()
	public void testCreateUserWithAddress() {
		Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started creating User with address");
		restTemplate = new RestTemplate();

		TrainingExcelDto trainingExcelDto = new TrainingExcelDto(getResource("userService.createUserWithAddress.name"), 
				getResource("userService.createUserWithAddress.postCode"), 
				getResource("userService.createUserWithAddress.address"), 
				getResource("userService.createUserWithAddress.phoneNo"), 
				getResource("userService.createUserWithAddress.email"));

		
		GeneralIdResponse response = restTemplate.postForObject(getResource("userService.createUserWithAddress.url"), trainingExcelDto, GeneralIdResponse.class);
		
		Assert.assertTrue(validateGenerateIdResponse(response), "Create wasn't successful");
		Logger.getLogger(UserServiceIT.class.getName()).log(Level.INFO, "Finished create user with address" + response.toString());
	}
}
