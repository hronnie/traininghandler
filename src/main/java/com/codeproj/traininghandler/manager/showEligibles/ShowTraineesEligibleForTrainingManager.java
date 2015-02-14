package com.codeproj.traininghandler.manager.showEligibles;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;
import com.codeproj.traininghandler.util.ThStringUtils;

public class ShowTraineesEligibleForTrainingManager {
	
	ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO;

	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeId(Long trainingTypeId) {
		List<TrainingPrerequisite> prerequisites = showTraineesEligibleForTrainingDAO.getPrerequisitesByTrainingId(trainingTypeId);
		List<TrainingTypePrerequisite> trainingPrerequisites = generatePrerequiseteDates(prerequisites);
		List<User> allUsers = showTraineesEligibleForTrainingDAO.getEligibleTrainees(trainingPrerequisites);
		List<UserDto> hasEmailUsers = new ArrayList<>();
		List<UserDto> onlyPhoneUsers = new ArrayList<>();
		sortUsersByHaveEmailOrNot(allUsers, hasEmailUsers, onlyPhoneUsers);
		return new TraineesEligibleForTrainingDto(hasEmailUsers, onlyPhoneUsers);
	}
	
	private void sortUsersByHaveEmailOrNot(List<User> allUsers,
			List<UserDto> hasEmailUsers, List<UserDto> onlyPhoneUsers) {
		if (allUsers.size() < 1) {
			return;
		}
		for (User item : allUsers) {
			if (ThStringUtils.isEmailPhoneEmail(item.getEmail())) {
				onlyPhoneUsers.add(convertUserToDto(item));
				continue;
			}
			hasEmailUsers.add(convertUserToDto(item));
		}
		
	}

	private UserDto convertUserToDto(User item) {
		UserDto result = new UserDto();
		result.setName(item.getName());
		result.setEmail(item.getEmail());
		result.setPhoneNo(item.getMobileNo());
		return result;
	}

	private List<TrainingTypePrerequisite> generatePrerequiseteDates
			(List<TrainingPrerequisite> prerequisites) {
		
		List<TrainingTypePrerequisite> result = new ArrayList<>();
		for (TrainingPrerequisite item : prerequisites) {
			DateTime complDate = new DateTime();
			complDate = complDate.minusMonths(item.getBetweenMonth());
			Long prerequisiteTrainingTypeId = item.getPrerequisiteTrainingTypeId();
			TrainingTypePrerequisite ttPrereq = new TrainingTypePrerequisite(prerequisiteTrainingTypeId, complDate.toDate());
			result.add(ttPrereq);
		}
		
		return result;
	}

	public void setShowTraineesEligibleForTrainingDAO(
			ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO) {
		this.showTraineesEligibleForTrainingDAO = showTraineesEligibleForTrainingDAO;
	}

}
