package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class TrainingPrerequisite implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long trainingPrerequisiteId;
	private TrainingType dependentTrainingType;
	private TrainingType prerequisiteTrainingType;
	private Integer betweenMonth;

	public TrainingPrerequisite() {
	}

	public TrainingPrerequisite(Long trainingPrerequisiteId,
			TrainingType dependentTrainingTypeId, TrainingType prerequisiteTrainingTypeId, Integer betweenMonth) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
		this.dependentTrainingType = dependentTrainingTypeId;
		this.prerequisiteTrainingType = prerequisiteTrainingTypeId;
		this.betweenMonth = betweenMonth;
	}

	@Override
	public Long getId() {
		return this.trainingPrerequisiteId;
	}
	
	@Override
	public void setId(Long id) {
		this.trainingPrerequisiteId = id;
	}
	
	public TrainingType getDependentTrainingType() {
		return dependentTrainingType;
	}

	public void setDependentTrainingType(TrainingType dependentTrainingTypeId) {
		this.dependentTrainingType = dependentTrainingTypeId;
	}

	public TrainingType getPrerequisiteTrainingType() {
		return prerequisiteTrainingType;
	}

	public void setPrerequisiteTrainingType(
			TrainingType prerequisiteTrainingTypeId) {
		this.prerequisiteTrainingType = prerequisiteTrainingTypeId;
	}

	public Long getTrainingPrerequisiteId() {
		return this.trainingPrerequisiteId;
	}

	public void setTrainingPrerequisiteId(Long trainingPrerequisiteId) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
	}

	public Integer getBetweenMonth() {
		return this.betweenMonth;
	}

	public void setBetweenMonth(Integer betweenMonth) {
		this.betweenMonth = betweenMonth;
	}
}
