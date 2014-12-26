package com.codeproj.traininghandler.manager.completedTraining;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TrainingTypeDto;
import com.codeproj.traininghandler.model.CompletedUserTraining;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;

@Component
public class CompletedTrainingManager {
	
	CompletedTrainingDAO completedTrainingDAO;

	public List<Long> create(List<CompletedUserTrainingDto> complatedUserTrainingDtoList) {
		List<CompletedUserTraining> complatedUserTrainingList = new ArrayList<>();
		for (CompletedUserTrainingDto dto : complatedUserTrainingDtoList) {
			CompletedUserTraining model = new CompletedUserTraining(null, new TrainingType(dto.getTrainingTypeId()), dto.getCompletedDate(), new User(dto.getUserId()));
			complatedUserTrainingList.add(model);
		}
		return completedTrainingDAO.create(complatedUserTrainingList);
	}

	public void setCompletedTrainingDAO(CompletedTrainingDAO completedTrainingDAO) {
		this.completedTrainingDAO = completedTrainingDAO;
	}

}
