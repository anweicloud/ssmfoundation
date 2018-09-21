package com.anwei.entity.permission;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the acct_user database table.
 * 
 */
@Entity
@Table(name = "access_role_permission")
@NamedQuery(name = "RolePermission.findAll", query = "SELECT a FROM RolePermission a")
public class RolePermission implements Serializable {
	
	private static final long serialVersionUID = 9052372091159868163L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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