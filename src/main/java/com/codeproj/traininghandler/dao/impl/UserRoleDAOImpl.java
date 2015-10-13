package com.codeproj.traininghandler.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.UserRoleDAO;

public class UserRoleDAOImpl implements UserRoleDAO {
	private SessionFactory sessionFactory;
	
	public UserRoleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public boolean deleteByUserId(Long userId) {
		String hql = "delete from UserRole where userId= :userId";
		sessionFactory.getCurrentSession().createQuery(hql).setLong("userId", userId).executeUpdate();
		return true;
	}

}
