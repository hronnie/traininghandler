package com.codeproj.traininghandler.integration;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import com.codeproj.traininghandler.common.GenericAPITest;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;

public class ShowEligibleUsersIT extends GenericAPITest {
	
	RestTemplate restTemplate = null;

	public ShowEligibleUsersIT(String name) {
		super(name);
		restTemplate = new RestTemplate();
	}
	
	@Test()
	public void testShowTraineesForTraining1() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started to show eligible trainees for training 1");
        
        TraineesEligibleForTrainingDto response = restTemplate.getForObject(getResource("showEligibleTrainees.get.url") + "/1", TraineesEligibleForTrainingDto.class);
        List<UserDto> emailUsers = response.getHasEmailUsers();
        List<UserDto> phoneUsers = response.getOnlyPhoneUsers();
        assertEquals("The number of email users is incorrectq", 6, emailUsers.size());
        assertEquals("The number of phone users is incorrectq", 4, phoneUsers.size());
        
        UserDto user0 = emailUsers.get(0);
        assertEquals("Got an unexpected user (wrong name)", "No training", user0.getName());
        assertEquals("Got an unexpected user (wrong email)", "email1@address.com", user0.getEmail());
        assertEquals("Got an unexpected user (wrong phone number)", "00000000018 1", user0.getPhoneNo());
        UserDto user1 = emailUsers.get(1);
        assertEquals("Got an unexpected user (wrong name)", "No training", user1.getName());
        assertEquals("Got an unexpected user (wrong email)", "email2@address.com", user1.getEmail());
        assertEquals("Got an unexpected user (wrong phone number)", "00000000018 1", user1.getPhoneNo());
        UserDto user2 = emailUsers.get(2);
        assertEquals("Got an unexpected user (wrong name)", "No training", user2.getName());
        assertEquals("Got an unexpected user (wrong email)", "email3@address.com", user2.getEmail());
        assertEquals("Got an unexpected user (wrong phone number)", "00000000018 1", user2.getPhoneNo());
        UserDto user3 = emailUsers.get(3);
        assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev1", user3.getName());
        assertEquals("Got an unexpected user (wrong email)", "5ostanfolyam@mail.com1", user3.getEmail());
        assertEquals("Got an unexpected user (wrong phone number)", "5555555555", user3.getPhoneNo());
        UserDto user4 = emailUsers.get(4);
        assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev 2", user4.getName());
        assertEquals("Got an unexpected user (wrong email)", "5ostanfolyam@mail.com2", user4.getEmail());
        assertEquals("Got an unexpected user (wrong phone number)", "5555555556", user4.getPhoneNo());
        UserDto user5 = emailUsers.get(5);
        assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev3", user5.getName());
        assertEquals("Got an unexpected user (wrong email)", "5ostanfolyam@mail.com3", user5.getEmail());
        assertEquals("Got an unexpected user (wrong phone number)", "5555555557", user5.getPhoneNo());
        assertEquals("The number of phone users is incorrectq", 4, phoneUsers.size());
        
        UserDto user6 = phoneUsers.get(0);
        assertEquals("Got an unexpected user (wrong name)", "No training", user6.getName());
        assertEquals("Got an unexpected user (wrong phone number)", "444444444", user6.getPhoneNo());
        UserDto user7 = phoneUsers.get(1);
        assertEquals("Got an unexpected user (wrong name)", "No training", user7.getName());
        assertEquals("Got an unexpected user (wrong phone number)", "5555555551", user7.getPhoneNo());
        UserDto user8 = phoneUsers.get(2);
        assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev4", user8.getName());
        assertEquals("Got an unexpected user (wrong phone number)", "5555555558", user8.getPhoneNo());
        UserDto user9 = phoneUsers.get(3);
        assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev5", user9.getName());
        assertEquals("Got an unexpected user (wrong phone number)", "5555555559", user9.getPhoneNo());
        
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished to show eligible trainees for training 1");
	}
	
	@Test()
	public void testShowTraineesForTraining2() {
		Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started to show eligible trainees for training 2");
		
		TraineesEligibleForTrainingDto response = restTemplate.getForObject(getResource("showEligibleTrainees.get.url") + "/2", TraineesEligibleForTrainingDto.class);
		List<UserDto> emailUsers = response.getHasEmailUsers();
		List<UserDto> phoneUsers = response.getOnlyPhoneUsers();
		assertEquals("The number of email users is incorrectq", 3, emailUsers.size());
		assertEquals("The number of phone users is incorrectq", 2, phoneUsers.size());
		
		UserDto user0 = emailUsers.get(0);
		assertEquals("Got an unexpected user (wrong name)", "1es regen1", user0.getName());
		assertEquals("Got an unexpected user (wrong email)", "regen1es@example.com1", user0.getEmail());
		assertEquals("Got an unexpected user (wrong phone number)", "111111111 1", user0.getPhoneNo());
		
		UserDto user1 = emailUsers.get(1);
		assertEquals("Got an unexpected user (wrong name)", "1es regen2", user1.getName());
		assertEquals("Got an unexpected user (wrong email)", "regen1es@example.com2", user1.getEmail());
		assertEquals("Got an unexpected user (wrong phone number)", "111111111 2", user1.getPhoneNo());
		
		UserDto user2 = emailUsers.get(2);
		assertEquals("Got an unexpected user (wrong name)", "1es regen3", user2.getName());
		assertEquals("Got an unexpected user (wrong email)", "regen1es@example.com3", user2.getEmail());
		assertEquals("Got an unexpected user (wrong phone number)", "111111111 3", user2.getPhoneNo());
		
		UserDto user3 = phoneUsers.get(0);
		assertEquals("Got an unexpected user (wrong name)", "1es regen4", user3.getName());
		assertEquals("Got an unexpected user (wrong phone number)", "1111111114", user3.getPhoneNo());
		
		UserDto user4 = phoneUsers.get(1);
		assertEquals("Got an unexpected user (wrong name)", "1es regen5", user4.getName());
		assertEquals("Got an unexpected user (wrong phone number)", "111111111 5", user4.getPhoneNo());
		
		Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished to show eligible trainees for training 1");
	}
	
	@Test()
	public void testShowTraineesForTrainingTk2() {
		Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started to show eligible trainees for training tk2");
		
		TraineesEligibleForTrainingDto response = restTemplate.getForObject(getResource("showEligibleTrainees.get.url") + "/27", TraineesEligibleForTrainingDto.class);
		List<UserDto> emailUsers = response.getHasEmailUsers();
		List<UserDto> phoneUsers = response.getOnlyPhoneUsers();
		assertEquals("The number of email users is incorrectq", 3, emailUsers.size());
		assertEquals("The number of phone users is incorrectq", 2, phoneUsers.size());
		
		UserDto user0 = emailUsers.get(0);
		assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev1", user0.getName());
		assertEquals("Got an unexpected user (wrong email)", "5ostanfolyam@mail.com1", user0.getEmail());
		assertEquals("Got an unexpected user (wrong phone number)", "5555555555", user0.getPhoneNo());
		
		UserDto user1 = emailUsers.get(1);
		assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev 2", user1.getName());
		assertEquals("Got an unexpected user (wrong email)", "5ostanfolyam@mail.com2", user1.getEmail());
		assertEquals("Got an unexpected user (wrong phone number)", "5555555556", user1.getPhoneNo());
		
		UserDto user2 = emailUsers.get(2);
		assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev3", user2.getName());
		assertEquals("Got an unexpected user (wrong email)", "5ostanfolyam@mail.com3", user2.getEmail());
		assertEquals("Got an unexpected user (wrong phone number)", "5555555557", user2.getPhoneNo());
		
		UserDto user3 = phoneUsers.get(0);
		assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev4", user3.getName());
		assertEquals("Got an unexpected user (wrong phone number)", "5555555558", user3.getPhoneNo());
		
		UserDto user4 = phoneUsers.get(1);
		assertEquals("Got an unexpected user (wrong name)", "5os es tk1 any tanfolyam nev5", user4.getName());
		assertEquals("Got an unexpected user (wrong phone number)", "5555555559", user4.getPhoneNo());		Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testRegisterMinimal" + response.toString());
		Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished to show eligible trainees for training tk2");
	}

}
