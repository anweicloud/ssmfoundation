package com.anwei.service.impl;
 
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.GenericDao;
import com.anwei.entity.AcctUser;
import com.anwei.service.UserService;
 
/**
 * 
 * @date 2018年8月7日	
 * @author Anwei
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
 
	@Autowired
	private GenericDao<AcctUser, ?> genericDao;

	@Override
	public AcctUser load(int id) {
		return genericDao.load(AcctUser.class, id);
	}

	@Override
	public AcctUser get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AcctUser> findAll() {
		return genericDao.findList(AcctUser.class);
	}

	@Override
	public void persist(AcctUser entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable save(AcctUser entity) {
		return genericDao.save(entity);
	}

	@Override
	public void saveOrUpdate(AcctUser entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AcctUser findByUserName(String username) {
		return genericDao.findObject("from AcctUser where nickName = ?", username);
	}

	@Override
	public AcctUser login(String username, String password) {
		return genericDao.findObject("from AcctUser where nickName = ? and password=?", username, password);
	}
	
}
