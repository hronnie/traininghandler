package com.codeproj.traininghandler.dao.impl;

import java.util.List;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.User;

public class ShowTraineesEligibleForTrainingDAOImpl implements
		ShowTraineesEligibleForTrainingDAO {


	@Override
	public List<TrainingPrerequisite> getPrerequisitesByTrainingId(
			Long trainingTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getEligibleTrainees(
			List<TrainingTypePrerequisite> trainingPrerequisites) {
		// TODO Auto-generated method stub
		return null;
	}

}
