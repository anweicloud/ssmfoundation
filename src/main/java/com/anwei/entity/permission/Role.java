package com.anwei.entity.permission;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	private Date createtime;

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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}