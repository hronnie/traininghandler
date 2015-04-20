package com.codeproj.traininghandler.rest.listAndEditTrainees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.dto.TraineeDtos;
import com.codeproj.traininghandler.manager.listAndEditTrainees.ListAndEditTraineesManager;

@RestController
@RequestMapping("/trainee")
public class ListAndEditTraineesService {
	
	@Autowired
	private ListAndEditTraineesManager listAndEditTraineesManager;
	

	@RequestMapping(value="/getAll", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody TraineeDtos getAllTrainees() {
		List<TraineeDto> traineeList = listAndEditTraineesManager.getAllTrainees();
		TraineeDtos result = new TraineeDtos(traineeList);
		return result;
	}
	
	public void setListAndEditTraineesManager(
			ListAndEditTraineesManager listAndEditTraineesManager) {
		this.listAndEditTraineesManager = listAndEditTraineesManager;
	}

}
