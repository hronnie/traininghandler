package com.codeproj.traininghandler.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.dao.common.AbstractHibernateDao;
import com.codeproj.traininghandler.model.TrainingType;

public class TrainingTypeDAOImpl extends AbstractHibernateDao<TrainingType> implements  TrainingTypeDAO {
	private SessionFactory sessionFactory;

	public TrainingTypeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	

    public TrainingTypeDAOImpl() {
        super();

        setClazz(TrainingType.class);
    }


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public TrainingType getTrainingTypeById(Long id) {
		return findOne(id);
	}
}
