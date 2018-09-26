package com.anwei.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.RoleDao;
import com.anwei.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Set<String> findRoleByUserId(long userId) {
		return roleDao.findRoleByUserId(userId);
	}

}
