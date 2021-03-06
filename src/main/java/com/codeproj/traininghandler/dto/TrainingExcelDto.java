package com.codeproj.traininghandler.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.codeproj.traininghandler.rest.common.BaseResponse;

@XmlRootElement(name="trainingExcelDto")
public class TrainingExcelDto extends BaseResponse {
	private String name;
	private String postCode;
	private String address;
	private String phoneNo;
	private String email;
	
	public TrainingExcelDto() {
		// empty constructor
	}
	
	public TrainingExcelDto(String name, String postCode, String address,
			String phoneNo, String email) {
		this.name = name;
		this.postCode = postCode;
		this.address = address;
		this.phoneNo = phoneNo;
		this.email = email;
	}
	
	public TrainingExcelDto(String message) {
		super(false, message);
	}

	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="postCode")
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@XmlElement(name="address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@XmlElement(name="phone")
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
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
		if (!(obj instanceof TrainingExcelDto)) {
			return false;
		}
		TrainingExcelDto other = (TrainingExcelDto) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
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
		if (phoneNo == null) {
			if (other.phoneNo != null) {
				return false;
			}
		} else if (!phoneNo.equals(other.phoneNo)) {
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
		return "TrainingExcelDto [name=" + name + ", postCode=" + postCode
				+ ", address=" + address + ", phoneNo=" + phoneNo + ", email="
				+ email + "]";
	}
	
	public String toStringUserFriendly() {
		StringBuffer sb = new StringBuffer();
		if (!StringUtils.isEmpty(name)) {
			sb.append("Név: ");
			sb.append(name);
			sb.append(", ");
		}
		if (!StringUtils.isEmpty(postCode)) {
			sb.append("Irányítószám");
			sb.append(postCode);
			sb.append(", ");
		}
		if (!StringUtils.isEmpty(address)) {
			sb.append("Cím:");
			sb.append(address);
			sb.append(", ");
		}
		if (!StringUtils.isEmpty(phoneNo)) {
			sb.append("Telefonszám: ");
			sb.append(phoneNo);
			sb.append(", ");
		}
		if (!StringUtils.isEmpty(email)) {
			sb.append("Email:");
			sb.append(email);
			sb.append(", ");
		}
		
		return sb.toString().substring(0, sb.length() - 2);
	}
}
