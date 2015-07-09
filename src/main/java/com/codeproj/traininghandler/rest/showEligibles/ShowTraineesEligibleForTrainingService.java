package com.codeproj.traininghandler.rest.showEligibles;

import org.joda.time.DateTime;
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
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ThDateUtils;

@RestController
@RequestMapping("/manageTraining")
public class ShowTraineesEligibleForTrainingService {
	
	@Autowired
	TrainingTypeService trainingTypeService;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;

	@RequestMapping(value="/getEligibleTraineesByTrainingTypeId/{trainingTypeId}/{trainingComplDate}", method = RequestMethod.GET,headers="Accept=application/json")
	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeIdAndComplDate(@PathVariable("trainingTypeId") Long trainingTypeId, 
			@PathVariable("trainingComplDate")  String trainingComplDateAttr) throws ValidationException {
		
		ShowTraineesEligibleForTrainingValidator.getEligibleTraineesByTrainingTypeIdAndComplDate(trainingTypeId, trainingComplDateAttr);
		
		DateTime trainingComplDate = ThDateUtils.convertStringDateToDateTime(trainingComplDateAttr);
		try {
			trainingTypeService.getTrainingTypeById(trainingTypeId);
		} catch (DatabaseEntityNotFoundException | ValidationException ex) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST);
		}
		return showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(trainingTypeId, trainingComplDate);
	}

	public void setTrainingTypeService(TrainingTypeService trainingTypeService) {
		this.trainingTypeService = trainingTypeService;
	}

	public void setShowTraineesEligibleForTrainingManager(
			ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager) {
		this.showTraineesEligibleForTrainingManager = showTraineesEligibleForTrainingManager;
	}
}
