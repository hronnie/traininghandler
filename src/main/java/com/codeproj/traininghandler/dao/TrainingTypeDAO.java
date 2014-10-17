package com.codeproj.traininghandler.dao;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.model.TrainingType;

@Service
@Transactional
public interface TrainingTypeDAO {
	public Long create(TrainingType trainingType);
	
	public TrainingType getTrainingTypeById(Long id);
	
	public List<TrainingType> getAll();
	
	public boolean update(TrainingType trainingType) throws DatabaseEntityNotFoundException;
	
	public boolean delete(Long trainingTypeId);
}