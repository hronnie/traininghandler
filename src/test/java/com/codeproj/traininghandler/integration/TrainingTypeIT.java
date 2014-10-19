package com.codeproj.traininghandler.integration;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeproj.traininghandler.common.GenericAPITest;

public class TrainingTypeIT extends GenericAPITest {
	
	public static Long var;
	
	public TrainingTypeIT(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Test()
	public void testCreateTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started creating Training type");

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("name", getResource("trainingtype.create.name")));
        nameValuePairs.add(new BasicNameValuePair("levelNo", getResource("trainingtype.create.levelNo")));
        nameValuePairs.add(new BasicNameValuePair("description", getResource("trainingtype.create.description")));

        JSONObject responseObj = postRequest("trainingType.create.url", nameValuePairs, true);
        Assert.assertTrue(validateGenerateIdResponse(responseObj), "Create wasn't successful");
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testRegisterMinimal" + responseObj.toString());
	}
	
	@Test(dependsOnMethods = { "testCreateTrainingType" })
	public void testGetTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started getting Training type");

        JSONObject responseObj = getRequest("trainingType.get.url", true, createdId.toString());
        try {
        	Integer trainingTypeId = (Integer)responseObj.get("trainingTypeId");
			Assert.assertEquals(new Long(trainingTypeId), createdId);
			Assert.assertEquals(responseObj.get("name"), getResource("trainingtype.create.name"));
			Assert.assertEquals(responseObj.get("levelNo"), getResource("trainingtype.create.levelNo"));
			Assert.assertEquals(responseObj.get("description"), getResource("trainingtype.create.description"));
		} catch (JSONException e) {
			Assert.fail("Invalid JSON during getting training type with id: " + createdId);
		}
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testGetTrainingType" + responseObj.toString());
	}
	
	@Test(dependsOnMethods = { "testCreateTrainingType" })
	public void testGetAllTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started getting all Training type");

        String responseText = getRequestReturnTextResponse("trainingType.get.all.url", true, null);
        Assert.assertNotNull(responseText);
        Assert.assertTrue(responseText.startsWith("[{"), "Response is not an array at get all training type");;
        
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testGetTrainingType" + responseText);
	}
	
	@Test(dependsOnMethods = { "testGetAllTrainingType" })
	public void testUpdateTrainingType() {
	}
	
	@Test(dependsOnMethods = { "testUpdateTrainingType" })
	public void testDeleteTrainingType() {
	}

}
