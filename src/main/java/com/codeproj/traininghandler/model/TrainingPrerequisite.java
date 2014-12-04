package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;

public class TrainingPrerequisite implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long trainingPrerequisiteId;
	private TrainingType dependentTrainingTypeId;
	private TrainingType prerequisiteTrainingTypeId;
	private Date completedDate;

	public TrainingPrerequisite() {
	}

	public TrainingPrerequisite(Long trainingPrerequisiteId,
			TrainingType dependentTrainingTypeId, TrainingType prerequisiteTrainingTypeId, Date completedDate) {
		this.trainingPrerequisiteId = trainingPrerequisiteId;
		this.dependentTrainingTypeId = dependentTrainingTypeId;
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
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

	public Date getCompletedDate() {
		return this.completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
}
