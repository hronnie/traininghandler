package com.codeproj.traininghandler.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Trainee")
public class TraineeDto {
	private Long userId;
	private Long addressId;
	private String name;
	private String postCode;
	private String address;
	private String phone;
	private String email;
	private String completedTrainings;
	
	public TraineeDto() {
		// empty constructor
	}

	public TraineeDto(String name, String postCode, String address,
			String phone, String email, Long userId, Long addressId,
			String completedTrainings) {
		this.name = name;
		this.postCode = postCode;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.userId = userId;
		this.addressId = addressId;
		this.completedTrainings = completedTrainings;
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	@XmlElement(name="postCode")
	public String getPostCode() {
		return postCode;
	}

	@XmlElement(name="address")
	public String getAddress() {
		return address;
	}

	@XmlElement(name="phone")
	public String getPhone() {
		return phone;
	}

	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}

	@XmlElement(name="userId")
	public Long getUserId() {
		return userId;
	}

	@XmlElement(name="addressId")
	public Long getAddressId() {
		return addressId;
	}

	@XmlElement(name="completedTrainings")
	public String getCompletedTrainings() {
		return completedTrainings;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	public void setCompletedTrainings(String completedTrainings) {
		this.completedTrainings = completedTrainings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((addressId == null) ? 0 : addressId.hashCode());
		result = prime
				* result
				+ ((completedTrainings == null) ? 0 : completedTrainings
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		TraineeDto other = (TraineeDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (completedTrainings == null) {
			if (other.completedTrainings != null)
				return false;
		} else if (!completedTrainings.equals(other.completedTrainings))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TraineeDto [name=" + name + ", postCode=" + postCode
				+ ", address=" + address + ", phone=" + phone + ", email="
				+ email + ", userId=" + userId + ", addressId=" + addressId
				+ ", completedTrainings=" + completedTrainings + "]";
	}
}
