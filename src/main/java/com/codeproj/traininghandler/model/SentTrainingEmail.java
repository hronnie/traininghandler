package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class SentTrainingEmail implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long sentTrainingEmailId;
	private Training training;
	private User user;
	private EmailTemplate emailTemplate;
	private String traineeEmailToken;
	private Boolean isConfirmed;
	private Boolean isAttend;

	public SentTrainingEmail() {
	}

	public SentTrainingEmail(Long sentTrainingEmailId, Training training,
			User user, EmailTemplate emailTemplate, String traineeEmailToken,
			Boolean isConfirmed, Boolean isAttend) {
		this.sentTrainingEmailId = sentTrainingEmailId;
		this.training = training;
		this.user = user;
		this.emailTemplate = emailTemplate;
		this.traineeEmailToken = traineeEmailToken;
		this.isConfirmed = isConfirmed;
		this.isAttend = isAttend;
	}

	@Override
	public Long getId() {
		return this.sentTrainingEmailId;
	}
	
	@Override
	public void setId(Long id) {
		this.sentTrainingEmailId = id;
	}
	
	public Long getSentTrainingEmailId() {
		return this.sentTrainingEmailId;
	}

	public void setSentTrainingEmailId(Long sentTrainingEmailId) {
		this.sentTrainingEmailId = sentTrainingEmailId;
	}

	public Training getTraining() {
		return this.training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EmailTemplate getEmailTemplate() {
		return this.emailTemplate;
	}

	public void setEmailTemplate(EmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public String getTraineeEmailToken() {
		return this.traineeEmailToken;
	}

	public void setTraineeEmailToken(String traineeEmailToken) {
		this.traineeEmailToken = traineeEmailToken;
	}

	public Boolean isIsConfirmed() {
		return this.isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Boolean isIsAttend() {
		return this.isAttend;
	}

	public void setIsAttend(Boolean isAttend) {
		this.isAttend = isAttend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailTemplate == null) ? 0 : emailTemplate.hashCode());
		result = prime * result
				+ ((isAttend == null) ? 0 : isAttend.hashCode());
		result = prime * result
				+ ((isConfirmed == null) ? 0 : isConfirmed.hashCode());
		result = prime
				* result
				+ ((sentTrainingEmailId == null) ? 0 : sentTrainingEmailId
						.hashCode());
		result = prime
				* result
				+ ((traineeEmailToken == null) ? 0 : traineeEmailToken
						.hashCode());
		result = prime * result
				+ ((training == null) ? 0 : training.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (!(obj instanceof SentTrainingEmail)) {
			return false;
		}
		SentTrainingEmail other = (SentTrainingEmail) obj;
		if (emailTemplate == null) {
			if (other.emailTemplate != null) {
				return false;
			}
		} else if (!emailTemplate.equals(other.emailTemplate)) {
			return false;
		}
		if (isAttend == null) {
			if (other.isAttend != null) {
				return false;
			}
		} else if (!isAttend.equals(other.isAttend)) {
			return false;
		}
		if (isConfirmed == null) {
			if (other.isConfirmed != null) {
				return false;
			}
		} else if (!isConfirmed.equals(other.isConfirmed)) {
			return false;
		}
		if (sentTrainingEmailId == null) {
			if (other.sentTrainingEmailId != null) {
				return false;
			}
		} else if (!sentTrainingEmailId.equals(other.sentTrainingEmailId)) {
			return false;
		}
		if (traineeEmailToken == null) {
			if (other.traineeEmailToken != null) {
				return false;
			}
		} else if (!traineeEmailToken.equals(other.traineeEmailToken)) {
			return false;
		}
		if (training == null) {
			if (other.training != null) {
				return false;
			}
		} else if (!training.equals(other.training)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SentTrainingEmail [sentTrainingEmailId=" + sentTrainingEmailId
				+ ", training=" + training + ", user=" + user
				+ ", emailTemplate=" + emailTemplate + ", traineeEmailToken="
				+ traineeEmailToken + ", isConfirmed=" + isConfirmed
				+ ", isAttend=" + isAttend + "]";
	}
}
