package com.anwei.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.GenericDao;
import com.anwei.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private GenericDao<Object, ?> genericDao;
	
	@Override
	public Set<String> findRoleByUserId(long user_id) {
		String sql = "select type from access_role r join access_user_role ur on(r.id=ur.role_id and ur.user_id=?)";
		Set<String> typeSet = new HashSet<String>();
		List<Map<String, Object>> list = genericDao.findColumnsBySQL(sql, user_id);
		for (Map<String, Object> map : list) {
			if (map.get("type") != null) {
				typeSet.add( map.get("type").toString() );
			}
		}
		return typeSet;
	}

}
