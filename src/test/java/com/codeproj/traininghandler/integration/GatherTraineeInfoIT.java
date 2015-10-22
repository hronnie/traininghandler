package com.codeproj.traininghandler.integration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeproj.traininghandler.common.GenericAPITest;
import com.codeproj.traininghandler.dto.AddressDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineePersonalAndTrainingInfoDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.rest.common.BooleanResponse;

public class GatherTraineeInfoIT extends GenericAPITest {

	
	RestTemplate restTemplate = null;
	public static final Date VALID_DOB;
	public static final Date VALID_COMPL1;
	public static final Date VALID_COMPL2;
	
	static {
		DateTime dt = new DateTime(2000, 3, 26, 12, 0, 0, 0);
		DateTime dt1 = new DateTime(2011, 3, 26, 12, 0, 0, 0);
		DateTime dt2 = new DateTime(2012, 3, 26, 12, 0, 0, 0);
		VALID_DOB = dt.toDate();
		VALID_COMPL1 = dt1.toDate();
		VALID_COMPL2 = dt2.toDate();
	}
	
	public GatherTraineeInfoIT(String name) {
		super(name);
		restTemplate = new RestTemplate();
	}

	@Test()
	public void testCreateTrainingType() {
        Logger.getLogger(GatherTraineeInfoIT.class.getName()).log(Level.INFO, "Started creating trainee info");
        restTemplate = new RestTemplate();
        AddressDto address = new AddressDto(getResource("gatherTraineeInfo.address.postCode"),
        		getResource("gatherTraineeInfo.address.city"),
        		getResource("gatherTraineeInfo.address.street"),
        		getResource("gatherTraineeInfo.address.houseNo"),
        		getResource("gatherTraineeInfo.address.country"));
        UserDto user = new UserDto(
        		getResource("gatherTraineeInfo.user.lastName"),
        		getResource("gatherTraineeInfo.user.firstName"),
        		getResource("gatherTraineeInfo.user.displayName"),
        		VALID_DOB,
        		getResource("gatherTraineeInfo.user.phoneNo"),
        		getResource("gatherTraineeInfo.user.email"),
        		null);
        
        List<CompletedUserTrainingDto> completedUserTrainingList = new ArrayList<>();
        CompletedUserTrainingDto cut1 = new CompletedUserTrainingDto(1L,
        		1L,
        		VALID_COMPL1);
        CompletedUserTrainingDto cut2 = new CompletedUserTrainingDto(1L,
        		2L,
        		VALID_COMPL1);
        completedUserTrainingList.add(cut1);
        completedUserTrainingList.add(cut2);
        
        TraineePersonalAndTrainingInfoDto traineeInfoDto = new TraineePersonalAndTrainingInfoDto(address, user, completedUserTrainingList);
        
        BooleanResponse response = restTemplate.postForObject(getResource("gatherTraineeInfo.create.url"), traineeInfoDto, BooleanResponse.class);

        Assert.assertTrue(response.getBooleanValue(), "Create wasn't successful");
        Logger.getLogger(GatherTraineeInfoIT.class.getName()).log(Level.INFO, "Finished testCreateTrainingType" + response.toString());
	}

}
