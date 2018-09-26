package com.anwei.dao;

import java.util.Set;

public interface PermissionDao {
	
	public Set<String> findPermissionByUserId(long userId);
	
}
