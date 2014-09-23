package com.codeproj.traininghandler.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.dao.TrainingTypeDAO;
import com.codeproj.traininghandler.model.TrainingType;

public class TrainingTypeDAOImpl implements TrainingTypeDAO {
	private SessionFactory sessionFactory;

	public TrainingTypeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public TrainingType getTrainingTypeById(Long id) {
		//sessionFactory.getCurrentSession().
		return null;
//		return findOne(id);
	}
	
	@Override
	@Transactional
	public List<TrainingType> getAll() {
		@SuppressWarnings("unchecked")
		List<TrainingType> trainingTypes = (List<TrainingType>) sessionFactory.getCurrentSession()
				.createCriteria(TrainingType.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return trainingTypes;
				
	}
	
//	
//	 *     @Override
//    @Transactional
//    public List<User> list() {
//        @SuppressWarnings("unchecked")
//        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
//                .createCriteria(User.class)
//                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
// 
//        return listUser;
//    }(non-Javadoc) 
//	 * @see com.codeproj.traininghandler.dao.TrainingTypeDAO#create(com.codeproj.traininghandler.model.TrainingType)
//	 */

	@Override
	@Transactional
	public void create(TrainingType trainingType) {
        sessionFactory.getCurrentSession().save(trainingType);
	}

}
