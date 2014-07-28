package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;

public class TrainingPrerequisite implements HibernatePersistable, Serializable {

	private Long trainingPrerequisiteId;
	private TrainingType trainingType;
	private Training training;
	private Date completedDate;

	public TrainingPrerequisite() {
	}

	public TrainingPrerequisite(Long trainingPrerequisiteId,
			TrainingType trainingType, Training training, Date completedDate) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
		this.trainingType = trainingType;
		this.training = training;
		this.completedDate = completedDate;
	}

	@Override
	public Long getId() {
		return this.trainingPrerequisiteId;
	}
	
	@Override
	public void setId(Long id) {
		this.trainingPrerequisiteId = id;
	}
	
	public Long getTrainingPrerequisiteId() {
		return this.trainingPrerequisiteId;
	}

	public void setTrainingPrerequisiteId(Long trainingPrerequisiteId) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
	}

	public TrainingType getTrainingType() {
		return this.trainingType;
	}

	public void rainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public Training getTraining() {
		return this.training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Date getCompletedDate() {
		return this.completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
}
