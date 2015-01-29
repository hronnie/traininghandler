package com.codeproj.traininghandler.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
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

	@Override
	@Transactional
	public boolean isCompletedTrainingExist(
			CompletedUserTraining completedUserTraining) {
		if (completedUserTraining.getUser() == null || completedUserTraining.getTrainingType() == null) {
			return false;
		}
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
			    "from CompletedUserTraining cut WHERE cut.user.userId=:userId AND cut.trainingType.trainingTypeId=:trainingTypeId");
		
		query.setParameter("userId", completedUserTraining.getUser().getUserId());
		query.setParameter("trainingTypeId", completedUserTraining.getTrainingType().getTrainingTypeId());
		
		CompletedUserTraining result = (CompletedUserTraining)query.uniqueResult();
		if (result != null && result.getCompletedUserTrainingId() != null && result.getCompletedUserTrainingId() > 0L) {
			return true;
		}
		
		return false;
	}
}
