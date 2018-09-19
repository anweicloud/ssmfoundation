package com.anwei.entity.permission;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the acct_user database table.
 * 
 */
@Entity
@Table(name = "access_menu_permission")
@NamedQuery(name = "MenuPermission.findAll", query = "SELECT a FROM MenuPermission a")
public class MenuPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long menu_id;
	private long permission_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createtime")
	private Date createtime;

	public MenuPermission() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}

	public long getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(long permission_id) {
		this.permission_id = permission_id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}