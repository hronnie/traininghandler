package com.codeproj.traininghandler.manager.trainee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproj.traininghandler.dao.TraineeDAO;
import com.codeproj.traininghandler.dto.TraineeDto;

public class TraineeManager {
	
	@Autowired
	private TraineeDAO traineeDAO;

	public List<TraineeDto> getAllTrainees() {
		return traineeDAO.getAllTrainees();
	}

	public void setTraineeDAO(
			TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}
}
