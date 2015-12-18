package com.codeproj.traininghandler.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.model.CompletedUserTraining;

public class CompletedTrainingDAOImpl implements CompletedTrainingDAO {
	
	private static final Logger logger = Logger.getLogger(CompletedTrainingDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public CompletedTrainingDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public CompletedTrainingDAOImpl() { /* empty constructor */ }

	@Override
	@Transactional
	public Long create(CompletedUserTraining complatedUserTraining) {
		logger.debug("Creating CompletedUserTraining: " + complatedUserTraining);
		sessionFactory.getCurrentSession().save(complatedUserTraining);
		return complatedUserTraining.getCompletedUserTrainingId();
	}

	@Override
	@Transactional
	public boolean isCompletedTrainingExist(
			CompletedUserTraining completedUserTraining) {
		if (completedUserTraining.getUser() == null || completedUserTraining.getTrainingType() == null) {
			logger.debug("isCompletedTrainingExist: user doesn't exist: " + completedUserTraining);
			return false;
		}
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"from CompletedUserTraining cut WHERE cut.user.userId=:userId AND cut.trainingType.trainingTypeId=:trainingTypeId");
			
			query.setParameter("userId", completedUserTraining.getUser().getUserId());
			query.setParameter("trainingTypeId", completedUserTraining.getTrainingType().getTrainingTypeId());
			
			CompletedUserTraining result = (CompletedUserTraining)query.uniqueResult();
			if (result != null && result.getCompletedUserTrainingId() != null && result.getCompletedUserTrainingId() > 0L) {
				logger.debug("The CompletedUserTraining does exist: " + completedUserTraining);
				return true;
			}
			
			logger.debug("The CompletedUserTraining does NOT exist: " + completedUserTraining);
			return false;
			
		} finally {
			session.close();
		}
	}

	@Override
	public List<CompletedUserTraining> getCompletedListByTrainingTypeId(
			Long trainingTypeId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from CompletedUserTraining where trainingTypeId = :trainingTypeId ");
			query.setParameter("trainingTypeId", trainingTypeId);
			List<CompletedUserTraining> result = (List<CompletedUserTraining>)query.list();
			logger.debug("The result of getCompletedListByTrainingTypeId is " + result);
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public boolean deleteByUserId(Long userId) {
		logger.debug("Deleting CompletedUserTraining with userId> " + userId);
		String hql = "delete from CompletedUserTraining where userId= :userId";
		sessionFactory.getCurrentSession().createQuery(hql).setLong("userId", userId).executeUpdate();
		return true;
	}

	@Override
	@Transactional
	public boolean update(CompletedUserTrainingDto completedUserTraining) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update CompletedUserTraining cut set cut.completedDate = :completedDate WHERE cut.user.userId=:userId AND cut.trainingType.trainingTypeId=:trainingTypeId");
			
			query.setParameter("userId", completedUserTraining.getUserId());
			query.setParameter("trainingTypeId", completedUserTraining.getTrainingTypeId());
			query.setParameter("completedDate", completedUserTraining.getCompletedDate());
			
			CompletedUserTraining result = (CompletedUserTraining)query.uniqueResult();
			if (result != null && result.getCompletedUserTrainingId() != null && result.getCompletedUserTrainingId() > 0L) {
				logger.debug("The CompletedUserTraining does exist: " + completedUserTraining);
				return true;
			}
			
			logger.debug("The CompletedUserTraining does NOT exist: " + completedUserTraining);
			return false;
			
		} finally {
			session.close();
			logger.debug("Updating CompletedUserTraining with userId> " + completedUserTraining.getUserId() + ", trainingTypeId> " + completedUserTraining.getTrainingTypeId() + ", completed date> " + completedUserTraining.getCompletedDate());
		}
	}

	@Override
	@Transactional
	public boolean delete(Long userId, Long trainingTypeId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(
					"delete CompletedUserTraining cut WHERE cut.user.userId=:userId AND cut.trainingType.trainingTypeId=:trainingTypeId");
			
			query.setParameter("userId", userId);
			query.setParameter("trainingTypeId", trainingTypeId);
			
			CompletedUserTraining result = (CompletedUserTraining)query.uniqueResult();
			if (result != null && result.getCompletedUserTrainingId() != null && result.getCompletedUserTrainingId() > 0L) {
				logger.debug("The CompletedUserTraining does exist");
				return true;
			}
			
			logger.debug("The CompletedUserTraining does NOT exist");
			return false;
			
		} finally {
			session.close();
			logger.debug("Deleting CompletedUserTraining with userId> " + userId + ", trainingTypeId> " + trainingTypeId);
		}
	}

	@Override
	@Transactional
	public List<CompletedUserTraining> listByUserId(Long userId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("from CompletedUserTraining where userId = :userId ");
			query.setParameter("userId", userId);
			List<CompletedUserTraining> result = (List<CompletedUserTraining>)query.list();
			logger.debug("The result of listByUserId is " + result);
			return result;
		} finally {
			session.close();
		}
	}
}
