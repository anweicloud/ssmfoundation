package com.anwei.entity.permission;

import java.io.Serializable;

/**
 * The persistent class for the acct_user database table.
 * 
 */
public class RolePermission implements Serializable {
	
	private static final long serialVersionUID = 9052372091159868163L;

	private long id;
	
	private long perm_id;
	
	private long role_id;

	public RolePermission() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPerm_id() {
		return perm_id;
	}

	public void setPerm_id(long perm_id) {
		this.perm_id = perm_id;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}
}