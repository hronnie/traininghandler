package com.codeproj.traininghandler.dao;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.codeproj.traininghandler.model.HibernatePersistable;

public abstract class ReadOnlyDAO<T extends HibernatePersistable>  {

//    public synchronized Logger getLogger() {
//        return Logger.getLogger(getClass());
//    }
//
//    /**
//     * Method used to flush the current hibernate session.
//     */
//    public synchronized void flush() {
//        this.getSessionFactory().getCurrentSession().flush();
//    }
//
//    /**
//     * Initiate the DAO.
//     *
//     * This method checks the schema version to ensure that the DAO is working
//     * with the expected schema.
//     */
//    @Override
//    public void initDao() {
//    }
//
//    //DAO Methods
//    public abstract Class<T> getBaseClass();
//
//    public synchronized T getObjectById(Long id) {
//        return getObjectById(getBaseClass(), id);
//    }
//
//    public synchronized <S extends T> S getObjectById(Class<S> clazz, Long id) {
//        S object = this.<S>cast(getHibernateTemplate().get(clazz, id));
//
//        if (object == null) {
//            throw new ObjectRetrievalFailureException(clazz, id);
//        }
//
//        return object;
//    }
//
//    public synchronized List<T> getAllObjects() {
//        return getAllObjects(getBaseClass());
//    }
//
//    public synchronized <S extends T> List<S> getAllObjects(Class<S> clazz) {
//        List<S> results = this.castList(getHibernateTemplate().find(
//                " FROM " + clazz.getName()
//                + " ORDER BY id ASC"), clazz);
//        return results;
//    }
//
//    /*
//     * Unchecked casting
//     */
//    /**
//     * Make an unchecked cast of a List.
//     *
//     * This is useful because it allows you to make an unchecked cast within a
//     * method, without having to taint the whole method with a
//     * SuppressWarnings("unchecked") annotation.
//     *
//     * @param <T> the element type
//     * @param list the List to cast
//     * @param clazz the element class
//     * @return a cast List
//     */
//    @SuppressWarnings("unchecked")
//    protected <T> List<T> castList(List list, Class<T> clazz) {
//        return list;
//    }
//
//    /**
//     * Make an unchecked cast of a Set.
//     *
//     * This is useful because it allows you to make an unchecked cast within a
//     * method, without having to taint the whole method with a
//     * SuppressWarnings("unchecked") annotation.
//     *
//     * @param <T> the element type
//     * @param set the Set to cast
//     * @param clazz the element class
//     * @return a cast Set
//     */
//    @SuppressWarnings("unchecked")
//    private <T> Set<T> castSet(Set set, Class<T> clazz) {
//        return set;
//    }
//
//    /**
//     * Make an unchecked cast of an object.
//     *
//     * This is useful because it allows you to make an unchecked cast within a
//     * method, without having to taint the whole method with a
//     * SuppressWarnings("unchecked") annotation.
//     *
//     * @param <T> the object type
//     * @param object the object to class
//     * @return the cast object
//     */
//    @SuppressWarnings("unchecked")
//    private <T> T cast(Object object) {
//        return (T) object;
//    }
}