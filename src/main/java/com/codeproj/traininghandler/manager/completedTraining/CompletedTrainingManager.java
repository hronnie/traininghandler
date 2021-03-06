package com.codeproj.traininghandler.manager.completedTraining;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.dto.UserDto;
import com.codeproj.traininghandler.manager.showEligibles.ShowTraineesEligibleForTrainingManager;
import com.codeproj.traininghandler.model.CompletedUserTraining;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.model.User;

@Component
public class CompletedTrainingManager {
	
	private static final Logger logger = Logger.getLogger(CompletedTrainingManager.class);
	
	@Autowired
	CompletedTrainingDAO completedTrainingDAO;
	
	@Autowired
	ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager;

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
			logger.debug("getUsersWhoCompletedTrainingType list is empty");
			return result;
		}
		for (CompletedUserTraining item : alreadyCompletedList) {
			result.add(item.getUser().getUserId());
		}
		logger.debug("getUsersWhoCompletedTrainingType result>> " + result);
		return result;
	}

	public boolean isUserEligibleToAddTraining(CompletedUserTrainingDto complatedUserTrainingDto) {
		logger.debug("isUserEligibleToAddTraining with>> " + complatedUserTrainingDto);

		TraineesEligibleForTrainingDto eligibleTrainees = 
				showTraineesEligibleForTrainingManager.getEligibleTraineesByTrainingTypeIdAndTrainingComplDate(
						complatedUserTrainingDto.getTrainingTypeId(), new DateTime(complatedUserTrainingDto.getCompletedDate()));
		logger.debug("eligibleTrainees>> " + eligibleTrainees);
		List<UserDto> allUserList = new ArrayList<>();
		allUserList.addAll(eligibleTrainees.getHasEmailUsers());
		allUserList.addAll(eligibleTrainees.getOnlyPhoneUsers());
		for (UserDto item : allUserList) {
			if (item.getUserId().equals(complatedUserTrainingDto.getUserId())) {
				logger.debug("userId has been found in the following user>> " + item);
				return true;
			}
		}
		logger.debug("userId hasn't been found in any user");
		return false;
	}
	
	// getters/setters
	
	public void setCompletedTrainingDAO(CompletedTrainingDAO completedTrainingDAO) {
		this.completedTrainingDAO = completedTrainingDAO;
	}

	public void setShowTraineesEligibleForTrainingManager(
			ShowTraineesEligibleForTrainingManager showTraineesEligibleForTrainingManager) {
		this.showTraineesEligibleForTrainingManager = showTraineesEligibleForTrainingManager;
	}

	public Long update(CompletedUserTrainingDto complatedUserTrainingDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
