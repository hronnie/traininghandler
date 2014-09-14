package com.codeproj.traininghandler.rest.trainingtype;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.exceptions.ValidationException;
import com.codeproj.traininghandler.manager.trainingtype.TrainingTypeManager;
import com.codeproj.traininghandler.util.ValidatorBaseUtility;

@RestController
@RequestMapping("/trainingtype")
public class TrainingTypeService {

	@Autowired
	private TrainingTypeManager trainingTypeManager;

//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	public String getGreeting(@PathVariable String name) {
//		String result = "Hello " + name;
//		return result;
//	}

	@RequestMapping(value = "/get/", method = RequestMethod.GET)
	public String getMsg() {

		String output = "This is a hello world output for config";

		return output;

	}
//
//	@POST
//	@Path("/create/")
//	@Produces("application/json; charset=UTF-8")
//	public Response create(@FormParam("name") String name,
//			@FormParam("levelNo") String levelNo,
//			@FormParam("description") String description)
//					throws ValidationException {
	@RequestMapping(value="/create/", method = RequestMethod.POST,headers="Accept=application/json")
	public TrainingTypeDto create(@RequestParam String name,
			@RequestParam String levelNo,
			@RequestParam String description)
			throws ValidationException {

		name = ValidatorBaseUtility.stripXSS(name);
		levelNo = ValidatorBaseUtility.stripXSS(levelNo);
		description = ValidatorBaseUtility.stripXSS(description);
		TrainingTypeServiceValidator.create(name, levelNo, description);
		trainingTypeManager.create(name, levelNo, description);

		return new TrainingTypeDto("name", "levelNo", "description");

	}

	public void setTrainingTypeManager(TrainingTypeManager trainingTypeManager) {
		this.trainingTypeManager = trainingTypeManager;
	}

}