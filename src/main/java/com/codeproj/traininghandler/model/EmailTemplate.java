package com.codeproj.traininghandler.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EmailTemplate implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long emailTemplateId;
	private String subject;
	private String body;
	private Set<SentTrainingEmail> sentTrainingEmails = new HashSet<>(0);

	public EmailTemplate() {
	}

	public EmailTemplate(Long emailTemplateId, String subject, String body) {
		this.emailTemplateId = emailTemplateId;
		this.subject = subject;
		this.body = body;
	}

	public EmailTemplate(Long emailTemplateId, String subject, String body,
			Set<SentTrainingEmail> sentTrainingEmails) {
		this.emailTemplateId = emailTemplateId;
		this.subject = subject;
		this.body = body;
		this.sentTrainingEmails = sentTrainingEmails;
	}

	@Override
	public Long getId() {
		return this.emailTemplateId;
	}
	
	@Override
	public void setId(Long id) {
		this.emailTemplateId = id;
	}
	
	public Long getEmailTemplateId() {
		return this.emailTemplateId;
	}

	public void setEmailTemplateId(Long emailTemplateId) {
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
