package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class UserType implements HibernatePersistable, Serializable {
	
	private Long userTypeId;
	private String userTypeName;
	
	public UserType() {
		// empty constructor
	}

	public UserType(Long userTypeId, String userTypeName) {
		this.userTypeId = userTypeId;
		this.userTypeName = userTypeName;
	}

	@Override
	public Long getId() {
		return this.userTypeId;
	}

	@Override
	public void setId(Long id) {
		userTypeId = id;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
}
