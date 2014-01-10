package com.codeproj.traininghandler.model;

import java.util.Date;

public class Log implements java.io.Serializable {

	private long logId;
	private User user;
	private String action;
	private String message;
	private Date dateTime;

	public Log() {
	}

	public Log(long logId, User user, String action, Date dateTime) {
		this.logId = logId;
		this.user = user;
		this.action = action;
		this.dateTime = dateTime;
	}

	public Log(long logId, User user, String action, String message,
			Date dateTime) {
		this.logId = logId;
		this.user = user;
		this.action = action;
		this.message = message;
		this.dateTime = dateTime;
	}

	public long getLogId() {
		return this.logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
