package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Address implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private Boolean isTrainingPlace;
	private Boolean isAppointmentPlace;
	private Set<TrainingType> trainingTypes = new HashSet<>(0);
	private Set<User> users = new HashSet<>(0);
	private Set<Training> trainings = new HashSet<>(0);
	private Set<Appointment> appointments = new HashSet<>(0);

	public Address() {
	}

	public Address(Long addressId, boolean isTrainingPlace,
			boolean isAppointmentPlace) {
		this.addressId = addressId;
		this.isTrainingPlace = isTrainingPlace;
		this.isAppointmentPlace = isAppointmentPlace;
	}

	public Address(long addressId, String addressLine1, String addressLine2,
			String city, String state, String country, String postalCode,
			Boolean isTrainingPlace, Boolean isAppointmentPlace,
			Set<TrainingType> trainingTypes, Set<User> users,
			Set<Training> trainings, Set<Appointment> appointments) {
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.isTrainingPlace = isTrainingPlace;
		this.isAppointmentPlace = isAppointmentPlace;
		this.trainingTypes = trainingTypes;
		this.users = users;
		this.trainings = trainings;
		this.appointments = appointments;
	}

	@Override
	public Long getId() {
		return this.addressId;
	}
	
	@Override
	public void setId(Long id) {
		this.addressId = id;
	}

	public long getAddressId() {
		return this.addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Boolean isIsTrainingPlace() {
		return this.isTrainingPlace;
	}

	public void setIsTrainingPlace(Boolean isTrainingPlace) {
		this.isTrainingPlace = isTrainingPlace;
	}

	public Boolean isIsAppointmentPlace() {
		return this.isAppointmentPlace;
	}

	public void setIsAppointmentPlace(Boolean isAppointmentPlace) {
		this.isAppointmentPlace = isAppointmentPlace;
	}

	public Set<TrainingType> getTrainingTypes() {
		return this.trainingTypes;
	}

	public void setTrainingTypes(Set<TrainingType> trainingTypes) {
		this.trainingTypes = trainingTypes;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Training> getTrainings() {
		return this.trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	public Set<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

}
