package com.codeproj.traininghandler.rest.completedTraining;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDtos;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.model.CompletedUserTraining;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdListResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;

@RestController
@RequestMapping("/completedTraining")
public class CompletedTrainingService {

	private static final Logger logger = Logger.getLogger(CompletedTrainingService.class);

	@Autowired
	public CompletedTrainingManager completedTrainingManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdListResponse create(@RequestBody List<CompletedUserTrainingDto> complatedUserTrainingDtoList) {
		logger.debug("create with>> " + complatedUserTrainingDtoList);
		try {
			CompletedTrainingServiceValidator.create(complatedUserTrainingDtoList);
			List<Long> resultValue = new ArrayList<>();
			for (CompletedUserTrainingDto item : complatedUserTrainingDtoList) {
				GeneralIdResponse itemResult = createOne(item);
				resultValue.add(itemResult.getValue());
			}
			GeneralIdListResponse result = new GeneralIdListResponse(resultValue);
			logger.debug("result >> " + result);
			return result;
		} catch (ValidationException ve) {
			return new GeneralIdListResponse(ve.getMessage());
		}
	}
	
	@RequestMapping(value="/createOne", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse createOne(@RequestBody CompletedUserTrainingDto complatedUserTrainingDto) {
		try {
			logger.debug("createOne with>> " + complatedUserTrainingDto);
			CompletedTrainingServiceValidator.createOne(complatedUserTrainingDto);
			
			BooleanResponse completedUserTrainingCheckResult = isCompletedTrainingExist(complatedUserTrainingDto);
			if (completedUserTrainingCheckResult.getBooleanValue()) {
				logger.info(Constants.VALIDATION_ERR_MSG_USER_TRAINING_COMPL_ALREADY_EXISTS);
				return new GeneralIdResponse(Constants.VALIDATION_ERR_MSG_USER_TRAINING_COMPL_ALREADY_EXISTS);
			}
			
			if (complatedUserTrainingDto.getSkipPrereqCheck()) {
				logger.debug("Prereqs were skipped");
				return new GeneralIdResponse(completedTrainingManager.create(complatedUserTrainingDto));
			}
			
			if (!isUserEligibleToAddTraining(complatedUserTrainingDto)) {
				logger.info(Constants.VALIDATION_ERR_MSG_MISSING_PREREQUISITE);
				return new GeneralIdResponse(Constants.VALIDATION_ERR_MSG_MISSING_PREREQUISITE);
			}
			Long result = completedTrainingManager.create(complatedUserTrainingDto);
			return new GeneralIdResponse(result);
		} catch (ValidationException ve) {
			return new GeneralIdResponse(ve.getMessage()); 
		}
	}

	public BooleanResponse isCompletedTrainingExist(
			CompletedUserTrainingDto newComplTraining) {
		logger.debug("isCompletedTrainingExist with>> " + newComplTraining);
		
		return new BooleanResponse(completedTrainingManager.isCompletedTrainingExist(newComplTraining), true);
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse update(@RequestBody CompletedUserTrainingDto complatedUserTrainingDto) {
		try {
			logger.debug("update with>> " + complatedUserTrainingDto);
			CompletedTrainingServiceValidator.update(complatedUserTrainingDto);
			boolean resultBool = completedTrainingManager.update(complatedUserTrainingDto);
			BooleanResponse result = new BooleanResponse(resultBool, true);
			logger.debug("update was successful >> " + result);
			return result;
		} catch (ValidationException ve) {
			return new BooleanResponse(ve.getMessage()); 
		}
	}
	
	@RequestMapping(value="/delete/{userId}/{trainingTypeId}", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse delete(@PathVariable("userId") Long userId, @PathVariable("trainingTypeId") Long trainingTypeId) {
		try {
			logger.debug("delete with>> userId> " + userId + ", trainingTypeId> " + trainingTypeId);
			CompletedTrainingServiceValidator.delete(userId, trainingTypeId);
			boolean resultBool = completedTrainingManager.delete(userId, trainingTypeId);
			BooleanResponse result = new BooleanResponse(resultBool, true);
			result.setSuccess(resultBool);
			logger.debug("delete was successful >> " + result);
			return result;
		} catch (ValidationException ve) {
			return new BooleanResponse(ve.getMessage()); 
		}
	}
	
	public CompletedUserTrainingDtos listByUserId(Long userId) {
		try {
			logger.debug("listByUserId with>> userId> " + userId );
			CompletedTrainingServiceValidator.listByUserId(userId);
			List<CompletedUserTraining> mgrResult = completedTrainingManager.listByUserId(userId);
			ModelMapper modelMapper = new ModelMapper();
		    Type targetListType = new TypeToken<List<CompletedUserTrainingDto>>() {}.getType();
		    List<CompletedUserTrainingDto> resultList = modelMapper.map(mgrResult, targetListType);
		    CompletedUserTrainingDtos result = new CompletedUserTrainingDtos(resultList);
			logger.debug("listByUserId was successful >> " + result);
			return result;
		} catch (ValidationException ve) {
			return new CompletedUserTrainingDtos(ve.getMessage()); 
		}
	}
	
	@RequestMapping(value="/getAllVieawable/{userId}", method = RequestMethod.GET,headers="Accept=application/json")
	public CompletedUserTrainingDtos listViewableByUserId(@PathVariable("userId") Long userId) {
		try {
			logger.debug("listViewableByUserId with>> userId> " + userId );
			CompletedTrainingServiceValidator.listViewableByUserId(userId);
			List<CompletedUserTrainingDto> mgrResult = completedTrainingManager.listViewableByUserId(userId);
			ModelMapper modelMapper = new ModelMapper();
			Type targetListType = new TypeToken<List<CompletedUserTrainingDto>>() {}.getType();
			List<CompletedUserTrainingDto> resultList = modelMapper.map(mgrResult, targetListType);
			CompletedUserTrainingDtos result = new CompletedUserTrainingDtos(resultList);
			logger.debug("listViewableByUserId was successful >> " + result);
			return result;
		} catch (ValidationException ve) {
			return new CompletedUserTrainingDtos(ve.getMessage()); 
		}
	}
	
	// getters and setters
	
	private boolean isUserEligibleToAddTraining(
			CompletedUserTrainingDto complatedUserTrainingDto) {
		return completedTrainingManager.isUserEligibleToAddTraining(complatedUserTrainingDto);
	}

	public void setCompletedTrainingManager(
			CompletedTrainingManager completedTrainingManager) {
		this.completedTrainingManager = completedTrainingManager;
	}
}
