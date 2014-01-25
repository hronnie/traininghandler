package com.codeproj.traininghandler.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.model.AbstractPersistentObject;

@Transactional(propagation=Propagation.MANDATORY)
public class GenericDAOImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, PK> {

	private Class<T> type;

	public GenericDAOImpl(SessionFactory sessionFactory, Class<T> type) {
		super.setSessionFactory(sessionFactory);
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public PK create(T o) {
		return (PK) getSession().save(o);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public T get(PK id) {
		T value = (T) getSession().get(type, id);
		if (value == null) {
            return null;
        }

        if (value instanceof HibernateProxy) {
			Hibernate.initialize(value);
	        value = (T) ((HibernateProxy) value).getHibernateLazyInitializer().getImplementation();
        }
        return value;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<T> getAll() {
		Criteria crit = getSession().createCriteria(type);
		return crit.list();
	}
	
	public void createOrUpdate(T o) {
		if (o instanceof AbstractPersistentObject) {
			if (((AbstractPersistentObject) o).isCreation()) {
				getSession().saveOrUpdate(o);
			} else {
				getSession().merge(o);
			}
		} else {
			throw new RuntimeException("this method support only AbstractPersistentObject");
		}
	}


	public void update(T o) {
		getSession().update(o);
	}

	public void delete(T o) {
		getSession().delete(o);
	}

}
