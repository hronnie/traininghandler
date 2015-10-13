package com.codeproj.traininghandler.manager.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.dao.CompletedTrainingDAO;
import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.dao.UserRoleDAO;
import com.codeproj.traininghandler.model.User;

@Component
public class UserManager {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AddressDAO addressDAO;
	
	@Autowired
	CompletedTrainingDAO completedTrainingDAO;
	
	@Autowired
	UserRoleDAO userRoleDAO;
	

	public Long create(String name, String displayName,
			Date dob, String phoneNumber, String email, Long addressId) {
		
		User newUser = new User(name, displayName, dob, phoneNumber, email, addressId);
		return userDAO.create(newUser);
	}

	public Long getUserIdByEmailAndName(String email, String name) {
		return userDAO.getUserIdByEmailAndName(email, name);
	}

	public User getUserByUserId(Long userId) {
		return userDAO.getUserByUserId(userId);
	}

	public boolean edit(User newUser) {
		return userDAO.edit(newUser);
	}
	
	public boolean deleteTrainee(Long userId, Long addressId) {
		boolean complUserTrDeleteResult = completedTrainingDAO.deleteByUserId(userId);
		boolean userRoleDeleteResult = userRoleDAO.deleteByUserId(userId);
		boolean userDeleteResult = userDAO.delete(userId);
		boolean addressDeleteResult = addressDAO.deleteByUserId(addressId);
		
		return addressDeleteResult && complUserTrDeleteResult && userDeleteResult && userRoleDeleteResult;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public void setCompletedTrainingDAO(CompletedTrainingDAO completedTrainingDAO) {
		this.completedTrainingDAO = completedTrainingDAO;
	}

	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
}
