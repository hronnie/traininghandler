package com.codeproj.traininghandler.model;

import java.util.Date;

public class UserLevel implements java.io.Serializable {

	private long userLevelId;
	private TrainingRef trainingRef;
	private User user;
	private Date completedDate;

	public UserLevel() {
	}

	public UserLevel(long userLevelId, TrainingRef trainingRef, User user) {
		this.userLevelId = userLevelId;
		this.trainingRef = trainingRef;
		this.user = user;
	}

	public UserLevel(long userLevelId, TrainingRef trainingRef, User user,
			Date completedDate) {
		this.userLevelId = userLevelId;
		this.trainingRef = trainingRef;
		this.user = user;
		this.completedDate = completedDate;
	}

	public long getUserLevelId() {
		return this.userLevelId;
	}

	public void setUserLevelId(long userLevelId) {
		this.userLevelId = userLevelId;
	}

	public TrainingRef getTrainingRef() {
		return this.trainingRef;
	}

	public void setTrainingRef(TrainingRef trainingRef) {
		this.trainingRef = trainingRef;
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
