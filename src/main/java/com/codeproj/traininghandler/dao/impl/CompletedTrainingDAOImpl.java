package com.codeproj.traininghandler.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dto.CompletedUserTrainingDto;
import com.codeproj.traininghandler.model.CompletedUserTraining;
import com.codeproj.traininghandler.model.User;

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
	
	@Override
	@Transactional
	public List<CompletedUserTrainingDto> listViewableByUserId(Long userId) {
		logger.debug("listViewableByUserId with the following userId > " + userId);
        Session session = sessionFactory.openSession();
        try {
        	
            Query query = session.createSQLQuery(buildListViewableByUserIdQuery());
            query.setLong("userId", userId);
            List<Object[]> resultSet = query.list();
            Iterator<Object[]> rowIt = resultSet.iterator();
            
            List<CompletedUserTrainingDto> result = new ArrayList<>();
            while (rowIt.hasNext()) {
                Object[] row = rowIt.next();
                CompletedUserTrainingDto compUsrTr = new CompletedUserTrainingDto();
                BigInteger userIdRs = (BigInteger)row[0];
                BigInteger ttId = (BigInteger)row[1];
                compUsrTr.setUserId(userIdRs.longValue());
                compUsrTr.setTrainingTypeId(ttId.longValue());
                compUsrTr.setCompletedDate((Date)row[2]);
                compUsrTr.setTrainingTypeName((String)row[3]);
                result.add(compUsrTr);
            }
            logger.debug("result CompletedUserTrainingDtos>> " + result);
            return result;
        } finally {
            session.close();
        }
	}
	
	private String buildListViewableByUserIdQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append("  cut.userId, cut.trainingTypeId, cut.completedDate, tt.name ");
		sb.append(" FROM ");
		sb.append("  CompletedUserTraining  cut ");
		sb.append(" INNER JOIN TrainingType tt ON (cut.trainingTypeId = tt.trainingTypeId) ");
		sb.append(" WHERE ");
		sb.append(" cut.userId = :userId");
		sb.append(" ORDER BY cut.trainingTypeId ");
		return sb.toString();
	}
}
