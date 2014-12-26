package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;

public class UserLevel implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userLevelId;
	private TrainingType trainingType;
	private User user;
	private Date completedDate;

	public UserLevel() {
	}

	public UserLevel(Long userLevelId, TrainingType trainingType, User user) {
		this.userLevelId = userLevelId;
		this.trainingType = trainingType;
		this.user = user;
	}

	@Override
	public Long getId() {
		return this.userLevelId;
	}
	
	@Override
	public void setId(Long id) {
		this.userLevelId = id;
	}
	
	public UserLevel(Long userLevelId, TrainingType trainingType, User user,
			Date completedDate) {
		this.userLevelId = userLevelId;
		this.trainingType = trainingType;
		this.user = user;
		this.completedDate = completedDate;
	}

	public Long getUserLevelId() {
		return this.userLevelId;
	}

	public void setUserLevelId(Long userLevelId) {
		this.userLevelId = userLevelId;
	}

	public TrainingType getTrainingType() {
		return this.trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCompletedDate() {
		return this.completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((completedDate == null) ? 0 : completedDate.hashCode());
		result = prime * result
				+ ((trainingType == null) ? 0 : trainingType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((userLevelId == null) ? 0 : userLevelId.hashCode());
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
		if (!(obj instanceof UserLevel)) {
			return false;
		}
		UserLevel other = (UserLevel) obj;
		if (completedDate == null) {
			if (other.completedDate != null) {
				return false;
			}
		} else if (!completedDate.equals(other.completedDate)) {
			return false;
		}
		if (trainingType == null) {
			if (other.trainingType != null) {
				return false;
			}
		} else if (!trainingType.equals(other.trainingType)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		if (userLevelId == null) {
			if (other.userLevelId != null) {
				return false;
			}
		} else if (!userLevelId.equals(other.userLevelId)) {
			return false;
		}
		return true;
	}
	
}
