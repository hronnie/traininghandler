package com.codeproj.traininghandler.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.TraineeDAO;
import com.codeproj.traininghandler.dto.TraineeDto;
import com.codeproj.traininghandler.util.Constants;

public class TraineeDAOImpl implements TraineeDAO {
	
	private static final Logger logger = Logger.getLogger(TraineeDAOImpl.class);
	
	private SessionFactory sessionFactory;

	public TraineeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public TraineeDAOImpl() { /* empty constructor */ }


	@Override
	@Transactional
	public List<TraineeDto> getAllTrainees() {
        Session session = sessionFactory.openSession();
        try {
        	String queryStr = buildListAllTraineesQuery();
        	
            Query query = session.createSQLQuery(queryStr);
            List<Object[]> resultSet = query.list();
            Iterator<Object[]> rowIt = resultSet.iterator();
            
            List<TraineeDto> result = new ArrayList<>();
            while (rowIt.hasNext()) {
                Object[] row = rowIt.next();
                TraineeDto trainee = new TraineeDto();
                BigInteger userId = (BigInteger)row[0];
                trainee.setUserId(userId.longValue());
                BigInteger addressId = (BigInteger)row[1];
                trainee.setAddressId(addressId.longValue());
                trainee.setName((String)row[2]);
                trainee.setPostCode((String)row[3]);
                trainee.setAddress((String)row[4]);
                trainee.setPhone((String)row[5]);
                trainee.setEmail((String)row[6]);
                trainee.setCompletedTrainings((String)row[7]);
                result.add(trainee);
            }
            logger.debug("getAllTrainees>> " + result);
            return result;
        } finally {
            session.close();
        }
	}

	private String buildListAllTraineesQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append("  u.userId, ");
		sb.append("  u.addressId, ");
		sb.append("  u.name, ");
		sb.append("  a.postalCode, ");
		sb.append("  a.oneLineAddress, ");
		sb.append("  u.mobileNo, ");
		sb.append("  u.email, ");
		sb.append("  comp_training_sub_table.complTrNames ");
		sb.append(" FROM ");
		sb.append("  User AS u ");
		sb.append(" INNER JOIN Address a on (u.addressId = a.addressId) ");
		sb.append(" LEFT JOIN ");
		sb.append(" ( ");
		sb.append("  SELECT ");
		sb.append("   cut.userId AS complUserId, group_concat(CONCAT_WS(' - ', tt.levelNo, DATE_FORMAT(cut.completedDate,'%Y %b %d')) ORDER BY tt.trainingTypeId ASC SEPARATOR '<br> ') AS complTrNames ");
		sb.append("  FROM ");
		sb.append("   CompletedUserTraining cut ");
		sb.append("  LEFT JOIN TrainingType tt on (tt.trainingTypeId = cut.trainingTypeId) ");
		sb.append("  GROUP BY cut.userId ");
		sb.append("  ORDER BY cut.trainingTypeId DESC ");
		sb.append(" ) ");
		sb.append(" AS comp_training_sub_table ON (comp_training_sub_table.complUserId = u.userId) ");
		sb.append(" WHERE ");
		sb.append("  u.userTypeId = ");
		sb.append(Constants.DB_USER_USER_TYPE_ID_TRAINEE);
		return sb.toString();
	}
}
