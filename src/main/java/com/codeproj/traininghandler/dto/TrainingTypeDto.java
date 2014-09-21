package com.codeproj.traininghandler.dto;

public class TrainingTypeDto {
	
	private Long trainingTypeId;
	private String name;
	private String levelNo;
	private String description;
	
	public TrainingTypeDto() {
		//empty constructor
	}
	
	public TrainingTypeDto(Long trainingTypeId, String name, String levelNo, String description) {
		this.trainingTypeId = trainingTypeId;
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
	}
	
	public Long getTrainingTypeId() {
		return trainingTypeId;
	}

	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevelNo() {
		return levelNo;
	}
	
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
		if (!(obj instanceof TrainingTypeDto)) {
			return false;
		}
		TrainingTypeDto other = (TrainingTypeDto) obj;
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
		return "TrainingTypeDto [trainingTypeId=" + trainingTypeId + ", name="
				+ name + ", levelNo=" + levelNo + ", description="
				+ description + "]";
	}
}
