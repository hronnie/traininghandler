package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TrainingType implements  Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long trainingTypeId;
	private String name;
	private String levelNo;
	private String description;
	private Set<TrainingPrerequisite> trainingPrerequisites = new HashSet<>();

	public TrainingType() {
		// empty constructor
	}
	
	public TrainingType(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public TrainingType(String name,
			String levelNo, String description) {
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
	}
	
	public TrainingType(Long trainingTypeId, String name,
			String levelNo, String description) {
		this.trainingTypeId = trainingTypeId;
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
	}
	
	public TrainingType(Long trainingTypeId, String name,
			String levelNo, String description, Set<TrainingPrerequisite> trainingPrerequisites) {
		this.trainingTypeId = trainingTypeId;
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
		this.trainingPrerequisites = trainingPrerequisites;
	}

	public Long getTrainingTypeId() {
		return this.trainingTypeId;
	}

	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevelNo() {
		return this.levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TrainingPrerequisite> getTrainingPrerequisites() {
		return trainingPrerequisites;
	}

	public void setTrainingPrerequisites(
			Set<TrainingPrerequisite> trainingPrerequisites) {
		this.trainingPrerequisites = trainingPrerequisites;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((levelNo == null) ? 0 : levelNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((trainingTypeId == null) ? 0 : trainingTypeId.hashCode());
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
		if (!(obj instanceof TrainingType)) {
			return false;
		}
		TrainingType other = (TrainingType) obj;
		if (trainingTypeId != null && other.trainingTypeId != null && trainingTypeId == other.trainingTypeId) {
			return true;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (levelNo == null) {
			if (other.levelNo != null) {
				return false;
			}
		} else if (!levelNo.equals(other.levelNo)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (trainingTypeId == null) {
			if (other.trainingTypeId != null) {
				return false;
			}
		} else if (!trainingTypeId.equals(other.trainingTypeId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TrainingType [trainingTypeId=" + trainingTypeId + ", name="
				+ name + ", levelNo=" + levelNo + ", description="
				+ description + ", trainingPrerequisites="
				+ trainingPrerequisites + "]";
	}
}
