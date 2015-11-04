package com.codeproj.traininghandler.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.model.Address;

public class AddressDAOImpl implements AddressDAO {
	
	private static final Logger logger = Logger.getLogger(AddressDAOImpl.class);
	
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
		logger.debug("Editing the following address" + address);
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
			Address result = (Address)criteria.uniqueResult();
			logger.debug("getAddressByAddressId result with addressId> " + addressId + " is> " + result);
			return result;
		} finally {
			session.close();
		}
	}

	@Override
	@Transactional
	public boolean deleteByUserId(Long addressId) {
		logger.debug("Deleting address object with the following addressId> " + addressId);
		String hql = "delete from Address where addressId= :addressId";
		sessionFactory.getCurrentSession().createQuery(hql).setLong("addressId", addressId).executeUpdate();
		return true;
	}
}
