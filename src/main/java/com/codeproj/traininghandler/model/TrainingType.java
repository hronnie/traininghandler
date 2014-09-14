package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TrainingType implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long trainingTypeId;
	private String name;
	private String levelNo;
	private String description;

	public TrainingType() {
	}

	public TrainingType(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public TrainingType(String name,
			String levelNo, String description) {
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
	}

	@Override
	public Long getId() {
		return this.trainingTypeId;
	}
	
	@Override
	public void setId(Long id) {
		this.trainingTypeId = id;
	}
	
	public Long getTrainingTypeId() {
		return this.trainingTypeId;
	}

	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevelNo() {
		return this.levelNo;
	}

	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
