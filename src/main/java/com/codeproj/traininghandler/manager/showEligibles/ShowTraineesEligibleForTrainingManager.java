package com.codeproj.traininghandler.manager.showEligibles;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
	
	private static final Logger logger = Logger.getLogger(ShowTraineesEligibleForTrainingManager.class);
	
	@Autowired
	ShowTraineesEligibleForTrainingDAO showTraineesEligibleForTrainingDAO;
	
	@Autowired
	CompletedTrainingManager completedTrainingManager;

	public TraineesEligibleForTrainingDto getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(Long trainingTypeId, DateTime trainingComplDate) {
		logger.debug("getEligibleTraineesByTrainingTypeIdAndTrainingComplDate with >> trainingTypeId>> " + trainingTypeId + ", trainingComplDate>> " + trainingComplDate);
		
		List<TrainingPrerequisite> prerequisites = showTraineesEligibleForTrainingDAO.getPrerequisitesByTrainingTypeId(trainingTypeId);
		logger.debug("prerequisites>> " + prerequisites);
		List<TrainingTypePrerequisite> trainingPrerequisites = generatePrerequiseteDates(prerequisites, trainingComplDate);
		logger.debug("trainingPrerequisites >> " + trainingPrerequisites);
		List<User> allUsers = showTraineesEligibleForTrainingDAO.getEligibleTrainees(trainingPrerequisites);
		filterAlreadyCompletedUsers(allUsers, trainingTypeId);
		
		List<UserDto> hasEmailUsers = new ArrayList<>();
		List<UserDto> onlyPhoneUsers = new ArrayList<>();
		sortUsersByHaveEmailOrNot(allUsers, hasEmailUsers, onlyPhoneUsers);
		
		TraineesEligibleForTrainingDto result = new TraineesEligibleForTrainingDto(hasEmailUsers, onlyPhoneUsers);
		logger.debug("result> " + result);
		return result;
	}

	private void filterAlreadyCompletedUsers(List<User> allUsers, Long trainingTypeId) {
		logger.debug("filterAlreadyCompletedUsers with allUsers>> " + allUsers + ", trainingTypeId" + trainingTypeId);
		
		List<Long> alreadyCompletedUsers = completedTrainingManager.getUsersWhoCompletedTrainingType(trainingTypeId);
		
		logger.debug("alreadyCompletedUsers>> " + alreadyCompletedUsers);
		
		List<User> filterOutTheseUsersList = new ArrayList<>();
		for (User item : allUsers) {
			if (alreadyCompletedUsers.contains(item.getUserId())) {
				filterOutTheseUsersList.add(item);
			}
		}
		
		logger.debug("filterOutTheseUsersList>> " + filterOutTheseUsersList);
		
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
			(final List<TrainingPrerequisite> prerequisites, final DateTime complDate) {
		
		List<TrainingTypePrerequisite> result = new ArrayList<>();
		for (TrainingPrerequisite item : prerequisites) {
			Long prerequisiteTrainingTypeId = item.getPrerequisiteTrainingTypeId();
			TrainingTypePrerequisite ttPrereq = new TrainingTypePrerequisite(prerequisiteTrainingTypeId, complDate.minusMonths(item.getBetweenMonth()).toDate());
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
