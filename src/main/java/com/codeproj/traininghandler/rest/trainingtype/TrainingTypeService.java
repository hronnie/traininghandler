package com.codeproj.traininghandler.rest.trainingtype;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/trainingtype")
public class TrainingTypeService {

	@Autowired
	TrainingTypeManager trainingTypeManager;

	@RequestMapping(value="/create/", method = RequestMethod.POST,headers="Accept=application/json")
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

	public void setTrainingTypeManager(TrainingTypeManager trainingTypeManager) {
		this.trainingTypeManager = trainingTypeManager;
	}

}