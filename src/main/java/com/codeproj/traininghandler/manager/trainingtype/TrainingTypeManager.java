package com.codeproj.traininghandler.manager.trainingtype;

import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.model.TrainingType;

@Component
public class TrainingTypeManager {
	TrainingTypeDAO trainingTypeDAO;

	public void create(String name, String levelNo, String description) {
		TrainingType newTrainingType = new TrainingType(name, levelNo, description);
		trainingTypeDAO.create(newTrainingType);
	}

	public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
		this.trainingTypeDAO = trainingTypeDAO;
	}
}
