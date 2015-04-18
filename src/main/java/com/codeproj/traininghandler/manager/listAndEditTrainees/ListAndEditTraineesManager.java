package com.codeproj.traininghandler.manager.listAndEditTrainees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.codeproj.traininghandler.dao.ListAndEditTraineesDAO;
import com.codeproj.traininghandler.dto.TraineeDto;

public class ListAndEditTraineesManager {
	
	@Autowired
	private ListAndEditTraineesDAO listAndEditTraineesDAO;

	public List<TraineeDto> getAllTrainees() {
		return listAndEditTraineesDAO.getAllTrainees();
	}

	public void setListAndEditTraineesDAO(
			ListAndEditTraineesDAO listAndEditTraineesDAO) {
		this.listAndEditTraineesDAO = listAndEditTraineesDAO;
	}
}
