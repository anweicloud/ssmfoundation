package com.anwei.entity.permission;

import java.io.Serializable;
import javax.persistence.*;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the acct_user database table.
 * 
 */
@Entity
@Table(name = "access_role")
@NamedQuery(name = "Role.findAll", query = "SELECT a FROM Role a")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;
	
	private String type;
	
	private int status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	private Date createtime;
	
	@Transient
	List<Permission> permissions;

	public Role() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}