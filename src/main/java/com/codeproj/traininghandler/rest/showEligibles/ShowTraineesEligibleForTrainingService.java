package com.codeproj.traininghandler.rest.showEligibles;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.rest.trainingtype.TrainingTypeService;

@RestController
@RequestMapping("/manageTraining")
public class ShowTraineesEligibleForTrainingService {
	
	private static final Logger logger = Logger.getLogger(ShowTraineesEligibleForTrainingService.class);
	
	@Autowired
	TrainingTypeService trainingTypeService;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;

	@RequestMapping(value="/getEligibleTraineesByTrainingTypeId/{trainingTypeId}", method = RequestMethod.GET,headers="Accept=application/json")
	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeId(@PathVariable("trainingTypeId") Long trainingTypeId) {
		try {
			logger.debug("getEligibleTraineesByTrainingTypeId with trainingTypeId >> " +  trainingTypeId);
			ShowTraineesEligibleForTrainingValidator.getEligibleTraineesByTrainingTypeIdAndComplDate(trainingTypeId);
			
			TrainingTypeDto trainingType = trainingTypeService.getTrainingTypeById(trainingTypeId);
			if (!trainingType.getSuccess()) {
				logger.debug("getEligibleTraineesByTrainingTypeId failed >> " +  trainingType.getMessage());
				return new TraineesEligibleForTrainingDto(trainingType.getMessage());
			}
			TraineesEligibleForTrainingDto result = showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(trainingTypeId, new DateTime().withTimeAtStartOfDay()); 
			logger.debug("getEligibleTraineesByTrainingTypeId succeed >> " +  result);
			return result;
		} catch (ValidationException ve) {
			return new TraineesEligibleForTrainingDto(ve.getMessage());
		}
	}

	public void setTrainingTypeService(TrainingTypeService trainingTypeService) {
		this.trainingTypeService = trainingTypeService;
	}

	public void setShowTraineesEligibleForTrainingManager(
			ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager) {
		this.showTraineesEligibleForTrainingManager = showTraineesEligibleForTrainingManager;
	}
}
