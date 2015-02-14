package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class TrainingPrerequisite implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long trainingPrerequisiteId;
	private Long dependentTrainingTypeId;
	private Long prerequisiteTrainingTypeId;
	private Integer betweenMonth;

	public TrainingPrerequisite() {
	}

	public TrainingPrerequisite(Long trainingPrerequisiteId,
			Long dependentTrainingTypeId, Long prerequisiteTrainingTypeId, Integer betweenMonth) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
		this.dependentTrainingTypeId = dependentTrainingTypeId;
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
		this.betweenMonth = betweenMonth;
	}

	public Long getTrainingPrerequisiteId() {
		return trainingPrerequisiteId;
	}

	public void setTrainingPrerequisiteId(Long trainingPrerequisiteId) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
	}

	public Long getDependentTrainingTypeId() {
		return dependentTrainingTypeId;
	}

	public void setDependentTrainingTypeId(Long dependentTrainingTypeId) {
		this.dependentTrainingTypeId = dependentTrainingTypeId;
	}

	public Long getPrerequisiteTrainingTypeId() {
		return prerequisiteTrainingTypeId;
	}

	public void setPrerequisiteTrainingTypeId(Long prerequisiteTrainingTypeId) {
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
	}

	public Integer getBetweenMonth() {
		return betweenMonth;
	}

	public void setBetweenMonth(Integer betweenMonth) {
		this.betweenMonth = betweenMonth;
	}
	
}
