package com.anwei.service;
 
import java.io.Serializable;
import java.util.List;

import com.anwei.entity.permission.User;
 
/**
 * 
 * @date 2018年8月7日	
 * @author Anwei
 *
 */
public interface UserService {
	User load(long id);
 
	User get(String id);
	
	User findByUserName(String username);
	
	User login(String username, String password);
	
	List<User> findAll();
 
	void persist(User entity);
 
	Serializable save(User entity);
 
	void saveOrUpdate(User entity);
 
	void delete(String id);
 
	void flush();
}
