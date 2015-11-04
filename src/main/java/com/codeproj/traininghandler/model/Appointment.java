package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;


public class Appointment implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long appointmentId;
	private User userByHealerId;
	private User userByPatientId;
	private Address address;
	private Date fromDateTime;
	private Date toDateTime;
	private String description;
	private Boolean isEmailConfirmation;

	public Appointment() {
	}

	public Appointment(Long appointmentId, User userByHealerId,
			User userByPatientId, Address address, Date fromDateTime,
			Date toDateTime, Boolean isEmailConfirmation) {
		this.appointmentId = appointmentId;
		this.userByHealerId = userByHealerId;
		this.userByPatientId = userByPatientId;
		this.address = address;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.isEmailConfirmation = isEmailConfirmation;
	}

	public Appointment(Long appointmentId, User userByHealerId,
			User userByPatientId, Address address, Date fromDateTime,
			Date toDateTime, String description, Boolean isEmailConfirmation) {
		this.appointmentId = appointmentId;
		this.userByHealerId = userByHealerId;
		this.userByPatientId = userByPatientId;
		this.address = address;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.description = description;
		this.isEmailConfirmation = isEmailConfirmation;
	}

	@Override
	public Long getId() {
		return this.appointmentId;
	}
	
	@Override
	public void setId(Long id) {
		this.appointmentId = id;
	}
	
	public Long getAppointmentId() {
		return this.appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public User getUserByHealerId() {
		return this.userByHealerId;
	}

	public void setUserByHealerId(User userByHealerId) {
		this.userByHealerId = userByHealerId;
	}

	public User getUserByPatientId() {
		return this.userByPatientId;
	}

	public void setUserByPatientId(User userByPatientId) {
		this.userByPatientId = userByPatientId;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getFromDateTime() {
		return this.fromDateTime;
	}

	public void setFromDateTime(Date fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public Date getToDateTime() {
		return this.toDateTime;
	}

	public void setToDateTime(Date toDateTime) {
		this.toDateTime = toDateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isIsEmailConfirmation() {
		return this.isEmailConfirmation;
	}

	public void setIsEmailConfirmation(Boolean isEmailConfirmation) {
		this.isEmailConfirmation = isEmailConfirmation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((appointmentId == null) ? 0 : appointmentId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((fromDateTime == null) ? 0 : fromDateTime.hashCode());
		result = prime
				* result
				+ ((isEmailConfirmation == null) ? 0 : isEmailConfirmation
						.hashCode());
		result = prime * result
				+ ((toDateTime == null) ? 0 : toDateTime.hashCode());
		result = prime * result
				+ ((userByHealerId == null) ? 0 : userByHealerId.hashCode());
		result = prime * result
				+ ((userByPatientId == null) ? 0 : userByPatientId.hashCode());
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
		if (!(obj instanceof Appointment)) {
			return false;
		}
		Appointment other = (Appointment) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (appointmentId == null) {
			if (other.appointmentId != null) {
				return false;
			}
		} else if (!appointmentId.equals(other.appointmentId)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (fromDateTime == null) {
			if (other.fromDateTime != null) {
				return false;
			}
		} else if (!fromDateTime.equals(other.fromDateTime)) {
			return false;
		}
		if (isEmailConfirmation == null) {
			if (other.isEmailConfirmation != null) {
				return false;
			}
		} else if (!isEmailConfirmation.equals(other.isEmailConfirmation)) {
			return false;
		}
		if (toDateTime == null) {
			if (other.toDateTime != null) {
				return false;
			}
		} else if (!toDateTime.equals(other.toDateTime)) {
			return false;
		}
		if (userByHealerId == null) {
			if (other.userByHealerId != null) {
				return false;
			}
		} else if (!userByHealerId.equals(other.userByHealerId)) {
			return false;
		}
		if (userByPatientId == null) {
			if (other.userByPatientId != null) {
				return false;
			}
		} else if (!userByPatientId.equals(other.userByPatientId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId
				+ ", userByHealerId=" + userByHealerId + ", userByPatientId="
				+ userByPatientId + ", address=" + address + ", fromDateTime="
				+ fromDateTime + ", toDateTime=" + toDateTime
				+ ", description=" + description + ", isEmailConfirmation="
				+ isEmailConfirmation + "]";
	}
}
