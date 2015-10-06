package com.codeproj.traininghandler.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserRoleDAO {
	public boolean deleteByUserId(Long userId);
}
