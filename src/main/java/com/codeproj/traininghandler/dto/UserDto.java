package com.codeproj.traininghandler.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
public class UserDto {
	private String name;
	private String lastName;
	private String firstName;
	private String displayName;
	private Date dob;
	private String phoneNo;
	private String email;
	private Long addressId;
	
	public UserDto() { /* empty constructor */}

	public UserDto(String lastName, String firstName, String displayName,
			Date dob, String phoneNo, String email, Long addressId) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.displayName = displayName;
		this.dob = dob;
		this.phoneNo = phoneNo;
		this.email = email;
		this.addressId = addressId;
	}
	
	public UserDto(String name, String phoneNo, String email, Long addressId) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.addressId = addressId;
	}

	@XmlElement(name="lastName")
	public String getLastName() {
		return lastName;
	}

	@XmlElement(name="firstName")
	public String getFirstName() {
		return firstName;
	}

	@XmlElement(name="displayName")
	public String getDisplayName() {
		return displayName;
	}

	@XmlElement(name="dob")
	public Date getDob() {
		return dob;
	}

	@XmlElement(name="phoneNo")
	public String getPhoneNo() {
		return phoneNo;
	}

	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	
	@XmlElement(name="addressId")
	public Long getAddressId() {
		return addressId;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
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
		if (!(obj instanceof UserDto)) {
			return false;
		}
		UserDto other = (UserDto) obj;
		if (addressId == null) {
			if (other.addressId != null) {
				return false;
			}
		} else if (!addressId.equals(other.addressId)) {
			return false;
		}
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (dob == null) {
			if (other.dob != null) {
				return false;
			}
		} else if (!dob.equals(other.dob)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (phoneNo == null) {
			if (other.phoneNo != null) {
				return false;
			}
		} else if (!phoneNo.equals(other.phoneNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserDto [lastName=" + lastName + ", firstName=" + firstName
				+ ", displayName=" + displayName + ", dob=" + dob
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", addressId="
				+ addressId + "]";
	}
}


