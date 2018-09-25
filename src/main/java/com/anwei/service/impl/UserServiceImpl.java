package com.anwei.service.impl;
 
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.GenericDao;
import com.anwei.entity.permission.User;
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
	private GenericDao<User, ?> genericDao;

	@Override
	public User load(long id) {
		return genericDao.load(User.class, id);
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		return genericDao.findList(User.class);
	}

	@Override
	public void persist(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable save(User entity) {
		return genericDao.save(entity);
	}

	@Override
	public void saveOrUpdate(User entity) {
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
	public User findByUserName(String username) {
		return genericDao.findObject("from User where nickName = ?", username);
	}

	@Override
	public User login(String username, String password) {
		return genericDao.findObject("from User where username = ? and password=?", username, password);
	}
	
}
