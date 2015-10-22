package com.codeproj.traininghandler.rest.trainingtype;


import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.dto.TrainingTypeDtos;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.rest.common.BaseResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.Constants;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/trainingtype")
public class TrainingTypeService {
	
	private static final Logger logger = Logger.getLogger(TrainingTypeService.class);

	@Autowired
	TrainingTypeManager trainingTypeManager;

	/**
	 * Creates a Training Type object
	 * @param trainingType - builds from unique trainingTypeId, name, levelNo and description 
	 * @return
	 * @throws ValidationException
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestBody TrainingTypeDto trainingType) {
		if (trainingType == null) {
			logger.debug(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
			return new GeneralIdResponse(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		try {
			String name = ValidatorBaseUtility.stripXSS(trainingType.getName());
			String levelNo = ValidatorBaseUtility.stripXSS(trainingType.getLevelNo());
			String description = ValidatorBaseUtility.stripXSS(trainingType.getDescription());
			
			TrainingTypeServiceValidator.create(name, levelNo, description);
			Long result = trainingTypeManager.create(name, levelNo, description);
			logger.debug("Molecule object stored successfully with the following data: " + trainingType.toString() );
			return new GeneralIdResponse(result);
		} catch (ValidationException ve) {
			return new GeneralIdResponse(ve.getMessage());
		}
	}

	/**
	 * Gets a Training Type object with the given id
	 * @param id - Training type id
	 * @return
	 * @throws DatabaseEntityNotFoundException
	 * @throws ValidationException
	 */
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TrainingTypeDto getTrainingTypeById(@PathVariable("id") Long id) {
		
		try {
			TrainingTypeServiceValidator.getTrainingTypeById(id);
		} catch (ValidationException ve) {
			return new TrainingTypeDto(ve.getMessage());
		}
		TrainingType trainingType;
		try {
			trainingType = trainingTypeManager.getTrainingTypeById(id);
		} catch (DatabaseEntityNotFoundException denfe) {
			return new TrainingTypeDto(denfe.getMessage());
		}
		if (trainingType == null) {
			return new TrainingTypeDto(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST);
		}
		ModelMapper modelMapper = new ModelMapper();
		TrainingTypeDto result = modelMapper.map(trainingType, TrainingTypeDto.class);
		result.setSuccess(true);
		result.setMessage("this is a test message");
		return result;
	}
	
	/**
	 * Gets all Training Type objects
	 * @return
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TrainingTypeDtos getAllTrainingType() {
		
		List<TrainingType> dbResult;
		try {
			dbResult = trainingTypeManager.getAllTrainingType();
		} catch (DatabaseEntityNotFoundException denfe) {
			return new TrainingTypeDtos(denfe.getMessage());
		}

		ModelMapper modelMapper = new ModelMapper();
		
	    Type targetListType = new TypeToken<List<TrainingTypeDto>>() {}.getType();
	    List<TrainingTypeDto> result = modelMapper.map(dbResult, targetListType);
		return new TrainingTypeDtos(result);
	}
	
	/**
	 * Updates a Training Type object
	 * @param trainingType - builds from unique trainingTypeId, name, levelNo and description
	 * @return
	 * @throws ValidationException
	 * @throws DatabaseEntityNotFoundException
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST,headers="Accept=application/json")
	public BaseResponse update(@RequestBody TrainingTypeDto trainingType) {
		
		if (trainingType == null) {
			return new BaseResponse(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		
		String name = ValidatorBaseUtility.stripXSS(trainingType.getName());
		String levelNo = ValidatorBaseUtility.stripXSS(trainingType.getLevelNo());
		String description = ValidatorBaseUtility.stripXSS(trainingType.getDescription());
		Long trainingTypeId = trainingType.getTrainingTypeId();
		
		try {
			TrainingTypeServiceValidator.update(trainingTypeId, name, levelNo, description);
			trainingTypeManager.update(trainingTypeId, name, levelNo, description);
		} catch (ValidationException ve) {
			return new BaseResponse(ve.getMessage());
		} catch (DatabaseEntityNotFoundException denfe) {
			return new BaseResponse(denfe.getMessage());
		}
		return new BaseResponse(true);
	}

	/**
	 * Deletes a Training Type object
	 * @param trainingType - builds from unique trainingTypeId, name, levelNo and description
	 * @return
	 * @throws ValidationException
	 * @throws DatabaseEntityNotFoundException
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST,headers="Accept=application/json")
	public BaseResponse delete(@RequestBody TrainingTypeDto trainingType) {
		if (trainingType == null) {
			return new BaseResponse(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		try {
			TrainingTypeServiceValidator.delete(trainingType.getTrainingTypeId());
			trainingTypeManager.delete(trainingType.getTrainingTypeId());
		} catch (ValidationException ve) {
			return new BaseResponse(ve.getMessage());
		} catch (DatabaseEntityNotFoundException denfe) {
			return new BaseResponse(denfe.getMessage());
		}
		
		return new BaseResponse(true);
	}
	
	public void setTrainingTypeManager(TrainingTypeManager trainingTypeManager) {
		this.trainingTypeManager = trainingTypeManager;
	}
}