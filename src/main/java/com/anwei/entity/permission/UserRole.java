package com.anwei.entity.permission;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the acct_user database table.
 * 
 */
@Entity
@Table(name = "access_user_role")
@NamedQuery(name = "UserRole.findAll", query = "SELECT a FROM UserRole a")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long user_id;
	
	private long role_id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	private Date createtime;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}