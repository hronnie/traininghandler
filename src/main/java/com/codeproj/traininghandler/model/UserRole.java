package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class UserRole implements HibernatePersistable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userRoleId;
	private User user;
	private Role role;
	private String authority;

	public UserRole() {
	}

	public UserRole(Long userRoleId, User user, String authority) {
		this.userRoleId = userRoleId;
		this.user = user;
		this.authority = authority;
	}

	@Override
	public Long getId() {
		return this.userRoleId;
	}
	
	@Override
	public void setId(Long id) {
		this.userRoleId = id;
	}
	
	public Long getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
