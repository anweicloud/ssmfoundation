package com.anwei.test.shiro;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;


public class ShiroTest {
	@Test
	public void testName() throws Exception {
		System.out.println("Shiro 入门");
		login("classpath:shiro.ini", "zhang", "123");
		
	    Subject subject = SecurityUtils.getSubject();  
	    System.out.println(subject.isAuthenticated());
	    
	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录  
	    System.out.println(subject.hasRole("role2"));
	    
	    boolean[] bs = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
	    System.out.println(bs[0]);
	    System.out.println(bs[1]);
	    System.out.println(bs[2]);
	    
	    //断言拥有角色：role1 and role3 失败抛出异常:   Subject does not have role [role3]
//	    subject.checkRoles("role1", "role3");
	    
	    subject.checkPermission("user:create");
	    subject.isPermitted("user:create");
	    subject.checkPermission("system:user:create,update,delete,view");
	  
	    //6、退出  
	    subject.logout();  
		
	}
	
	private void login(String configFile, String username, String password) {  
	    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<org.apache.shiro.mgt.SecurityManager> factory =  
	            new IniSecurityManagerFactory(configFile);  
	  
	    //2、得到SecurityManager实例 并绑定给SecurityUtils  
	    org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();  
	    SecurityUtils.setSecurityManager(securityManager);  
	  
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");  
	    subject.login(token);  
	}  
}
