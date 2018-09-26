package com.anwei.dao;

import java.util.List;

import com.anwei.entity.permission.User;

public interface UserDao {
	List<User> findAll();
	User login(String username, String password);
	User load(long userId);
}
