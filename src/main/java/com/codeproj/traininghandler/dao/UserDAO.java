package com.codeproj.traininghandler.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.model.User;

@Service
@Transactional
public interface UserDAO {
	public Long create(User user);

	public Long getUserIdByEmailAndName(String email, String name);

	public User getUserByUserId(Long testUserId);

	public boolean edit(User testUserModel);
	
	public boolean delete(Long userId);
}
