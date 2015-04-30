package com.codeproj.traininghandler.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.model.User;

public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public UserDAOImpl() { /* empty constructor */	}

	@Override
	@Transactional
	public Long create(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user.getUserId();
	}

	@Override
	@Transactional
	public Long getUserIdByEmail(String email) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			User user = (User)criteria.uniqueResult();
			if (user == null) {
				return -1L;
			}
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
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			User user = (User)criteria.uniqueResult();
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
}
