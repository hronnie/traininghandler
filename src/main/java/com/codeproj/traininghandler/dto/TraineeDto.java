package com.codeproj.traininghandler.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Trainee")
public class TraineeDto {
	private String name;
	private String postCode;
	private String address;
	private String phone;
	private String email;
	private List<String> completedTrainings;
	
	public TraineeDto() {
		// empty constructor
	}

	public TraineeDto(String name, String postCode, String address,
			String phone, String email) {
		this.name = name;
		this.postCode = postCode;
		this.address = address;
		this.phone = phone;
		this.email = email;
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

	@XmlElement(name="completedTraings")
	public List<String> getCompletedTrainings() {
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
	
	public void setCompletedTrainings(List<String> completedTrainings) {
		this.completedTrainings = completedTrainings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime
				* result
				+ ((completedTrainings == null) ? 0 : completedTrainings
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result
				+ ((postCode == null) ? 0 : postCode.hashCode());
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
		if (!(obj instanceof TraineeDto)) {
			return false;
		}
		TraineeDto other = (TraineeDto) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (completedTrainings == null) {
			if (other.completedTrainings != null) {
				return false;
			}
		} else if (!completedTrainings.equals(other.completedTrainings)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (postCode == null) {
			if (other.postCode != null) {
				return false;
			}
		} else if (!postCode.equals(other.postCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TraineeDto [name=" + name + ", postCode=" + postCode
				+ ", address=" + address + ", phone=" + phone + ", email="
				+ email + ", completedTrainings=" + completedTrainings + "]";
	}
}
