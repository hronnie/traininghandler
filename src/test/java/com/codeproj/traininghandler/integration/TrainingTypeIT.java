package com.codeproj.traininghandler.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.codehaus.jettison.json.JSONObject;

import com.codeproj.traininghandler.common.GenericAPITest;

public class TrainingTypeIT extends GenericAPITest {
	
	public TrainingTypeIT(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testCreateTrainingType() {
        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Started testRegisterMinimal");

        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("name", getResource("trainingtype.create.name")));
        nameValuePairs.add(new BasicNameValuePair("levelNo", getResource("trainingtype.create.levelNo")));
        nameValuePairs.add(new BasicNameValuePair("description", getResource("trainingtype.create.description")));

        JSONObject responseObj = postRequest("trainingType.create.url", nameValuePairs, true);
//        JSONObject token = (JSONObject) getFieldValue(responseObj, "token");
//        assertNotNull(getFieldValue(token, "key"));

        Logger.getLogger(TrainingTypeIT.class.getName()).log(Level.INFO, "Finished testRegisterMinimal" + responseObj.toString());
	}

}
