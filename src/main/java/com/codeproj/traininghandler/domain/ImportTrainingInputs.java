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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result
				+ ((trainingTypeId == null) ? 0 : trainingTypeId.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		if (!(obj instanceof ImportTrainingInputs)) {
			return false;
		}
		ImportTrainingInputs other = (ImportTrainingInputs) obj;
		if (day == null) {
			if (other.day != null) {
				return false;
			}
		} else if (!day.equals(other.day)) {
			return false;
		}
		if (month == null) {
			if (other.month != null) {
				return false;
			}
		} else if (!month.equals(other.month)) {
			return false;
		}
		if (trainingTypeId == null) {
			if (other.trainingTypeId != null) {
				return false;
			}
		} else if (!trainingTypeId.equals(other.trainingTypeId)) {
			return false;
		}
		if (year == null) {
			if (other.year != null) {
				return false;
			}
		} else if (!year.equals(other.year)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ImportTrainingInputs [trainingTypeId=" + trainingTypeId
				+ ", year=" + year + ", month=" + month + ", day=" + day + "]";
	}


}
