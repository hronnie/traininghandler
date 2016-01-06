package com.codeproj.traininghandler.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.util.Constants;

public class TrainingTypeDAOImpl implements TrainingTypeDAO {
	
	private static final Logger logger = Logger.getLogger(TrainingTypeDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public TrainingTypeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public TrainingTypeDAOImpl() {
		// empty constructor
	}

	@Override
	@Transactional
	public Long create(TrainingType trainingType) {
		logger.debug("Creating TrainingType with>> " + trainingType);
        sessionFactory.getCurrentSession().save(trainingType);
        return trainingType.getTrainingTypeId();
	}
	
	@Override
	@Transactional
	public boolean update(TrainingType trainingType) throws DatabaseEntityNotFoundException {
		try {
			logger.debug("Updating TrainingType with>> " + trainingType);
			sessionFactory.getCurrentSession().update(trainingType);
		} catch (HibernateException e) {
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST);
		}
		return true;
	}


	@Override
	@Transactional
	public TrainingType getTrainingTypeById(Long trainingTypeId) throws DatabaseEntityNotFoundException {
		Session session = null;
		try {
			session = sessionFactory.openSession();
            Query query = session.createSQLQuery("select * from TrainingType where trainingTypeId= :trainingTypeId");
            query.setParameter("trainingTypeId", trainingTypeId);
            Object[] resultSet = (Object[])query.uniqueResult();
            if (resultSet == null || resultSet.length < 1) {
            	logger.warn("getTrainingTypeById with>> " + trainingTypeId + " and TrainingType hasn't found!");
            	throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND);
            }
            TrainingType result = new TrainingType();
            BigInteger idBint = (BigInteger)resultSet[0];
            result.setTrainingTypeId(idBint.longValue());
            result.setName((String)resultSet[1]);
            result.setLevelNo((String)resultSet[2]);
            result.setDescription((String)resultSet[3]);
			logger.debug("getTrainingTypeById with>> " + trainingTypeId + " and the result is >> " + result);
			return result;
		} finally {
			session.close();
		}
	}
	
	@Override
	@Transactional
	public List<TrainingType> getAll() throws DatabaseEntityNotFoundException {
		List<TrainingType> trainingTypes = (List<TrainingType>) sessionFactory.getCurrentSession()
		.createCriteria(TrainingType.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.list();
		if (trainingTypes == null || trainingTypes.isEmpty()) {
			logger.error("TrainingType list is empty!");
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND);
		}
		return trainingTypes;
	}

	@Override
	@Transactional
	public boolean delete(Long trainingTypeId) throws DatabaseEntityNotFoundException {
		try {
			logger.debug("Deleting TrainingType with trainingTypeId >> " + trainingTypeId);
			String hql = "delete from TrainingType where trainingTypeId= :trainingTypeId";
			sessionFactory.getCurrentSession().createQuery(hql).setLong("trainingTypeId", trainingTypeId).executeUpdate();
			return true;
		} catch (HibernateException e) {
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST);
		}
	}
}
