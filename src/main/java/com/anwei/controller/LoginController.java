package com.anwei.controller;

import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anwei.common.enums.ResultCode;
import com.anwei.common.result.Result;
import com.anwei.common.web.HttpUtil;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String login(String str, Model model) {
		model.addAttribute("msg", "Hello thymeleaf");
		return "login";
	}
	
	@RequestMapping("login2")
	public String loginJSP(String str) {
		return "login2";
	}
	
	/**
	 * Servlet退出销毁session
	 * 
	 * 使用shiro就不需要这个方法了，验证拦截器使用shiro的
	 * 不再使用SpringMVC拦截器去做
	 * 
	 * 也就是使用shiro管理登录及权限信息，跟Servlet Session无关
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="logout1")
	public Result logout(HttpServletRequest req) {
		System.out.println("logout");
		req.getSession().invalidate();
		return Result.success();
	}
	
	@RequestMapping("reg")
	public String register() {
		return "reg";
	}
	
	@RequestMapping("success")
	public String success(Model model) {
		model.addAttribute("currentUser", SecurityUtils.getSubject().getSession().getAttribute("currentUser"));
		model.addAttribute("currentTime", DateFormatUtils.format(Calendar.getInstance(Locale.CHINA), "yyyy/MM/dd HH:mm:ss"));
		return "success";
	}
	
	/**
	 * Shiro登录认证及授权
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping("shiro")
	public String shiroLogin(HttpServletRequest req, HttpServletResponse resp, String username, String password, Model model) {
		System.out.println(username+" "+password);
		
		String msg = null;
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try {  
            subject.login(token);  
            
            if (subject.isAuthenticated()) {  
            	System.out.println("验证通过");
            	return "redirect:./success";  
            } else {  
                return "login";  
            }  
        // 以下catch中的异常来自MyReaml中的手动抛出异常，是依据一定的条件抛出指定异常。例如if(u>10) throw new ExcessiveAttemptsException();
        } catch (IncorrectCredentialsException e) {  
            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
        } catch (ExcessiveAttemptsException e) {  
            msg = "登录失败次数过多";  
        } catch (LockedAccountException e) {  
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
        } catch (DisabledAccountException e) {  
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
        } catch (ExpiredCredentialsException e) {  
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
        } catch (UnknownAccountException e) {  
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
        } catch (UnauthorizedException e) {  
            msg = "您没有得到相应的授权！" + e.getMessage();  
        } catch (AccountException e) {
        	msg = e.getMessage();
		}
		
		System.err.println(msg);
		if (HttpUtil.isAjax(req)) { // Ajax请求，返回提示信息
			HttpUtil.responeWriter(resp, Result.failure(ResultCode.USER_LOGIN_ERROR, msg));
			return null;
		} else {
			// 跳转至jsp并带入提示信息
			model.addAttribute("msg", msg);
			return "login";
		}
	}
	
}
