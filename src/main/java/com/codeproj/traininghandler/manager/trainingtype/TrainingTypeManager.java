package com.codeproj.traininghandler.manager.trainingtype;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.model.TrainingType;

@Component
public class TrainingTypeManager {
	TrainingTypeDAO trainingTypeDAO;

	public boolean create(String name, String levelNo, String description) {
		TrainingType newTrainingType = new TrainingType(name, levelNo, description);
		trainingTypeDAO.create(newTrainingType);
		return true;
	}
	
	public TrainingType getTrainingTypeById(Long id) {
		return trainingTypeDAO.getTrainingTypeById(id);
	}
	
	public List<TrainingType> getAllTrainingType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
		this.trainingTypeDAO = trainingTypeDAO;
	}
}
