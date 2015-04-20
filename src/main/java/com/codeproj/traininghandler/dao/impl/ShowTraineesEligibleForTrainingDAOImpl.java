package com.codeproj.traininghandler.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.ShowTraineesEligibleForTrainingDAO;
import com.codeproj.traininghandler.domain.TrainingTypePrerequisite;
import com.codeproj.traininghandler.model.TrainingPrerequisite;
import com.codeproj.traininghandler.model.User;
import com.codeproj.traininghandler.util.Constants;

public class ShowTraineesEligibleForTrainingDAOImpl implements
		ShowTraineesEligibleForTrainingDAO {

	private SessionFactory sessionFactory;
	
	public ShowTraineesEligibleForTrainingDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public ShowTraineesEligibleForTrainingDAOImpl() {
		// empty constructor
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TrainingPrerequisite> getPrerequisitesByTrainingId(
			Long trainingTypeId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(TrainingPrerequisite.class);
			criteria.add(Restrictions.eq("dependentTrainingTypeId", trainingTypeId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return (List<TrainingPrerequisite>)criteria.list();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getEligibleTrainees(
			List<TrainingTypePrerequisite> trainingPrerequisites) {
		
        Session session = sessionFactory.openSession();
        try {
        	if (trainingPrerequisites == null) {
        		return new ArrayList<>();
        	}
        	String queryStr = null;
    		if (trainingPrerequisites.size() > 0) {
    			queryStr = buildEligibleTraineesQuery(trainingPrerequisites.size());
    		} else {
    			queryStr = buildEligibleTraineesQueryWithNoPrereq(trainingPrerequisites.size());
    		}
        	
            Query query = session.createSQLQuery(queryStr);
            addParameters(query, trainingPrerequisites);
            List<Object[]> resultSet = query.list();
            Iterator<Object[]> rowIt = resultSet.iterator();
            
            List<User> result = new ArrayList<>();
            while (rowIt.hasNext()) {
                Object[] row = rowIt.next();
                User user = new User();
                BigInteger userId = (BigInteger)row[0];
                user.setUserId(userId.longValue());
                user.setName((String)row[1]);
                user.setEmail((String)row[2]);
                user.setMobileNo((String)row[3]);
                
                result.add(user);
            }
            return result;
        } finally {
            session.close();
        }
	}

	private void addParameters(Query query, List<TrainingTypePrerequisite> trainingPrerequisites) {
		int cnt = 0;
		for (TrainingTypePrerequisite item : trainingPrerequisites) {
			query.setParameter(cnt++, item.getPrerequisiteTrainingTypeId());
			query.setParameter(cnt++, item.getMinCompletedDate());
		}
	}

	private String buildEligibleTraineesQuery(int size) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DISTINCT ");
		sb.append("  u.userId, u.name, u.email, u.mobileNo ");
		sb.append(" FROM ");
		sb.append("  CompletedUserTraining cu ");
		sb.append(" INNER JOIN User u ON (u.userId = cu.userId) ");
		sb.append(" WHERE ");
		sb.append(" u.userTypeId =  ");
		sb.append(Constants.DB_USER_USER_TYPE_ID_TRAINEE);
		sb.append(" AND ");
		for (int i = 0; i < size; i++) {
			sb.append(" (cu.trainingTypeId = ? AND cu.completedDate <= ?) ");
			if (i != size - 1) {
				sb.append(" OR ");
			}
		}
		return sb.toString();
	}
	
	private String buildEligibleTraineesQueryWithNoPrereq(int size) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT DISTINCT ");
		sb.append("  u.userId, u.name, u.email, u.mobileNo ");
		sb.append(" FROM ");
		sb.append("  User u ");
		sb.append(" WHERE ");
		sb.append(" u.userTypeId = ");
		sb.append(Constants.DB_USER_USER_TYPE_ID_TRAINEE);
		return sb.toString();
	}
}
