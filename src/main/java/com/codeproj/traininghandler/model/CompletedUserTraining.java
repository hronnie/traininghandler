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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((completedDate == null) ? 0 : completedDate.hashCode());
		result = prime
				* result
				+ ((completedUserTrainingId == null) ? 0
						: completedUserTrainingId.hashCode());
		result = prime * result
				+ ((trainingType == null) ? 0 : trainingType.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CompletedUserTraining)) {
			return false;
		}
		CompletedUserTraining other = (CompletedUserTraining) obj;
		if (completedDate == null) {
			if (other.completedDate != null) {
				return false;
			}
		} else if (!completedDate.equals(other.completedDate)) {
			return false;
		}
		if (completedUserTrainingId == null) {
			if (other.completedUserTrainingId != null) {
				return false;
			}
		} else if (!completedUserTrainingId
				.equals(other.completedUserTrainingId)) {
			return false;
		}
		if (trainingType == null) {
			if (other.trainingType != null) {
				return false;
			}
		} else if (!trainingType.equals(other.trainingType)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}
	
}
