package com.codeproj.traininghandler.manager.user;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.model.User;

@Component
public class UserManager {
	
	UserDAO userDAO;

	public Long create(String name, String displayName,
			Date dob, String phoneNumber, String email, Long addressId) {
		
		User newUser = new User(name, displayName, dob, phoneNumber, email, addressId);
		return userDAO.create(newUser);
	}

	public Long getUserIdByEmail(String email) {
		return userDAO.getUserIdByEmail(email);
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
