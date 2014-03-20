package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class SentTrainingEmail implements HibernatePersistable, Serializable {

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
}
