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
			throw new DatabaseEntityNotFoundException("The requested object cannot be found");
		}
		return true;
	}

	@Override
	@Transactional
	public TrainingType getTrainingTypeById(Long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TrainingType.class);
		criteria.add(Restrictions.eq("trainingTypeId", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (TrainingType)criteria.uniqueResult();
	}
	
	@Override
	@Transactional
	public List<TrainingType> getAll() {
		List<TrainingType> trainingTypes = (List<TrainingType>) sessionFactory.getCurrentSession()
		.createCriteria(TrainingType.class)
		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
		.list();
		return trainingTypes;
	}

	@Override
	@Transactional
	public boolean delete(Long trainingTypeId) {
		sessionFactory.getCurrentSession().delete(trainingTypeId);
		return true;
	}
}
