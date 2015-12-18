package com.codeproj.traininghandler.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.codeproj.traininghandler.rest.common.BaseResponse;

@XmlRootElement(name="CompletedUserTraining")
@XmlSeeAlso(CompletedUserTrainingDto.class)
public class CompletedUserTrainingDtos extends BaseResponse {
	
	private List<CompletedUserTrainingDto> completedUserTrainingDtoList;
	
	public CompletedUserTrainingDtos() {} // empty constructor

	public CompletedUserTrainingDtos(List<CompletedUserTrainingDto> completedUserTrainingDtoList) {
		this.completedUserTrainingDtoList = completedUserTrainingDtoList;
		this.setSuccess(true);
	}
	
	public CompletedUserTrainingDtos(String message) {
		super(message);
	}

	@XmlElement(name="completedUserTrainingDtoList")
	public List<CompletedUserTrainingDto> getCompletedUserTrainingDtoList() {
		return completedUserTrainingDtoList;
	}

	public void setCompletedUserTrainingDtoList(List<CompletedUserTrainingDto> completedUserTrainingDtoList) {
		this.completedUserTrainingDtoList = completedUserTrainingDtoList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((completedUserTrainingDtoList == null) ? 0 : completedUserTrainingDtoList.hashCode());
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
		CompletedUserTrainingDtos other = (CompletedUserTrainingDtos) obj;
		if (completedUserTrainingDtoList == null) {
			if (other.completedUserTrainingDtoList != null)
				return false;
		} else if (!completedUserTrainingDtoList.equals(other.completedUserTrainingDtoList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompletedUserTrainingDtos [completedUserTrainingDtoList=" + completedUserTrainingDtoList + "]";
	} 
}
