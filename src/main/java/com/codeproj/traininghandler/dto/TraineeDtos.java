package com.codeproj.traininghandler.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name="Trainees")
@XmlSeeAlso({TraineeDto.class})
public class TraineeDtos {

	private List<TraineeDto> trainees;
	
	public TraineeDtos() {
		// empty constructor
	}

	public TraineeDtos(List<TraineeDto> trainees) {
		this.trainees = trainees;
	}

	@XmlElement(name="traineeList")
	public List<TraineeDto> getTrainees() {
		return trainees;
	}

	public void setTrainees(List<TraineeDto> trainees) {
		this.trainees = trainees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((trainees == null) ? 0 : trainees.hashCode());
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
		if (!(obj instanceof TraineeDtos)) {
			return false;
		}
		TraineeDtos other = (TraineeDtos) obj;
		if (trainees == null) {
			if (other.trainees != null) {
				return false;
			}
		} else if (!trainees.equals(other.trainees)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TraineeDtos [trainees=" + trainees + "]";
	} 
}
