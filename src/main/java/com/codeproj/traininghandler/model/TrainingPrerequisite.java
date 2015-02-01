package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class TrainingPrerequisite implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long trainingPrerequisiteId;
	private TrainingType dependentTrainingTypeId;
	private TrainingType prerequisiteTrainingTypeId;
	private Integer betweenMonth;

	public TrainingPrerequisite() {
	}

	public TrainingPrerequisite(Long trainingPrerequisiteId,
			TrainingType dependentTrainingTypeId, TrainingType prerequisiteTrainingTypeId, Integer betweenMonth) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
		this.dependentTrainingTypeId = dependentTrainingTypeId;
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
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
	
	public TrainingType getDependentTrainingTypeId() {
		return dependentTrainingTypeId;
	}

	public void setDependentTrainingTypeId(TrainingType dependentTrainingTypeId) {
		this.dependentTrainingTypeId = dependentTrainingTypeId;
	}

	public TrainingType getPrerequisiteTrainingTypeId() {
		return prerequisiteTrainingTypeId;
	}

	public void setPrerequisiteTrainingTypeId(
			TrainingType prerequisiteTrainingTypeId) {
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
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
