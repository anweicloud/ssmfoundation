package com.anwei.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.PermissionDao;
import com.anwei.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Set<String> findPermissionByUserId(long userId) {
		return permissionDao.findPermissionByUserId(userId);
	}

}
