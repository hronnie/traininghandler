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
}
