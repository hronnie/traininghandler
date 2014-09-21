package com.codeproj.traininghandler.dao;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.common.IOperations;
import com.codeproj.traininghandler.model.TrainingType;

@Service
@Transactional
public interface TrainingTypeDAO extends IOperations<TrainingType> {


}
