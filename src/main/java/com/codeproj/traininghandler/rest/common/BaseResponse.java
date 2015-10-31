package com.codeproj.traininghandler.rest.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class BaseResponse {
	private Boolean success;
	private String message;
	
	public BaseResponse() {} // empty constructor

	public BaseResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public BaseResponse(String message) {
		this.success = false;
		this.message = message;
	}
	
	public BaseResponse(Boolean success) {
		this.success = success;
		this.message = null;
	}

	@XmlElement(name="success")
	public Boolean getSuccess() {
		return success;
	}

	@XmlElement(name="message")
	public String getMessage() {
		return message;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
