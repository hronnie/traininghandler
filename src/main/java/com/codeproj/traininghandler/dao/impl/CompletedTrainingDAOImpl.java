package com.codeproj.traininghandler.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.model.CompletedUserTraining;

public class CompletedTrainingDAOImpl implements CompletedTrainingDAO {
	private SessionFactory sessionFactory;

	public CompletedTrainingDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public CompletedTrainingDAOImpl() { /* empty constructor */ }

	@Override
	@Transactional
	public Long create(CompletedUserTraining complatedUserTraining) {
		sessionFactory.getCurrentSession().save(complatedUserTraining);
		return complatedUserTraining.getCompletedUserTrainingId();
	}
	
	

}
