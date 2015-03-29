package com.codeproj.traininghandler.integration;


import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeproj.traininghandler.common.GenericAPITest;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

public class TrainingTypeIT extends GenericAPITest {
	
	RestTemplate restTemplate = null;
	
	public static final Long TEST_GET_TRAINING_TYPE_ID = 1L;
	
	public TrainingTypeIT(String name) {
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
	
	@Test(dependsOnMethods = { "testCreateTrainingType" })
	public void testGetTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started getting Training type");
        
        TrainingTypeDto trainingType = restTemplate.getForObject(getResource("trainingType.get.url") + TEST_GET_TRAINING_TYPE_ID, TrainingTypeDto.class);

		Assert.assertEquals(trainingType.getTrainingTypeId(), TEST_GET_TRAINING_TYPE_ID);
		Assert.assertEquals(trainingType.getName(), getResource("trainingtype.get.name"));
		Assert.assertEquals(trainingType.getLevelNo(), getResource("trainingtype.get.levelNo"));
		Assert.assertEquals(trainingType.getDescription(), "");
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testGetTrainingType" + trainingType.toString());
	}
	
	@Test(dependsOnMethods = { "testCreateTrainingType" })
	public void testGetAllTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started getting all Training type");

		List<TrainingTypeDto> trainingTypeList = restTemplate.getForObject(getResource("trainingType.get.all.url"), List.class);
        assertNotNull("The result is null", trainingTypeList);
        assertTrue("The size of the list should be greater then 0", trainingTypeList.size() > 0);
 
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testGetAllTrainingType");
	}
	
	@Test(dependsOnMethods = { "testGetAllTrainingType" })
	public void testUpdateTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started updating Training type");
        
        restTemplate = new RestTemplate();
        TrainingTypeDto trainingType = new TrainingTypeDto(createdId, 
        			getResource("trainingtype.update.name"), 
        			getResource("trainingtype.update.levelNo"), 
        			getResource("trainingtype.update.description"));
        
        BooleanResponse response = restTemplate.postForObject(getResource("trainingType.update.url"), trainingType, BooleanResponse.class);

        Assert.assertTrue(response.getPrimitiveBooleanValue(), "Update wasn't successful");

        // get updated training type and assert
        
        TrainingTypeDto getTrainingTypeResp = restTemplate.getForObject(getResource("trainingType.get.url") + createdId, TrainingTypeDto.class);
        
		Assert.assertEquals(getTrainingTypeResp.getTrainingTypeId(), createdId);
		Assert.assertEquals(getTrainingTypeResp.getName(), getResource("trainingtype.update.name"));
		Assert.assertEquals(getTrainingTypeResp.getLevelNo(), getResource("trainingtype.update.levelNo"));
		Assert.assertEquals(getTrainingTypeResp.getDescription(), getResource("trainingtype.update.description"));
        
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testUpdateTrainingType" + getTrainingTypeResp.toString());
    }
	
//	@Test(dependsOnMethods = { "testUpdateTrainingType" })
//	public void testDeleteTrainingType() {
//        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started deleting Training type");
//        
//        TrainingTypeDto trainingType = new TrainingTypeDto(createdId, null, null, null);
//        BooleanResponse response = restTemplate.postForObject(getResource("trainingType.delete.url"), trainingType, BooleanResponse.class);
//        Assert.assertTrue(response.getPrimitiveBooleanValue(), "Delete wasn't successful");
//
//        // get updated training type and assert
//        
//        Assert.assertTrue(confirmObjectNotFound(getRequestReturnTextResponse("trainingType.get.url", true, createdId.toString())), "Object still found after delete");
//        
//        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testDeleteTrainingType");
//
//	}

}
