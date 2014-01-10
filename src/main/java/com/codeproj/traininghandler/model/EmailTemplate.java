package com.codeproj.traininghandler.model;

import java.util.HashSet;
import java.util.Set;

public class EmailTemplate implements java.io.Serializable {

	private long emailTemplateId;
	private String subject;
	private String body;
	private Set<SentTrainingEmail> sentTrainingEmails = new HashSet<>(0);

	public EmailTemplate() {
	}

	public EmailTemplate(long emailTemplateId, String subject, String body) {
		this.emailTemplateId = emailTemplateId;
		this.subject = subject;
		this.body = body;
	}

	public EmailTemplate(long emailTemplateId, String subject, String body,
			Set<SentTrainingEmail> sentTrainingEmails) {
		this.emailTemplateId = emailTemplateId;
		this.subject = subject;
		this.body = body;
		this.sentTrainingEmails = sentTrainingEmails;
	}

	public long getEmailTemplateId() {
		return this.emailTemplateId;
	}

	public void setEmailTemplateId(long emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Set<SentTrainingEmail> getSentTrainingEmails() {
		return this.sentTrainingEmails;
	}

	public void setSentTrainingEmails(Set<SentTrainingEmail> sentTrainingEmails) {
		this.sentTrainingEmails = sentTrainingEmails;
	}
}
