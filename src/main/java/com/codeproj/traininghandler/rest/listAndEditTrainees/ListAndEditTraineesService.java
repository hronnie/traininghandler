package com.codeproj.traininghandler.rest.listAndEditTrainees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.dto.TraineeDtos;
import com.codeproj.traininghandler.manager.listAndEditTrainees.ListAndEditTraineesManager;

public class ListAndEditTraineesService {
	
	@Autowired
	private ListAndEditTraineesManager listAndEditTraineesManager;
	

	public TraineeDtos getAllTrainees() {
		List<TraineeDto> traineeList = listAndEditTraineesManager.getAllTrainees();
		TraineeDtos result = new TraineeDtos(traineeList);
		return result;
	}


	public void setListAndEditTraineesManager(
			ListAndEditTraineesManager listAndEditTraineesManager) {
		this.listAndEditTraineesManager = listAndEditTraineesManager;
	}

}
