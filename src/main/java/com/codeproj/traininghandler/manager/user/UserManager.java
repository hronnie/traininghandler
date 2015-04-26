package com.codeproj.traininghandler.manager.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.UserDAO;
import com.codeproj.traininghandler.model.User;

@Component
public class UserManager {
	
	@Autowired
	UserDAO userDAO;

	public Long create(String name, String displayName,
			Date dob, String phoneNumber, String email, Long addressId) {
		
		User newUser = new User(name, displayName, dob, phoneNumber, email, addressId);
		return userDAO.create(newUser);
	}

	public Long getUserIdByEmail(String email) {
		return userDAO.getUserIdByEmail(email);
	}

	public User getUserByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean edit(User newUser) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
