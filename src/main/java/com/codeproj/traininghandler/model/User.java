package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements HibernatePersistable, Serializable {

	private long userId;
	private Address address;
	private String displayName;
	private String password;
	private boolean isMaster;
	private boolean isPatient;
	private boolean isHealer;
	private boolean isTrainee;
	private String firstName;
	private String lastName;
	private String userName;
	private Date dob;
	private String email;
	private String mobileNo;
	private boolean isEnabled;
	private Set<Authorities> authoritieses = new HashSet<>(0);
	private Set<UserLevel> userLevels = new HashSet<>(0);
	private Set<Appointment> appointmentsForPatientId = new HashSet<>(0);
	private Set<Appointment> appointmentsForHealerId = new HashSet<>(0);
	private Set<SentTrainingEmail> sentTrainingEmails = new HashSet<>(0);
	private Set<Log> logs = new HashSet<>(0);
	private Set<Training> trainings = new HashSet<>(0);

	public User() {
	}

	public User(long userId, Address address, boolean isMaster,
			boolean isPatient, boolean isHealer, boolean isTrainee,
			String firstName, String lastName, String email, boolean isEnabled) {
		this.userId = userId;
		this.address = address;
		this.isMaster = isMaster;
		this.isPatient = isPatient;
		this.isHealer = isHealer;
		this.isTrainee = isTrainee;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isEnabled = isEnabled;
	}

	public User(long userId, Address address, String displayName,
			String password, boolean isMaster, boolean isPatient,
			boolean isHealer, boolean isTrainee, String firstName,
			String lastName, String userName, Date dob, String email,
			String mobileNo, boolean isEnabled, Set<Authorities> authoritieses,
			Set<UserLevel> userLevels,
			Set<Appointment> appointmentsForPatientId,
			Set<Appointment> appointmentsForHealerId,
			Set<SentTrainingEmail> sentTrainingEmails, Set<Log> logs,
			Set<Training> trainings) {
		this.userId = userId;
		this.address = address;
		this.displayName = displayName;
		this.password = password;
		this.isMaster = isMaster;
		this.isPatient = isPatient;
		this.isHealer = isHealer;
		this.isTrainee = isTrainee;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.dob = dob;
		this.email = email;
		this.mobileNo = mobileNo;
		this.isEnabled = isEnabled;
		this.authoritieses = authoritieses;
		this.userLevels = userLevels;
		this.appointmentsForPatientId = appointmentsForPatientId;
		this.appointmentsForHealerId = appointmentsForHealerId;
		this.sentTrainingEmails = sentTrainingEmails;
		this.logs = logs;
		this.trainings = trainings;
	}

	@Override
	public Long getId() {
		return this.userId;
	}
	
	@Override
	public void setId(Long id) {
		this.userId = id;
	}
	
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsMaster() {
		return this.isMaster;
	}

	public void setIsMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public boolean isIsPatient() {
		return this.isPatient;
	}

	public void setIsPatient(boolean isPatient) {
		this.isPatient = isPatient;
	}

	public boolean isIsHealer() {
		return this.isHealer;
	}

	public void setIsHealer(boolean isHealer) {
		this.isHealer = isHealer;
	}

	public boolean isIsTrainee() {
		return this.isTrainee;
	}

	public void setIsTrainee(boolean isTrainee) {
		this.isTrainee = isTrainee;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public boolean isIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Set<Authorities> getAuthoritieses() {
		return this.authoritieses;
	}

	public void setAuthoritieses(Set<Authorities> authoritieses) {
		this.authoritieses = authoritieses;
	}

	public Set<UserLevel> getUserLevels() {
		return this.userLevels;
	}

	public void setUserLevels(Set<UserLevel> userLevels) {
		this.userLevels = userLevels;
	}

	public Set<Appointment> getAppointmentsForPatientId() {
		return this.appointmentsForPatientId;
	}

	public void setAppointmentsForPatientId(
			Set<Appointment> appointmentsForPatientId) {
		this.appointmentsForPatientId = appointmentsForPatientId;
	}

	public Set<Appointment> getAppointmentsForHealerId() {
		return this.appointmentsForHealerId;
	}

	public void setAppointmentsForHealerId(
			Set<Appointment> appointmentsForHealerId) {
		this.appointmentsForHealerId = appointmentsForHealerId;
	}

	public Set<SentTrainingEmail> getSentTrainingEmails() {
		return this.sentTrainingEmails;
	}

	public void setSentTrainingEmails(Set<SentTrainingEmail> sentTrainingEmails) {
		this.sentTrainingEmails = sentTrainingEmails;
	}

	public Set<Log> getLogs() {
		return this.logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}
}
