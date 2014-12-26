package com.codeproj.traininghandler.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<Long> create(List<CompletedUserTraining> complatedUserTrainingList) {
		
		List<Long> result = new ArrayList<>();
		for (CompletedUserTraining item : complatedUserTrainingList) {
			sessionFactory.getCurrentSession().save(item);
			result.add(item.getCompletedUserTrainingId());
		}
        return result;
	}

}
