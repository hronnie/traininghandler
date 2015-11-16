package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class Role implements HibernatePersistable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
}
