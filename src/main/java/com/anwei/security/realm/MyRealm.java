package com.anwei.security.realm;  
  
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.anwei.entity.AcctUser;
import com.anwei.service.UserService;  
  
/** 
 * 自定义的指定Shiro验证用户登录的类 
 * @see 在本例中定义了2个用户:papio和big,papio具有admin角色和admin:manage权限,big不具有任何角色和权限 
 * @create
 * @author
 */  
public class MyRealm extends AuthorizingRealm {  
	
	@Autowired
	private UserService userService;
	
	public MyRealm() {
		super();
	}

	/** 
     * 认证     Authentication
     * 验证当前登录的Subject 
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
        //获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e  
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        
        AcctUser user = userService.login(token.getUsername(), new String(token.getPassword()));
        
        // 根据不同的情况抛出不同的异常，达到指定的错误文字提示
        if(null == user){
			throw new AccountException("帐号或密码不正确！");
		/**
		 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
		 */
		}else if(user.getStatus() == 1){
			throw new DisabledAccountException("帐号已经禁止登录！");
		}else{
			//更新登录时间 last login time
//			user.setLastLoginTime(new Date());
//			userService.updateByPrimaryKeySelective(user);
		}
        
        this.setSession("currentUser", user);
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }  
    
	
    /** 
     * 授权     Authorization
     * 为当前登录的Subject授予角色和权限 
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
     * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){  
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentUsername = (String)super.getAvailablePrincipal(principals);  
        System.out.println("认证成功！这里是授权：" + currentUsername);

        SimpleAuthorizationInfo authorInfo = new SimpleAuthorizationInfo();  
        //实际中可能会像上面注释的那样从数据库取得  
        
        //根据用户ID查询角色（role），放入到Authorization里。使用Set去重
//		Set<String> roles = roleService.findRoleByUserId(userId);
//		authorInfo.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。也就是url
//		Set<String> permissions = permissionService.findPermissionByUserId(userId);
//		authorInfo.setStringPermissions(permissions);
		return authorInfo;
    }  
      
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }  
}
 
