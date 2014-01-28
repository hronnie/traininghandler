package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class GeneralSettings implements HibernatePersistable, Serializable {

	private long generalSettingsId;
	private String name;
	private String value;

	public GeneralSettings() {
	}

	public GeneralSettings(long generalSettingsId, String name, String value) {
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
	
	public long getGeneralSettingsId() {
		return this.generalSettingsId;
	}

	public void setGeneralSettingsId(long generalSettingsId) {
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
}
