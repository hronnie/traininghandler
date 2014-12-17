package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;

public class CompletedUserTraining implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;

	private Long completedUserTrainingId;
	private User user;
	private TrainingType trainingType;
	private Date completedDate;
	
	public CompletedUserTraining() {
		// empty constructor
	}

	public CompletedUserTraining(Long completedUserTrainingId,
			TrainingType trainingType, Date completedDate, User user) {
		this.setCompletedUserTrainingId(completedUserTrainingId);
		this.trainingType = trainingType;
		this.completedDate = completedDate;
		this.user = user;
	}

	@Override
	public Long getId() {
		return this.completedUserTrainingId;
	}

	@Override
	public void setId(Long id) {
		this.completedUserTrainingId = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCompletedUserTrainingId() {
		return completedUserTrainingId;
	}

	public void setCompletedUserTrainingId(Long completedUserTrainingId) {
		this.completedUserTrainingId = completedUserTrainingId;
	}

	public TrainingType getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
}
