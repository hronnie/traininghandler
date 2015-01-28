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

//	public List<Long> create(List<CompletedUserTrainingDto> complatedUserTrainingDtoList) {
//		List<Long> result = new ArrayList<>();
//		for (CompletedUserTrainingDto dto : complatedUserTrainingDtoList) {
//			CompletedUserTraining model = new CompletedUserTraining(null, new TrainingType(dto.getTrainingTypeId()), dto.getCompletedDate(), new User(dto.getUserId()));
//			result.add(completedTrainingDAO.create(model));
//		}
//		return result;
//	}

	public Long create(CompletedUserTrainingDto dto) {
		CompletedUserTraining model = new CompletedUserTraining(null, new TrainingType(dto.getTrainingTypeId()), dto.getCompletedDate(), new User(dto.getUserId()));
		return completedTrainingDAO.create(model);
	}

	public void setCompletedTrainingDAO(CompletedTrainingDAO completedTrainingDAO) {
		this.completedTrainingDAO = completedTrainingDAO;
	}
}
