package com.codeproj.traininghandler.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.codeproj.traininghandler.rest.common.BaseResponse;

@XmlRootElement(name="CompletedUserTraining")
public class CompletedUserTrainingDto extends BaseResponse {
	private Long userId;
	private Long trainingTypeId;
	private Date completedDate;
	private Boolean skipPrereqCheck;
	
	public CompletedUserTrainingDto() { /* empty constructor */}
	
	public CompletedUserTrainingDto(Long userId, Long trainingTypeId,
			Date completedDate) {
		this.userId = userId;
		this.trainingTypeId = trainingTypeId;
		this.completedDate = completedDate;
		this.skipPrereqCheck = false;
	}
	
	public CompletedUserTrainingDto(Long userId, Long trainingTypeId,
			Date completedDate, Boolean skipPrereqCheck) {
		this.userId = userId;
		this.trainingTypeId = trainingTypeId;
		this.completedDate = completedDate;
		this.skipPrereqCheck = skipPrereqCheck;
	}
	
	public CompletedUserTrainingDto(String message) {
		super(false, message);
	}

	@XmlElement(name="userId")
	public Long getUserId() {
		return userId;
	}

	@XmlElement(name="trainingTypeId")
	public Long getTrainingTypeId() {
		return trainingTypeId;
	}

	@XmlElement(name="completedDate")
	public Date getCompletedDate() {
		return completedDate;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setTrainingTypeId(Long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	public Boolean getSkipPrereqCheck() {
		return skipPrereqCheck;
	}

	public void setSkipPrereqCheck(Boolean skipPrereqCheck) {
		this.skipPrereqCheck = skipPrereqCheck;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((completedDate == null) ? 0 : completedDate.hashCode());
		result = prime * result
				+ (int) (trainingTypeId ^ (trainingTypeId >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		if (!(obj instanceof CompletedUserTrainingDto)) {
			return false;
		}
		CompletedUserTrainingDto other = (CompletedUserTrainingDto) obj;
		if (completedDate == null) {
			if (other.completedDate != null) {
				return false;
			}
		} else if (!completedDate.equals(other.completedDate)) {
			return false;
		}
		if (!trainingTypeId.equals(other.trainingTypeId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CompletedUserTrainingDto [userId=" + userId
				+ ", trainingTypeId=" + trainingTypeId + ", completedDate="
				+ completedDate + "]";
	}
}
