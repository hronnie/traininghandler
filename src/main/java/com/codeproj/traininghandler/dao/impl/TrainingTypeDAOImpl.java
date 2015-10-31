package com.codeproj.traininghandler.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.exceptions.DatabaseEntityNotFoundException;
import com.codeproj.traininghandler.model.TrainingType;
import com.codeproj.traininghandler.util.Constants;

public class TrainingTypeDAOImpl implements TrainingTypeDAO {
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
        sessionFactory.getCurrentSession().save(trainingType);
        return trainingType.getTrainingTypeId();
	}
	
	@Override
	@Transactional
	public boolean update(TrainingType trainingType) throws DatabaseEntityNotFoundException {
		try {
			sessionFactory.getCurrentSession().update(trainingType);
		} catch (HibernateException e) {
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST);
		}
		return true;
	}

	@Override
	@Transactional
	public TrainingType getTrainingTypeById(Long id) throws DatabaseEntityNotFoundException {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TrainingType.class);
			criteria.add(Restrictions.eq("trainingTypeId", id));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			TrainingType result = (TrainingType)criteria.uniqueResult();
			if (result == null) {
				throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND);
			}
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
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_REQUESTED_OBJECT_CANNOT_BE_FOUND);
		}
		return trainingTypes;
	}

	@Override
	@Transactional
	public boolean delete(Long trainingTypeId) throws DatabaseEntityNotFoundException {
		try {
			String hql = "delete from TrainingType where trainingTypeId= :trainingTypeId";
			sessionFactory.getCurrentSession().createQuery(hql).setLong("trainingTypeId", trainingTypeId).executeUpdate();
			return true;
		} catch (HibernateException e) {
			throw new DatabaseEntityNotFoundException(Constants.VALIDATION_ERR_MSG_TRAINING_TYPE_DOESNT_EXIST);
		}
	}
}
