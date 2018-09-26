package com.anwei.dao;

import java.util.Set;

public interface RoleDao {
	
	public Set<String> findRoleByUserId(long user_id);
	
}
