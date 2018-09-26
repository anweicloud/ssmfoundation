package com.anwei.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anwei.dao.UserDao;
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
	private UserDao userDao;
	
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User login(String username, String password) {
		return userDao.login(username, password);
	}

	@Override
	public User load(long userId) {
		return userDao.load(userId);
	}
	
}
