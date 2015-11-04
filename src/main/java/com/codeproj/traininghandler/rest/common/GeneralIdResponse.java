package com.codeproj.traininghandler.rest.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="id")
public class GeneralIdResponse extends BaseResponse {
	
	private Long value;
	
	public GeneralIdResponse() {
		// empty contructor
	}
	
	public GeneralIdResponse(Long id) {
		super(true, null);
		this.value = id;
	}
	
	public GeneralIdResponse(String message) {
		super(false, message);
	}

	@XmlElement(name="value")
	public Long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "GeneralIdResponse [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof GeneralIdResponse)) {
			return false;
		}
		GeneralIdResponse other = (GeneralIdResponse) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
