package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class GeneralSettings implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long generalSettingsId;
	private String name;
	private String value;

	public GeneralSettings() {
	}

	public GeneralSettings(Long generalSettingsId, String name, String value) {
		this.generalSettingsId = generalSettingsId;
		this.name = name;
		this.value = value;
	}

	@Override
	public Long getId() {
		return this.generalSettingsId;
	}
	
	@Override
	public void setId(Long id) {
		this.generalSettingsId = id;
	}
	
	public Long getGeneralSettingsId() {
		return this.generalSettingsId;
	}

	public void setGeneralSettingsId(Long generalSettingsId) {
		this.generalSettingsId = generalSettingsId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GeneralSettings [generalSettingsId=" + generalSettingsId
				+ ", name=" + name + ", value=" + value + "]";
	}
	
}
