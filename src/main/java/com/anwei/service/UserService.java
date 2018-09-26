package com.anwei.service;
 
import java.util.List;

import com.anwei.entity.permission.User;
 
/**
 * 
 * @date 2018年8月7日	
 * @author Anwei
 *
 */
public interface UserService {
	User load(long userId);
	User login(String username, String password);
	List<User> findAll();
}
