package com.anwei.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.GenericDao;
import com.anwei.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private GenericDao<Object, ?> genericDao;
	
	@Override
	public Set<String> findPermissionByUserId(long user_id) {
		String sql = "select p.url from access_permission p join access_role_permission rp on(p.id=rp.perm_id) join access_user_role ur on(rp.role_id=ur.role_id) where ur.user_id=?";
		Set<String> typeSet = new HashSet<String>();
		List<Map<String, Object>> list = genericDao.findColumnsBySQL(sql, user_id);
		for (Map<String, Object> map : list) {
			if (map.get("url") != null) {
				typeSet.add( map.get("url").toString() );
			}
		}
		return typeSet;
	}

}
