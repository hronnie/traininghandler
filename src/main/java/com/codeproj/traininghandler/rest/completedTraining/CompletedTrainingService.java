package com.codeproj.traininghandler.rest.completedTraining;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.rest.common.GeneralIdListResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RestController
@RequestMapping("/completedTraining")
public class CompletedTrainingService {
	
	@Autowired
	public CompletedTrainingManager completedTrainingManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdListResponse create(@RequestBody List<CompletedUserTrainingDto> complatedUserTrainingDtoList) throws ValidationException {
		CompletedTrainingServiceValidator.create(complatedUserTrainingDtoList);
		List<Long> resultValue = new ArrayList<>();
		for (CompletedUserTrainingDto item : complatedUserTrainingDtoList) {
			GeneralIdResponse itemResult = createCompletedTraining(item);
			resultValue.add(itemResult.getValue());
		}
		return new GeneralIdListResponse(resultValue);
	}
	
	public GeneralIdResponse create(CompletedUserTrainingDto complatedUserTrainingDto) throws ValidationException {
		return createCompletedTraining(complatedUserTrainingDto);
	}

	private GeneralIdResponse createCompletedTraining(CompletedUserTrainingDto complatedUserTrainingDto)
			throws ValidationException {
		Long result = completedTrainingManager.create(complatedUserTrainingDto);
		return new GeneralIdResponse(result);
	}

	public void setCompletedTrainingManager(
			CompletedTrainingManager completedTrainingManager) {
		this.completedTrainingManager = completedTrainingManager;
	}

}
