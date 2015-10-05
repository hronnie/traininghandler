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
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
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
	public GeneralIdResponse create(@RequestBody TrainingTypeDto trainingType)
			throws ValidationException {
		
		if (trainingType == null) {
			logger.debug(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}

		String name = ValidatorBaseUtility.stripXSS(trainingType.getName());
		String levelNo = ValidatorBaseUtility.stripXSS(trainingType.getLevelNo());
		String description = ValidatorBaseUtility.stripXSS(trainingType.getDescription());

		TrainingTypeServiceValidator.create(name, levelNo, description);
		Long result = trainingTypeManager.create(name, levelNo, description);
		logger.debug("Molecule object stored successfully with the following data: " + trainingType.toString() );
		return new GeneralIdResponse(result);
	}

	/**
	 * Gets a Training Type object with the given id
	 * @param id - Training type id
	 * @return
	 * @throws DatabaseEntityNotFoundException
	 * @throws ValidationException
	 */
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TrainingTypeDto getTrainingTypeById(@PathVariable("id")
				Long id) throws DatabaseEntityNotFoundException, ValidationException {
		
		if (id == null || id < 0) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_HAS_NOT_FOUND);
		}
		TrainingType trainingType = trainingTypeManager.getTrainingTypeById(id);
		if (trainingType == null) {
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_HAS_NOT_FOUND);
		}
		ModelMapper modelMapper = new ModelMapper();
		TrainingTypeDto result = modelMapper.map(trainingType, TrainingTypeDto.class);
		return result;
	}
	
	/**
	 * Gets all Training Type objects
	 * @return
	 */
	@RequestMapping(value="/getAll", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody List<TrainingTypeDto> getAllTrainingType() {
		List<TrainingType> dbResult = trainingTypeManager.getAllTrainingType();
		if (dbResult == null || dbResult.isEmpty()) {
			return null;
		}
		ModelMapper modelMapper = new ModelMapper();
		
	    Type targetListType = new TypeToken<List<TrainingTypeDto>>() {}.getType();
	    List<TrainingTypeDto> result = modelMapper.map(dbResult, targetListType);
		return result;
	}
	
	/**
	 * Updates a Training Type object
	 * @param trainingType - builds from unique trainingTypeId, name, levelNo and description
	 * @return
	 * @throws ValidationException
	 * @throws DatabaseEntityNotFoundException
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse update(@RequestBody TrainingTypeDto trainingType) throws ValidationException, DatabaseEntityNotFoundException {
		
		if (trainingType == null) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		
		String name = ValidatorBaseUtility.stripXSS(trainingType.getName());
		String levelNo = ValidatorBaseUtility.stripXSS(trainingType.getLevelNo());
		String description = ValidatorBaseUtility.stripXSS(trainingType.getDescription());
		Long trainingTypeId = trainingType.getTrainingTypeId();
		
		TrainingTypeServiceValidator.update(trainingTypeId, name, levelNo, description);
		trainingTypeManager.update(trainingTypeId, name, levelNo, description);
		return new BooleanResponse(true);
	}

	/**
	 * Deletes a Training Type object
	 * @param trainingType - builds from unique trainingTypeId, name, levelNo and description
	 * @return
	 * @throws ValidationException
	 * @throws DatabaseEntityNotFoundException
	 */
	@RequestMapping(value="/delete", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse delete(@RequestBody TrainingTypeDto trainingType)  throws ValidationException, DatabaseEntityNotFoundException {
		if (trainingType == null) {
			throw new ValidationException(Constants.VALIDATION_ERR_MSG_ERROR_DURING_SENDING_REQUEST);
		}
		
		TrainingTypeServiceValidator.delete(trainingType.getTrainingTypeId());
		trainingTypeManager.delete(trainingType.getTrainingTypeId());
		return new BooleanResponse(true);
	}
	
	public void setTrainingTypeManager(TrainingTypeManager trainingTypeManager) {
		this.trainingTypeManager = trainingTypeManager;
	}
}