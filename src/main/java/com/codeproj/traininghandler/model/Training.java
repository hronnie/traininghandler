package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Training implements HibernatePersistable, Serializable {

	private Long trainingId;
	private TrainingType trainingType;
	private Address address;
	private String nameOfMaster;
	private Date dateTime;
	private String description;
	private Boolean autoInvite;
	private Set<TrainingPrerequisite> trainingPrerequisites = new HashSet<>(0);
	private Set<SentTrainingEmail> sentTrainingEmails = new HashSet<>(0);
	private Set<User> users = new HashSet<>(0); 

	public Training() {
	}

	public Training(Long trainingId, TrainingType trainingType, Address address,
			String nameOfMaster, Date dateTime, Boolean autoInvite) {
		this.trainingId = trainingId;
		this.trainingType = trainingType;
		this.address = address;
		this.nameOfMaster = nameOfMaster;
		this.dateTime = dateTime;
		this.autoInvite = autoInvite;
	}

	public Training(Long trainingId, TrainingType trainingType, Address address,
			String nameOfMaster, Date dateTime, String description,
			Boolean autoInvite,
			Set<TrainingPrerequisite> trainingPrerequisites,
			Set<SentTrainingEmail> sentTrainingEmails) {
		this.trainingId = trainingId;
		this.trainingType = trainingType;
		this.address = address;
		this.nameOfMaster = nameOfMaster;
		this.dateTime = dateTime;
		this.description = description;
		this.autoInvite = autoInvite;
		this.trainingPrerequisites = trainingPrerequisites;
		this.sentTrainingEmails = sentTrainingEmails;
	}

	@Override
	public Long getId() {
		return this.trainingId;
	}
	
	@Override
	public void setId(Long id) {
		this.trainingId = id;
	}
	
	public Long getTrainingId() {
		return this.trainingId;
	}

	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}

	public TrainingType getTrainingType() {
		return this.trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNameOfMaster() {
		return this.nameOfMaster;
	}

	public void setNameOfMaster(String nameOfMaster) {
		this.nameOfMaster = nameOfMaster;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isAutoInvite() {
		return this.autoInvite;
	}

	public void setAutoInvite(Boolean autoInvite) {
		this.autoInvite = autoInvite;
	}

	public Set<TrainingPrerequisite> getTrainingPrerequisites() {
		return this.trainingPrerequisites;
	}

	public void setTrainingPrerequisites(
			Set<TrainingPrerequisite> trainingPrerequisites) {
		this.trainingPrerequisites = trainingPrerequisites;
	}

	public Set<SentTrainingEmail> getSentTrainingEmails() {
		return this.sentTrainingEmails;
	}

	public void setSentTrainingEmails(Set<SentTrainingEmail> sentTrainingEmails) {
		this.sentTrainingEmails = sentTrainingEmails;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
