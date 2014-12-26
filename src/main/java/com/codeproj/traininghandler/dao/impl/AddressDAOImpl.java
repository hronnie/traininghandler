package com.codeproj.traininghandler.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.model.Address;

public class AddressDAOImpl implements AddressDAO{
	private SessionFactory sessionFactory;

	public AddressDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public AddressDAOImpl() { /* empty constructor */ }
	
	@Override
	@Transactional
	public Long create(Address address) {
        sessionFactory.getCurrentSession().save(address);
        return address.getAddressId();
	}
}
