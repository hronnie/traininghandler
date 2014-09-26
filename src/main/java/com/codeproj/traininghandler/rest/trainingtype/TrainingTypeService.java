package com.codeproj.traininghandler.rest.trainingtype;


import java.util.List;
import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/trainingtype")
public class TrainingTypeService {

	@Autowired
	TrainingTypeManager trainingTypeManager;

	@RequestMapping(value="/create", method = RequestMethod.POST,headers="Accept=application/json")
	public boolean create(@RequestParam String name,
			@RequestParam String levelNo,
			@RequestParam String description)
			throws ValidationException {

		name = ValidatorBaseUtility.stripXSS(name);
		levelNo = ValidatorBaseUtility.stripXSS(levelNo);
		description = ValidatorBaseUtility.stripXSS(description);
		TrainingTypeServiceValidator.create(name, levelNo, description);
		trainingTypeManager.create(name, levelNo, description);

		return true;
	}

	@RequestMapping(value="/get", method = RequestMethod.GET,headers="Accept=application/json")
	public TrainingTypeDto getTrainingTypeById(@RequestParam(value = "id",required = false, defaultValue = "0")
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
	public List<TrainingTypeDto> getAllTrainingType() {
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
}