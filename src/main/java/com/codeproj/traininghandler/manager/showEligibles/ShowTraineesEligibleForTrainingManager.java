package com.codeproj.traininghandler.manager.showEligibles;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.User;

public class ShowTraineesEligibleForTrainingManager {
	
	ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO;

	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeId(Long trainingTypeId) {
		List<TrainingPrerequisite> prerequisites = showTraineesEligibleForTrainingDAO.getPrerequisitesByTrainingId(trainingTypeId);
		List<TrainingTypePrerequisite> trainingPrerequisites = generatePrerequiseteDates(prerequisites);
		List<User> allUsers = showTraineesEligibleForTrainingDAO.getEligibleTrainees(trainingPrerequisites);
		List<UserDto> hasEmailUsers = new ArrayList<>();
		List<UserDto> onlyPhoneUsers = new ArrayList<>();
		sortUsersByHasEmailOrNot(allUsers, hasEmailUsers, onlyPhoneUsers);
		return new TraineesEligibleForTrainingDto(hasEmailUsers, onlyPhoneUsers);
	}
	
	private void sortUsersByHasEmailOrNot(List<User> allUsers,
			List<UserDto> hasEmailUsers, List<UserDto> onlyPhoneUsers) {
		// TODO Auto-generated method stub
		
	}

	private List<TrainingTypePrerequisite> generatePrerequiseteDates
			(List<TrainingPrerequisite> prerequisites) {
		
		List<TrainingTypePrerequisite> result = new ArrayList<>();
		for (TrainingPrerequisite item : prerequisites) {
			DateTime complDate = new DateTime();
			complDate.minusMonths(item.getBetweenMonth());
			TrainingTypePrerequisite ttPrereq = new TrainingTypePrerequisite(item.getTrainingPrerequisiteId(), complDate.toDate());
			result.add(ttPrereq);
		}
		
		return result;
	}

	public void setShowTraineesEligibleForTrainingDAO(
			ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO) {
		this.showTraineesEligibleForTrainingDAO = showTraineesEligibleForTrainingDAO;
	}

}
