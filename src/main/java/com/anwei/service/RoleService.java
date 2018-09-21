package com.anwei.service;

import java.util.Set;

public interface RoleService {
	public Set<String> findRoleByUserId(long user_id);
}
