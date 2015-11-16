package com.codeproj.traininghandler.rest.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="boolean")
public class BooleanResponse extends BaseResponse {

    protected Boolean booleanValue;

    public BooleanResponse() {
    }

    public BooleanResponse(Boolean booleanValue) {
    	super(true);
        this.booleanValue = booleanValue;
    }
    
    public BooleanResponse(String boolStr) {
        this.booleanValue = Boolean.parseBoolean(boolStr);
    }

    @XmlElement(name="value")
    public Boolean getBooleanValue() {
        return booleanValue;
    }

	@Override
	public String toString() {
		return "BooleanResponse [booleanValue=" + booleanValue + "]";
	}
}
