package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TrainingRef implements HibernatePersistable, Serializable {

	private Long trainingRefId;
	private Address address;
	private String name;
	private Byte levelNo;
	private String description;
	private Set<UserLevel> userLevels = new HashSet<>(0);
	private Set<Training> trainings = new HashSet<>(0);
	private Set<TrainingPrerequisite> trainingPrerequisites = new HashSet<>(0);

	public TrainingRef() {
	}

	public TrainingRef(Long trainingRefId, Address address) {
		this.trainingRefId = trainingRefId;
		this.address = address;
	}

	public TrainingRef(Long trainingRefId, Address address, String name,
			Byte levelNo, String description, Set<UserLevel> userLevels,
			Set<Training> trainings,
			Set<TrainingPrerequisite> trainingPrerequisites) {
		this.trainingRefId = trainingRefId;
		this.address = address;
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
		this.userLevels = userLevels;
		this.trainings = trainings;
		this.trainingPrerequisites = trainingPrerequisites;
	}

	@Override
	public Long getId() {
		return this.trainingRefId;
	}
	
	@Override
	public void setId(Long id) {
		this.trainingRefId = id;
	}
	
	public Long getTrainingRefId() {
		return this.trainingRefId;
	}

	public void setTrainingRefId(Long trainingRefId) {
		this.trainingRefId = trainingRefId;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getLevelNo() {
		return this.levelNo;
	}

	public void setLevelNo(Byte levelNo) {
		this.levelNo = levelNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserLevel> getUserLevels() {
		return this.userLevels;
	}

	public void setUserLevels(Set<UserLevel> userLevels) {
		this.userLevels = userLevels;
	}

	public Set<Training> getTrainings() {
		return this.trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}

	public Set<TrainingPrerequisite> getTrainingPrerequisites() {
		return this.trainingPrerequisites;
	}

	public void setTrainingPrerequisites(
			Set<TrainingPrerequisite> trainingPrerequisites) {
		this.trainingPrerequisites = trainingPrerequisites;
	}
}
