package com.codeproj.traininghandler.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.codeproj.traininghandler.rest.common.BaseResponse;

@XmlRootElement(name="TrainingTypeDtos")
@XmlSeeAlso(TrainingTypeDto.class)
public class TrainingTypeDtos extends BaseResponse {
	
	List<TrainingTypeDto> trainingTypeDtoList;
	
	public TrainingTypeDtos() {} // empty constructor
	
	public TrainingTypeDtos(String message) {
		super(message);
	}

	public TrainingTypeDtos(List<TrainingTypeDto> trainingTypeDtoList) {
		super(true);
		this.trainingTypeDtoList = trainingTypeDtoList;
	}

	@XmlElement(name="trainingTypeDtoList")
	public List<TrainingTypeDto> getTrainingTypeDtoList() {
		return trainingTypeDtoList;
	}

	public void setTrainingTypeDtoList(List<TrainingTypeDto> trainingTypeDtoList) {
		this.trainingTypeDtoList = trainingTypeDtoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((trainingTypeDtoList == null) ? 0 : trainingTypeDtoList
						.hashCode());
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
		if (!(obj instanceof TrainingTypeDtos)) {
			return false;
		}
		TrainingTypeDtos other = (TrainingTypeDtos) obj;
		if (trainingTypeDtoList == null) {
			if (other.trainingTypeDtoList != null) {
				return false;
			}
		} else if (!trainingTypeDtoList.equals(other.trainingTypeDtoList)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TrainingTypeDtos [trainingTypeDtoList=" + trainingTypeDtoList
				+ "]";
	}
	
}
