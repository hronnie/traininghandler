package com.codeproj.traininghandler.rest.common;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="idList")
public class GeneralIdListResponse {

	private List<Long> values;
	
	public GeneralIdListResponse(List<Long> values) {
		this.values = values;
	}
	
	public GeneralIdListResponse() {
		// empty constructor
	}

	@XmlElement(name="values")
	public List<Long> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "GeneralIdListResponse [values=" + values + "]";
	}
}
