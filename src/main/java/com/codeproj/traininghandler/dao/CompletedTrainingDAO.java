package com.codeproj.traininghandler.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.model.CompletedUserTraining;

@Service
@Transactional
public interface CompletedTrainingDAO {
	public Long create(CompletedUserTraining complatedUserTrainingList);
}
