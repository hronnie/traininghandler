package com.codeproj.traininghandler.rest.showEligibles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.rest.trainingtype.TrainingTypeService;

@RestController
@RequestMapping("/manageTraining")
public class ShowTraineesEligibleForTrainingService {
	
	@Autowired
	TrainingTypeService trainingTypeService;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;

	@RequestMapping(value="/getEligibleTraineesByTrainingTypeId/{trainingTypeId}", method = RequestMethod.GET,headers="Accept=application/json")
	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeId(@PathVariable("trainingTypeId") Long trainingTypeId) throws ValidationException {
		try {
			trainingTypeService.getTrainingTypeById(trainingTypeId);
		} catch (DatabaseEntityNotFoundException | ValidationException ex) {
			throw new ValidationException("Training type doesn't exist");
		}
		return showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeId(trainingTypeId);
	}

	public void setTrainingTypeService(TrainingTypeService trainingTypeService) {
		this.trainingTypeService = trainingTypeService;
	}

	public void setShowTraineesEligibleForTrainingManager(
			ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager) {
		this.showTraineesEligibleForTrainingManager = showTraineesEligibleForTrainingManager;
	}



}
