package com.codeproj.traininghandler.rest.trainingtype;


import java.util.List;
import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.rest.common.BooleanResponse;
import com.codeproj.traininghandler.rest.common.GeneralIdResponse;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/trainingtype")
public class TrainingTypeService {

	@Autowired
	TrainingTypeManager trainingTypeManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public GeneralIdResponse create(@RequestParam String name,
			@RequestParam String levelNo,
			@RequestParam String description)
			throws ValidationException {

		name = ValidatorBaseUtility.stripXSS(name);
		levelNo = ValidatorBaseUtility.stripXSS(levelNo);
		description = ValidatorBaseUtility.stripXSS(description);
		TrainingTypeServiceValidator.create(name, levelNo, description);
		Long result = trainingTypeManager.create(name, levelNo, description);
		return new GeneralIdResponse(result);
	}

	@RequestMapping(value="/get/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TrainingTypeDto getTrainingTypeById(@PathVariable("id")
				Long id) throws DatabaseEntityNotFoundException, ValidationException {
		
		if (id == null || id < 0) {
			throw new ValidationException("TrainingType id is less then 0");
		}
		TrainingType trainingType = trainingTypeManager.getTrainingTypeById(id);
		if (trainingType == null) {
			throw new DatabaseEntityNotFoundException("TrainingType not found with id: [" + id + "]");
		}
		ModelMapper modelMapper = new ModelMapper();
		TrainingTypeDto result = modelMapper.map(trainingType, TrainingTypeDto.class);
		return result;
	}
	
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

	public void setTrainingTypeManager(TrainingTypeManager trainingTypeManager) {
		this.trainingTypeManager = trainingTypeManager;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse update(@RequestParam Long trainingTypeId,
			@RequestParam String name,
			@RequestParam String levelNo,
			@RequestParam String description) throws ValidationException, DatabaseEntityNotFoundException {
		
		name = ValidatorBaseUtility.stripXSS(name);
		levelNo = ValidatorBaseUtility.stripXSS(levelNo);
		description = ValidatorBaseUtility.stripXSS(description);
		TrainingTypeServiceValidator.update(trainingTypeId, name, levelNo, description);
		trainingTypeManager.update(trainingTypeId, name, levelNo, description);
		return new BooleanResponse(true);
	}

	@RequestMapping(value="/delete", method = RequestMethod.POST,headers="Accept=application/json")
	public BooleanResponse delete(@RequestParam Long trainingTypeId)  throws ValidationException, DatabaseEntityNotFoundException {
		TrainingTypeServiceValidator.delete(trainingTypeId);
		trainingTypeManager.delete(trainingTypeId);
		return new BooleanResponse(true);
	}
}