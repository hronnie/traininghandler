package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	private String postalCode;
	private String city;
	private String street;
	private String houseNo;
	private String country;
	private String oneLineAddress;
	private Boolean isTrainingPlace = new Boolean(false);
	private Boolean isAppointmentPlace = new Boolean(false);
	private Set<User> users = new HashSet<>(0);

	public Address() {
		// empty constructor
	}
	
	public Address(Long addressId) {
		this.addressId = addressId;
	}

	public Address(Long addressId, String postalCode, String city,
			String street, String houseNo, String country, String oneLineAddress,
			Boolean isTrainingPlace, Boolean isAppointmentPlace, Set<User> users) {
		this.addressId = addressId;
		this.postalCode = postalCode;
		this.city = city;
		this.street = street;
		this.houseNo = houseNo;
		this.country = country;
		this.oneLineAddress = oneLineAddress;
		this.isTrainingPlace = isTrainingPlace;
		this.isAppointmentPlace = isAppointmentPlace;
		this.users = users;
	}
	
	public Address(String city, String country, String houseNo, String postalCode,
			String street, String address) {
		this.city = city;
		this.country = country;
		this.houseNo = houseNo;
		this.postalCode = postalCode;
		this.street = street;
		this.oneLineAddress = address;
		this.isAppointmentPlace = new Boolean(false);
		this.isTrainingPlace = new Boolean(false);
	}
	
	public Address(String postalCode, String oneLineAddress) {
		this.oneLineAddress = oneLineAddress;
		this.postalCode = postalCode;
		this.isAppointmentPlace = new Boolean(false);
		this.isTrainingPlace = new Boolean(false);
	}

	public Long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOneLineAddress() {
		return oneLineAddress;
	}

	public void setOneLineAddress(String oneLineAddress) {
		this.oneLineAddress = oneLineAddress;
	}

	public Boolean getIsTrainingPlace() {
		return isTrainingPlace;
	}

	public void setIsTrainingPlace(Boolean isTrainingPlace) {
		this.isTrainingPlace = isTrainingPlace;
	}

	public Boolean getIsAppointmentPlace() {
		return isAppointmentPlace;
	}

	public void setIsAppointmentPlace(Boolean isAppointmentPlace) {
		this.isAppointmentPlace = isAppointmentPlace;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressId == null) ? 0 : addressId.hashCode());
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
				+ ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result
				+ ((oneLineAddress == null) ? 0 : oneLineAddress.hashCode());
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
		if (!(obj instanceof Address)) {
			return false;
		}
		Address other = (Address) obj;
		if (addressId == null) {
			if (other.addressId != null) {
				return false;
			}
		} else if (!addressId.equals(other.addressId)) {
			return false;
		}
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
		if (oneLineAddress == null) {
			if (other.oneLineAddress != null) {
				return false;
			}
		} else if (!oneLineAddress.equals(other.oneLineAddress)) {
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
		if (postalCode == null) {
			if (other.postalCode != null) {
				return false;
			}
		} else if (!postalCode.equals(other.postalCode)) {
			return false;
		}
		if (street == null) {
			if (other.street != null) {
				return false;
			}
		} else if (!street.equals(other.street)) {
			return false;
		}
		if (users == null) {
			if (other.users != null) {
				return false;
			}
		} else if (!users.equals(other.users)) {
			return false;
		}
		return true;
	}
}
