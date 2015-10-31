package com.codeproj.traininghandler.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.codeproj.traininghandler.rest.common.BaseResponse;

@XmlRootElement(name="TrainingType")
public class TrainingTypeDto extends BaseResponse {
	
	private Long trainingTypeId;
	private String name;
	private String levelNo;
	private String description;
	
	public TrainingTypeDto() {
		//empty constructor
	}
	
	public TrainingTypeDto(String message) {
		super(false, message);
	}
	
	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TrainingTypeDto(Long trainingTypeId, String name, String levelNo, String description) {
		this.trainingTypeId = trainingTypeId;
		this.levelNo = levelNo;
		this.name = name;
		this.description = description;
	}
	
	@XmlElement(name="trainingTypeId")
	public Long getTrainingTypeId() {
		return trainingTypeId;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	@XmlElement(name="levelNo")
	public String getLevelNo() {
		return levelNo;
	}
	
	@XmlElement(name="description")
	public String getDescription() {
		return description;
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
