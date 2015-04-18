package com.codeproj.traininghandler.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.ListAndEditTraineesDAO;
import com.codeproj.traininghandler.dto.TraineeDto;

public class ListAndEditTraineesDAOImpl implements ListAndEditTraineesDAO {
	private SessionFactory sessionFactory;

	public ListAndEditTraineesDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ListAndEditTraineesDAOImpl() { /* empty constructor */ }


	@Override
	@Transactional
	public List<TraineeDto> getAllTrainees() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	SELECT 
	 u.userId, 
	 u.name, 
	 a.postalCode,
	 a.oneLineAddress,
	 u.mobileNo, 
	 u.email,
	 u.addressId,
	 comp_training_sub_table.complTrNames
	FROM 
	 User AS u  
	INNER JOIN Address a on (u.addressId = a.addressId)
	INNER JOIN
	(
	 SELECT 
	  cut.userId AS complUserId, group_concat(tt.name SEPARATOR ', ') AS complTrNames
	 FROM 
	  CompletedUserTraining cut
	 LEFT JOIN TrainingType tt on (tt.trainingTypeId = cut.trainingTypeId)
	 GROUP BY cut.userId
	 ORDER BY cut.trainingTypeId DESC
	) 
	AS comp_training_sub_table ON (comp_training_sub_table.complUserId = u.userId)


	 */


}
