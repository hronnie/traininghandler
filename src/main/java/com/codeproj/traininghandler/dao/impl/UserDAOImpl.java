package com.codeproj.traininghandler.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.model.User;

public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public UserDAOImpl() { /* empty constructor */	}

	@Override
	@Transactional
	public Long create(User user) {
		logger.debug("Creating user with >> " + user);
        sessionFactory.getCurrentSession().save(user);
        return user.getUserId();
	}

	@Override
	@Transactional
	public Long getUserIdByEmailAndName(String email, String name) {
		Session session = null;
		try {
			logger.debug("getUserIdByEmailAndName, email>> " + email + ", name>> " + name);
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			Criterion andRest = Restrictions.and(Restrictions.eq("email", email), Restrictions.eq("name", name));
			criteria.add(andRest);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			User user = (User)criteria.uniqueResult();
			if (user == null) {
				logger.warn("User doesn't exist");
				return -1L;
			}
			logger.debug("result >> " + user.getUserId());
			return user.getUserId();
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public User getUserByUserId(Long userId) {
		Session session = null;
		try {
			logger.debug("getUserByUserId, userId>> " + userId);
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			User user = (User)criteria.uniqueResult();
			logger.debug("user result>> " + user);
			return user;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public boolean edit(User user) {
		sessionFactory.getCurrentSession().update(user);
		return true;	
	}

	@Override
	@Transactional
	public boolean delete(Long userId) {
		String hql = "delete from User where userId= :userId";
		sessionFactory.getCurrentSession().createQuery(hql).setLong("userId", userId).executeUpdate();
		return true;
	}
}
