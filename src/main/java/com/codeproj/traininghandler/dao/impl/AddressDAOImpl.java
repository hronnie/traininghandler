package com.codeproj.traininghandler.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.model.Address;
import com.codeproj.traininghandler.model.User;

public class AddressDAOImpl implements AddressDAO {
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

	@Override
	@Transactional
	public boolean edit(Address address) {
		sessionFactory.getCurrentSession().update(address);
		return true;
	}

	@Override
	public Address getAddressByAddressId(Long addressId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Address.class);
			criteria.add(Restrictions.eq("addressId", addressId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return (Address)criteria.uniqueResult();
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public boolean deleteByUserId(Long addressId) {
		String hql = "delete from Address where addressId= :addressId";
		sessionFactory.getCurrentSession().createQuery(hql).setLong("addressId", addressId).executeUpdate();
		return true;
	}
}
