package com.codeproj.traininghandler.rest.completedTraining;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdListResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;

@RestController
@RequestMapping("/completedTraining")
public class CompletedTrainingService {

	//TODO: add more logs to the code
	private static final Logger logger = Logger.getLogger(CompletedTrainingService.class);

	@Autowired
	public CompletedTrainingManager completedTrainingManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdListResponse create(@RequestBody List<CompletedUserTrainingDto> complatedUserTrainingDtoList) throws ValidationException {
		CompletedTrainingServiceValidator.create(complatedUserTrainingDtoList);
		List<Long> resultValue = new ArrayList<>();
		for (CompletedUserTrainingDto item : complatedUserTrainingDtoList) {
			GeneralIdResponse itemResult = createOne(item);
			resultValue.add(itemResult.getValue());
		}
		return new GeneralIdListResponse(resultValue);
	}
	
	@RequestMapping(value="/createOne", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse createOne(@RequestBody CompletedUserTrainingDto complatedUserTrainingDto) throws ValidationException  {
		BooleanResponse completedUserTrainingCheckResult = isCompletedTrainingExist(complatedUserTrainingDto);
		if (completedUserTrainingCheckResult.getPrimitiveBooleanValue()) {
			return null;
		}
		if (!isUserEligibleToAddTraining(complatedUserTrainingDto)) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_MISSING_PREREQUISITE + complatedUserTrainingDto.toString());
		}
		Long result = completedTrainingManager.create(complatedUserTrainingDto);
		return new GeneralIdResponse(result);
	}

	public BooleanResponse isCompletedTrainingExist(
			CompletedUserTrainingDto newComplTraining) {
		return new BooleanResponse(completedTrainingManager.isCompletedTrainingExist(newComplTraining));
	}
	
	private boolean isUserEligibleToAddTraining(
			CompletedUserTrainingDto complatedUserTrainingDto) {
		return completedTrainingManager.isUserEligibleToAddTraining(complatedUserTrainingDto);
	}

	public void setCompletedTrainingManager(
			CompletedTrainingManager completedTrainingManager) {
		this.completedTrainingManager = completedTrainingManager;
	}
}
