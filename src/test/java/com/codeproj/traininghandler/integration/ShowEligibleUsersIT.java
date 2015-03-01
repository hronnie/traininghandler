package com.codeproj.traininghandler.integration;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeproj.traininghandler.common.GenericAPITest;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

public class ShowEligibleUsersIT extends GenericAPITest {
	
	RestTemplate restTemplate = null;

	public ShowEligibleUsersIT(String name) {
		super(name);
		restTemplate = new RestTemplate();
	}
	
	@Test()
	public void testCreateTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started creating Training type");
        restTemplate = new RestTemplate();
        TrainingTypeDto trainingType = new TrainingTypeDto(0L, 
        			getResource("trainingtype.create.name"), 
        			getResource("trainingtype.create.levelNo"), 
        			getResource("trainingtype.create.description"));
        
        GeneralIdResponse response = restTemplate.postForObject(getResource("trainingType.create.url"), trainingType, GeneralIdResponse.class);

        Assert.assertTrue(validateGenerateIdResponse(response), "Create wasn't successful");
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testRegisterMinimal" + response.toString());
	}

}
