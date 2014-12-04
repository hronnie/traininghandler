package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;

public class CompletedUserTraining implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;

	private Long completedUserTrainingId;
	private Long trainingTypeId;
	private Date completedDate;
	
	public CompletedUserTraining() {
		// empty constructor
	}

	public CompletedUserTraining(Long completedUserTrainingId,
			Long trainingTypeId, Date completedDate) {
		this.completedUserTrainingId = completedUserTrainingId;
		this.trainingTypeId = trainingTypeId;
		this.completedDate = completedDate;
	}

	@Override
	public Long getId() {
		return this.completedUserTrainingId;
	}

	@Override
	public void setId(Long id) {
		this.completedUserTrainingId = id;
	}

	public Long getTrainingTypeId() {
		return trainingTypeId;
	}

	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
}
