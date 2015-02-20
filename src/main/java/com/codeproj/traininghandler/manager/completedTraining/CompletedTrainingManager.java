package com.codeproj.traininghandler.manager.completedTraining;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.model.CompletedUserTraining;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;

@Component
public class CompletedTrainingManager {
	
	@Autowired
	CompletedTrainingDAO completedTrainingDAO;

	public Long create(CompletedUserTrainingDto dto) {
		CompletedUserTraining model = new CompletedUserTraining(null, new TrainingType(dto.getTrainingTypeId()), dto.getCompletedDate(), new User(dto.getUserId()));
		return completedTrainingDAO.create(model);
	}
	
	public boolean isCompletedTrainingExist(CompletedUserTrainingDto dto) {
		CompletedUserTraining model = new CompletedUserTraining(null, new TrainingType(dto.getTrainingTypeId()), dto.getCompletedDate(), new User(dto.getUserId()));
		return completedTrainingDAO.isCompletedTrainingExist(model);
	}
	
	public List<Long> getUsersWhoCompletedTrainingType(Long trainingTypeId) {
		List<Long> result = new ArrayList<>();
		List<CompletedUserTraining> alreadyCompletedList = completedTrainingDAO.getCompletedListByTrainingTypeId(trainingTypeId);
		if (alreadyCompletedList == null || alreadyCompletedList.isEmpty()) {
			return result;
		}
		for (CompletedUserTraining item : alreadyCompletedList) {
			result.add(item.getUser().getUserId());
		}
		return result;
	}
	
	public void setCompletedTrainingDAO(CompletedTrainingDAO completedTrainingDAO) {
		this.completedTrainingDAO = completedTrainingDAO;
	}
}
