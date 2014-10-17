package com.codeproj.traininghandler.rest.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="id")
public class GeneralIdResponse {
	
	private Long value;
	
	public GeneralIdResponse() {
		// empty contructor
	}
	
	public GeneralIdResponse(Long id) {
		this.value = id;
	}

	@XmlElement(name="value")
	public Long getValue() {
		return value;
	}
}
