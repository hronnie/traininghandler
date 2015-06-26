package com.codeproj.traininghandler.manager.showEligibles;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.manager.completedTraining.CompletedTrainingManager;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.User;
import com.codeproj.traininghandler.util.ThStringUtils;

public class ShowTraineesEligibleForTrainingManager {
	
	@Autowired
	ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO;
	
	@Autowired
	CompletedTrainingManager completedTrainingManager;

	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeId(Long trainingTypeId) {
		//TODO: check with custom date not just for today
		List<TrainingPrerequisite> prerequisites = showTraineesEligibleForTrainingDAO.getPrerequisitesByTrainingTypeId(trainingTypeId);
		List<TrainingTypePrerequisite> trainingPrerequisites = generatePrerequiseteDates(prerequisites);
		List<User> allUsers = showTraineesEligibleForTrainingDAO.getEligibleTrainees(trainingPrerequisites);
		filterAlreadyCompletedUsers(allUsers, trainingTypeId);
		
		List<UserDto> hasEmailUsers = new ArrayList<>();
		List<UserDto> onlyPhoneUsers = new ArrayList<>();
		sortUsersByHaveEmailOrNot(allUsers, hasEmailUsers, onlyPhoneUsers);
		return new TraineesEligibleForTrainingDto(hasEmailUsers, onlyPhoneUsers);
	}
	
	
	
	private void filterAlreadyCompletedUsers(List<User> allUsers, Long trainingTypeId) {
		List<Long> alreadyCompletedUsers = completedTrainingManager.getUsersWhoCompletedTrainingType(trainingTypeId);
		List<User> filterOutTheseUsersList = new ArrayList<>();
		for (User item : allUsers) {
			if (alreadyCompletedUsers.contains(item.getUserId())) {
				filterOutTheseUsersList.add(item);
			}
		}
		allUsers.removeAll(filterOutTheseUsersList);
	}

	private void sortUsersByHaveEmailOrNot(List<User> allUsers,
			List<UserDto> hasEmailUsers, List<UserDto> onlyPhoneUsers) {
		if (allUsers.size() < 1) {
			return;
		}
		for (User item : allUsers) {
			if (ThStringUtils.isFakeEmail(item.getEmail())) {
				onlyPhoneUsers.add(convertUserToDto(item));
				continue;
			}
			hasEmailUsers.add(convertUserToDto(item));
		}
		
	}

	private UserDto convertUserToDto(User item) {
		UserDto result = new UserDto();
		result.setUserId(item.getUserId());
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
	
	public List<TrainingPrerequisite> getPrerequisitesByTrainingTypeId(Long trainingTypeId) {
		return showTraineesEligibleForTrainingDAO.getPrerequisitesByTrainingTypeId(trainingTypeId);
	}

	// accessors
	
	public void setShowTraineesEligibleForTrainingDAO(
			ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO) {
		this.showTraineesEligibleForTrainingDAO = showTraineesEligibleForTrainingDAO;
	}

	public void setCompletedTrainingManager(
			CompletedTrainingManager completedTrainingManager) {
		this.completedTrainingManager = completedTrainingManager;
	}
}
