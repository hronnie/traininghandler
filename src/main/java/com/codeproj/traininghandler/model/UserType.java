package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class UserType implements HibernatePersistable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userTypeId == null) ? 0 : userTypeId.hashCode());
		result = prime * result
				+ ((userTypeName == null) ? 0 : userTypeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserType)) {
			return false;
		}
		UserType other = (UserType) obj;
		if (userTypeId == null) {
			if (other.userTypeId != null) {
				return false;
			}
		} else if (!userTypeId.equals(other.userTypeId)) {
			return false;
		}
		if (userTypeName == null) {
			if (other.userTypeName != null) {
				return false;
			}
		} else if (!userTypeName.equals(other.userTypeName)) {
			return false;
		}
		return true;
	}
	
}
