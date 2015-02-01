package com.codeproj.traininghandler.rest.showEligibles;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.rest.trainingtype.TrainingTypeService;

public class ShowTraineesEligibleForTrainingService {
	
	@Autowired
	TrainingTypeService trainingTypeService;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager manager;

	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeId(Long trainingTypeId) throws ValidationException {
		try {
			trainingTypeService.getTrainingTypeById(trainingTypeId);
		} catch (DatabaseEntityNotFoundException | ValidationException ex) {
			throw new ValidationException("Training type doesn't exist");
		}
		return manager.getEligibleTraineesByTrainingTypeId(trainingTypeId);
	}

	public void setTrainingTypeService(TrainingTypeService trainingTypeService) {
		this.trainingTypeService = trainingTypeService;
	}

	public void setManager(ShowTraineesEligibleForTrainingManager manager) {
		this.manager = manager;
	}

}
