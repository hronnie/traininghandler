package com.codeproj.traininghandler.rest.completedTraining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;

@RestController
@RequestMapping("/completedTraining")
public class CompletedTrainingService {
	
	@Autowired
	public CompletedTrainingManager complatedTrainingManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody List<CompletedUserTrainingDto> complatedUserTrainingDtoList) throws ValidationException {
		CompletedTrainingServiceValidator.create(complatedUserTrainingDtoList);
		Long result = complatedTrainingManager.create(complatedUserTrainingDtoList);
		return new GeneralIdResponse(result);
	}

	public void setComplatedTrainingManager(
			CompletedTrainingManager complatedTrainingManager) {
		this.complatedTrainingManager = complatedTrainingManager;
	}

}
