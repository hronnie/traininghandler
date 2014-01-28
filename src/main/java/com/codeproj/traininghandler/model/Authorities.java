package com.codeproj.traininghandler.model;

import java.io.Serializable;

public class Authorities implements HibernatePersistable, Serializable {

	private long userRoleId;
	private User user;
	private String authority;

	public Authorities() {
	}

	public Authorities(long userRoleId, User user, String authority) {
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
	
	public long getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
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

}
