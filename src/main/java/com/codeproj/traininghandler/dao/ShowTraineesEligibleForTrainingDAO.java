package com.codeproj.traininghandler.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.dto.TraineesEligibleForTrainingDto;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.User;

@Service
@Transactional
public interface ShowTraineesEligibleForTrainingDAO {
	public List<User> getEligibleTrainees(List<TrainingTypePrerequisite> trainingPrerequisites);

	public List<TrainingPrerequisite> getPrerequisitesByTrainingId(Long trainingTypeId);
}
