package com.anwei.entity.permission;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	// 权限类型如：如“MENU”表示菜单的访问权限、“OPERATION”表示功能模块的操作权限、“FILE”表示文件的修改权限、“ELEMENT”表示页面元素的可见性控制等
	private String name;
	
	private String url;
	
	private Date createtime;
	
	private int status;

	public Permission() {
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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}