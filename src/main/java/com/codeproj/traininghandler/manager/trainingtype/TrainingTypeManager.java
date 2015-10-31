package com.codeproj.traininghandler.manager.trainingtype;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.model.TrainingType;

@Component
public class TrainingTypeManager {
	TrainingTypeDAO trainingTypeDAO;

	public Long create(String name, String levelNo, String description) {
		TrainingType newTrainingType = new TrainingType(name, levelNo, description);
		return trainingTypeDAO.create(newTrainingType);
	}
	
	public TrainingType getTrainingTypeById(Long id) throws DatabaseEntityNotFoundException {
		return trainingTypeDAO.getTrainingTypeById(id);
	}
	
	public List<TrainingType> getAllTrainingType() throws DatabaseEntityNotFoundException {
		return trainingTypeDAO.getAll();
	}

	public boolean update(Long trainingTypeId, String name, String levelNo, String description) throws DatabaseEntityNotFoundException {
		return trainingTypeDAO.update(new TrainingType(trainingTypeId, name, levelNo, description));
	}

	public boolean delete(Long trainingTypeId) throws DatabaseEntityNotFoundException {
		return trainingTypeDAO.delete(trainingTypeId);
	}
	

	public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
		this.trainingTypeDAO = trainingTypeDAO;
	}
}
