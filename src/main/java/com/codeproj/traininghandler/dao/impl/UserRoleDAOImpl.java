package com.codeproj.traininghandler.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.UserRoleDAO;

public class UserRoleDAOImpl implements UserRoleDAO {
	
	private static final Logger logger = Logger.getLogger(UserRoleDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	public UserRoleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public boolean deleteByUserId(Long userId) {
		logger.debug("deleting userRole with userId>> " + userId);
		String hql = "delete from UserRole where userId= :userId";
		sessionFactory.getCurrentSession().createQuery(hql).setLong("userId", userId).executeUpdate();
		return true;
	}

}
