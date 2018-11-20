package com.anwei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.anwei.entity.permission.User;

public interface UserDao {
	List<User> findAll();
	User login(@Param("username") String username,@Param("password") String password);
	User load(long userId);
}
