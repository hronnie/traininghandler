package com.codeproj.traininghandler.rest.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="boolean")
public class BooleanResponse {

    protected Boolean booleanValue;

    public BooleanResponse() {
    }

    public BooleanResponse(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
    
    public BooleanResponse(String boolStr) {
        this.booleanValue = Boolean.parseBoolean(boolStr);
    }

    @XmlElement(name="value")
    public String getBooleanValue() {
        return booleanValue.toString();
    }
    
    public boolean getPrimitiveBooleanValue() {
    	return booleanValue;
    }
}
