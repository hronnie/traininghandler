package com.codeproj.traininghandler.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Address")
public class AddressDto {
	private String postCode;
	private String city;
	private String street;
	private String houseNo;
	private String country;
	private Boolean isTrainingPlace;
	private Boolean isAppointmentPlace;
	
	public AddressDto() { /* empty constructor */ }
	
	@XmlElement(name="postCode")
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@XmlElement(name="city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement(name="street")
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	@XmlElement(name="houseNo")
	public String getHouseNo() {
		return houseNo;
	}
	
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	@XmlElement(name="country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlElement(name="isTrainingPlace")
	public Boolean getIsTrainingPlace() {
		return isTrainingPlace;
	}
	
	public void setIsTrainingPlace(Boolean isTrainingPlace) {
		this.isTrainingPlace = isTrainingPlace;
	}
	
	@XmlElement(name="isAppointmentPlace")
	public Boolean getIsAppointmentPlace() {
		return isAppointmentPlace;
	}
	
	public void setIsAppointmentPlace(Boolean isAppointmentPlace) {
		this.isAppointmentPlace = isAppointmentPlace;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((houseNo == null) ? 0 : houseNo.hashCode());
		result = prime
				* result
				+ ((isAppointmentPlace == null) ? 0 : isAppointmentPlace
						.hashCode());
		result = prime * result
				+ ((isTrainingPlace == null) ? 0 : isTrainingPlace.hashCode());
		result = prime * result
				+ ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		if (!(obj instanceof AddressDto)) {
			return false;
		}
		AddressDto other = (AddressDto) obj;
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (houseNo == null) {
			if (other.houseNo != null) {
				return false;
			}
		} else if (!houseNo.equals(other.houseNo)) {
			return false;
		}
		if (isAppointmentPlace == null) {
			if (other.isAppointmentPlace != null) {
				return false;
			}
		} else if (!isAppointmentPlace.equals(other.isAppointmentPlace)) {
			return false;
		}
		if (isTrainingPlace == null) {
			if (other.isTrainingPlace != null) {
				return false;
			}
		} else if (!isTrainingPlace.equals(other.isTrainingPlace)) {
			return false;
		}
		if (postCode == null) {
			if (other.postCode != null) {
				return false;
			}
		} else if (!postCode.equals(other.postCode)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AddressDto [postCode=" + postCode + ", city=" + city
				+ ", street=" + street + ", houseNo=" + houseNo + ", country="
				+ country + ", isTrainingPlace=" + isTrainingPlace
				+ ", isAppointmentPlace=" + isAppointmentPlace + "]";
	}
}
