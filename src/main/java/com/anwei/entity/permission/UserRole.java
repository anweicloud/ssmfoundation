package com.anwei.entity.permission;

import java.io.Serializable;

/**
 * The persistent class for the acct_user database table.
 * 
 */
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long user_id;
	
	private long role_id;

	public UserRole() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
}