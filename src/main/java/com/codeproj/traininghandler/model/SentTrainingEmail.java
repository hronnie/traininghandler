package com.codeproj.traininghandler.model;

public class SentTrainingEmail implements java.io.Serializable {

	private long sentTrainingEmailId;
	private Training training;
	private User user;
	private EmailTemplate emailTemplate;
	private String traineeEmailToken;
	private boolean isConfirmed;
	private boolean isAttend;

	public SentTrainingEmail() {
	}

	public SentTrainingEmail(long sentTrainingEmailId, Training training,
			User user, EmailTemplate emailTemplate, String traineeEmailToken,
			boolean isConfirmed, boolean isAttend) {
		this.sentTrainingEmailId = sentTrainingEmailId;
		this.training = training;
		this.user = user;
		this.emailTemplate = emailTemplate;
		this.traineeEmailToken = traineeEmailToken;
		this.isConfirmed = isConfirmed;
		this.isAttend = isAttend;
	}

	public long getSentTrainingEmailId() {
		return this.sentTrainingEmailId;
	}

	public void setSentTrainingEmailId(long sentTrainingEmailId) {
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

	public boolean isIsConfirmed() {
		return this.isConfirmed;
	}

	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public boolean isIsAttend() {
		return this.isAttend;
	}

	public void setIsAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
}
