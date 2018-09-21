package com.anwei.service;

import java.util.Set;

public interface PermissionService {
	public Set<String> findPermissionByUserId(long user_id);
}
