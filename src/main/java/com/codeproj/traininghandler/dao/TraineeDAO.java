package com.codeproj.traininghandler.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dto.TraineeDto;

@Service
@Transactional
public interface TraineeDAO {
	public List<TraineeDto> getAllTrainees();
}
