package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class TrainingPrerequisite implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long trainingPrerequisiteId;
	private TrainingType dependentTrainingType;
	private TrainingType prerequisiteTrainingType;
	private Integer betweenMonth;

	public TrainingPrerequisite() {
	}

	public TrainingPrerequisite(Long trainingPrerequisiteId,
			TrainingType dependentTrainingType, TrainingType prerequisiteTrainingType, Integer betweenMonth) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
		this.dependentTrainingType = dependentTrainingType;
		this.prerequisiteTrainingType = prerequisiteTrainingType;
		this.betweenMonth = betweenMonth;
	}

	public Long getTrainingPrerequisiteId() {
		return trainingPrerequisiteId;
	}

	public void setTrainingPrerequisiteId(Long trainingPrerequisiteId) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
	}

	public TrainingType getDependentTrainingType() {
		return dependentTrainingType;
	}

	public void setDependentTrainingType(TrainingType dependentTrainingType) {
		this.dependentTrainingType = dependentTrainingType;
	}

	public TrainingType getPrerequisiteTrainingType() {
		return prerequisiteTrainingType;
	}

	public void setPrerequisiteTrainingType(TrainingType prerequisiteTrainingType) {
		this.prerequisiteTrainingType = prerequisiteTrainingType;
	}

	public Integer getBetweenMonth() {
		return betweenMonth;
	}

	public void setBetweenMonth(Integer betweenMonth) {
		this.betweenMonth = betweenMonth;
	}
	
}
