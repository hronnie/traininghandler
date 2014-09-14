package com.codeproj.traininghandler.dao.common;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements IOperations<T> {
    private Class<T> clazz;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    // API

    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    @Override
    @Transactional
    public final T findOne(final long id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Override
    @Transactional
    public final List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    @Transactional
    public final void create(final T entity) {
        Preconditions.checkNotNull(entity);
        // getCurrentSession().persist(entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    @Transactional
    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    @Override
    @Transactional
    public final void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    @Override
    @Transactional
    public final void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}