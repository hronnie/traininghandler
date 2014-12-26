package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Address implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long addressId;
	private String postalCode;
	private String city;
	private String street;
	private String houseNo;
	private String country;
	private Boolean isTrainingPlace;
	private Boolean isAppointmentPlace;
	private Set<TrainingType> trainingTypes = new HashSet<>(0);
	private Set<User> users = new HashSet<>(0);
	private Set<Training> trainings = new HashSet<>(0);
	private Set<Appointment> appointments = new HashSet<>(0);

	public Address() {
		// empty constructor
	}
	
	public Address(Long addressId) {
		this.addressId = addressId;
	}

	public Address(Long addressId, String postalCode, String city,
			String street, String houseNo, String country,
			Boolean isTrainingPlace, Boolean isAppointmentPlace,
			Set<TrainingType> trainingTypes, Set<User> users,
			Set<Training> trainings, Set<Appointment> appointments) {
		this.addressId = addressId;
		this.postalCode = postalCode;
		this.city = city;
		this.street = street;
		this.houseNo = houseNo;
		this.country = country;
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

	public Set<TrainingType> getTrainingTypes() {
		return trainingTypes;
	}

	public void setTrainingTypes(Set<TrainingType> trainingTypes) {
		this.trainingTypes = trainingTypes;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}
}
