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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((betweenMonth == null) ? 0 : betweenMonth.hashCode());
		result = prime
				* result
				+ ((dependentTrainingTypeId == null) ? 0
						: dependentTrainingTypeId.hashCode());
		result = prime
				* result
				+ ((prerequisiteTrainingTypeId == null) ? 0
						: prerequisiteTrainingTypeId.hashCode());
		result = prime
				* result
				+ ((trainingPrerequisiteId == null) ? 0
						: trainingPrerequisiteId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainingPrerequisite other = (TrainingPrerequisite) obj;
		if (betweenMonth == null) {
			if (other.betweenMonth != null)
				return false;
		} else if (!betweenMonth.equals(other.betweenMonth))
			return false;
		if (dependentTrainingTypeId == null) {
			if (other.dependentTrainingTypeId != null)
				return false;
		} else if (!dependentTrainingTypeId
				.equals(other.dependentTrainingTypeId))
			return false;
		if (prerequisiteTrainingTypeId == null) {
			if (other.prerequisiteTrainingTypeId != null)
				return false;
		} else if (!prerequisiteTrainingTypeId
				.equals(other.prerequisiteTrainingTypeId))
			return false;
		if (trainingPrerequisiteId == null) {
			if (other.trainingPrerequisiteId != null)
				return false;
		} else if (!trainingPrerequisiteId.equals(other.trainingPrerequisiteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingPrerequisite [trainingPrerequisiteId="
				+ trainingPrerequisiteId + ", dependentTrainingTypeId="
				+ dependentTrainingTypeId + ", prerequisiteTrainingTypeId="
				+ prerequisiteTrainingTypeId + ", betweenMonth=" + betweenMonth
				+ "]";
	}
}
