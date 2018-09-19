package com.anwei.service;
 
import java.io.Serializable;
import java.util.List;

import com.anwei.entity.AcctUser;
 
/**
 * 
 * @date 2018年8月7日	
 * @author Anwei
 *
 */
public interface UserService {
	AcctUser load(int id);
 
	AcctUser get(String id);
	
	AcctUser findByUserName(String username);
	
	AcctUser login(String username, String password);
	
	List<AcctUser> findAll();
 
	void persist(AcctUser entity);
 
	Serializable save(AcctUser entity);
 
	void saveOrUpdate(AcctUser entity);
 
	void delete(String id);
 
	void flush();
}
