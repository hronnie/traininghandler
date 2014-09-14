package com.codeproj.traininghandler.dto;

public class TrainingTypeDto {
	
	private String name;
	private String levelNo;
	private String description;
	
	public TrainingTypeDto() {
		//empty constructor
	}
	
	public TrainingTypeDto(String name, String levelNo, String description) {
		this.name = name;
		this.levelNo = levelNo;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLevelNo() {
		return levelNo;
	}
	
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
