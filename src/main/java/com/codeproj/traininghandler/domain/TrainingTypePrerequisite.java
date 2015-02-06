package com.codeproj.traininghandler.domain;

import java.util.Date;

import com.codeproj.traininghandler.util.ThDateUtils;

public class TrainingTypePrerequisite {
	
	Long prerequisiteTrainingTypeId;
	Date minCompletedDate;
	
	public TrainingTypePrerequisite() {
		//empty constructor
	}

	public TrainingTypePrerequisite(Long prerequisiteTrainingTypeId,
			Date minCompletedDate) {
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
		this.minCompletedDate = minCompletedDate;
	}

	public Long getPrerequisiteTrainingTypeId() {
		return prerequisiteTrainingTypeId;
	}

	public void setPrerequisiteTrainingTypeId(Long prerequisiteTrainingTypeId) {
		this.prerequisiteTrainingTypeId = prerequisiteTrainingTypeId;
	}

	public Date getMinCompletedDate() {
		return minCompletedDate;
	}

	public void setMinCompletedDate(Date minCompletedDate) {
		this.minCompletedDate = minCompletedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((minCompletedDate == null) ? 0 : minCompletedDate.hashCode());
		result = prime
				* result
				+ ((prerequisiteTrainingTypeId == null) ? 0
						: prerequisiteTrainingTypeId.hashCode());
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
		if (!(obj instanceof TrainingTypePrerequisite)) {
			return false;
		}
		TrainingTypePrerequisite other = (TrainingTypePrerequisite) obj;
		if (minCompletedDate == null) {
			if (other.minCompletedDate != null) {
				return false;
			}
		} else if (!ThDateUtils.isDateEqualsWithoutMs(minCompletedDate, other.minCompletedDate)) {
			return false;
		}
		if (prerequisiteTrainingTypeId == null) {
			if (other.prerequisiteTrainingTypeId != null) {
				return false;
			}
		} else if (!prerequisiteTrainingTypeId
				.equals(other.prerequisiteTrainingTypeId)) {
			return false;
		}
		return true;
	}
}
