package com.codeproj.traininghandler.dao;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.model.TrainingType;

@Service
@Transactional
public interface TrainingTypeDAO {
	public TrainingType getTrainingTypeById(Long id);
	
	public void create(TrainingType trainingType);
	
	public List<TrainingType> getAll();
}