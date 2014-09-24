package com.codeproj.traininghandler.dao;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.model.TrainingType;

@Service
@Transactional
public interface TrainingTypeDAO {
	public void create(TrainingType trainingType);
	
	public TrainingType getTrainingTypeById(Long id);
	
	public List<TrainingType> getAll();
	
	public void update(TrainingType trainingType);
	
	public void delete(TrainingType trainingType);
}