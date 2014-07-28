package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;

public class UserLevel implements HibernatePersistable, Serializable {

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
}
