package com.codeproj.traininghandler.domain;

public class ImportTrainingInputs {
	private String trainingTypeId;
	private String year;
	private String month;
	private String day;
	
	public ImportTrainingInputs() {} // empty constructor
	
	public ImportTrainingInputs(String trainingTypeId, String year, String month, String day) {
		this.trainingTypeId = trainingTypeId;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String getTrainingTypeId() {
		return trainingTypeId;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public void setTrainingTypeId(String trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}


}
