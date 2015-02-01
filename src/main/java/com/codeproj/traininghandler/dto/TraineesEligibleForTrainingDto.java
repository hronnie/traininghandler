package com.codeproj.traininghandler.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
public class TraineesEligibleForTrainingDto {
	private List<UserDto> hasEmailUsers;
	private List<UserDto> onlyPhoneUsers;
	
	public TraineesEligibleForTrainingDto() {
		// empty constructor
	}

	public TraineesEligibleForTrainingDto(List<UserDto> hasEmailUsers,
			List<UserDto> onlyPhoneUsers) {
		this.hasEmailUsers = hasEmailUsers;
		this.onlyPhoneUsers = onlyPhoneUsers;
	}

	@XmlElement(name="hasEmailUsers")
	public List<UserDto> getHasEmailUsers() {
		return hasEmailUsers;
	}

	@XmlElement(name="onlyPhoneUsers")
	public List<UserDto> getOnlyPhoneUsers() {
		return onlyPhoneUsers;
	}

	public void setHasEmailUsers(List<UserDto> hasEmailUsers) {
		this.hasEmailUsers = hasEmailUsers;
	}

	public void setOnlyPhoneUsers(List<UserDto> onlyPhoneUsers) {
		this.onlyPhoneUsers = onlyPhoneUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hasEmailUsers == null) ? 0 : hasEmailUsers.hashCode());
		result = prime * result
				+ ((onlyPhoneUsers == null) ? 0 : onlyPhoneUsers.hashCode());
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
		if (!(obj instanceof TraineesEligibleForTrainingDto)) {
			return false;
		}
		TraineesEligibleForTrainingDto other = (TraineesEligibleForTrainingDto) obj;
		if (hasEmailUsers == null) {
			if (other.hasEmailUsers != null) {
				return false;
			}
		} else if (!hasEmailUsers.equals(other.hasEmailUsers)) {
			return false;
		}
		if (onlyPhoneUsers == null) {
			if (other.onlyPhoneUsers != null) {
				return false;
			}
		} else if (!onlyPhoneUsers.equals(other.onlyPhoneUsers)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TraineesEligibleForTrainingDto [hasEmailUsers=" + hasEmailUsers
				+ ", onlyPhoneUsers=" + onlyPhoneUsers + "]";
	}
}
