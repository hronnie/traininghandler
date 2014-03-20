package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class Role implements HibernatePersistable, Serializable {
	
	private Long roleId;
	private String roleName;

	public Role() {
		// empty constructor
	}

	public Role(Long roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	@Override
	public Long getId() {
		return roleId;
	}

	@Override
	public void setId(Long id) {
		this.roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
