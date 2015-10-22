package com.codeproj.traininghandler.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.codeproj.traininghandler.rest.common.BaseResponse;

@XmlRootElement(name="TraineePersonalAndTrainingInfo")
@XmlSeeAlso({AddressDto.class, UserDto.class, CompletedUserTrainingDto.class})
public class TraineePersonalAndTrainingInfoDto extends BaseResponse {
	private AddressDto address;
	private UserDto user;
	private List<CompletedUserTrainingDto> completedUserTrainingList;
	
	public TraineePersonalAndTrainingInfoDto() {/* empty constructor */ }

	public TraineePersonalAndTrainingInfoDto(AddressDto address, UserDto user,
			List<CompletedUserTrainingDto> completedUserTrainingList) {
		this.address = address;
		this.user = user;
		this.completedUserTrainingList = completedUserTrainingList;
	}
	
	public TraineePersonalAndTrainingInfoDto(String message) {
		super(false, message);
	}

	@XmlElement(name="address")
	public AddressDto getAddress() {
		return address;
	}

	@XmlElement(name="user")
	public UserDto getUser() {
		return user;
	}

	@XmlElement(name="completedUserTrainingList")
	public List<CompletedUserTrainingDto> getCompletedUserTrainingList() {
		return completedUserTrainingList;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public void setCompletedUserTrainingList(
			List<CompletedUserTrainingDto> completedUserTrainingList) {
		this.completedUserTrainingList = completedUserTrainingList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime
				* result
				+ ((completedUserTrainingList == null) ? 0
						: completedUserTrainingList.hashCode());
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
		if (!(obj instanceof TraineePersonalAndTrainingInfoDto)) {
			return false;
		}
		TraineePersonalAndTrainingInfoDto other = (TraineePersonalAndTrainingInfoDto) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (completedUserTrainingList == null) {
			if (other.completedUserTrainingList != null) {
				return false;
			}
		} else if (!completedUserTrainingList
				.equals(other.completedUserTrainingList)) {
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

	@Override
	public String toString() {
		return "TraineePersonalAndTrainingInfoDto [address=" + address
				+ ", user=" + user + ", completedUserTrainingList="
				+ completedUserTrainingList + "]";
	}
	
}
