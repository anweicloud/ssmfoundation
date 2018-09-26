package com.anwei.test.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.anwei.entity.permission.User;
import com.anwei.service.PermissionService;
import com.anwei.service.RoleService;
import com.anwei.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml"})
public class MybatisTest {
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private PermissionService permService;
	
	@Test
	public void testService() throws Exception {
		List<User> user = userService.findAll();
		System.out.println(JSON.toJSONString(user));
		User user2 = userService.login("A", "1");
		System.out.println(JSON.toJSONString(user2));
		user2 = userService.load(1);
		System.out.println(JSON.toJSONString(user2));
		System.out.println(roleService.findRoleByUserId(user2.getId()));
		System.out.println(permService.findPermissionByUserId(user2.getId()));
	}
}
