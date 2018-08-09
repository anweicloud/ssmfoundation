package com.anwei.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.anwei.common.result.Result;

@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="auth.pub")
	public String auth(HttpServletRequest req) {
		System.out.println("auth");
		req.getSession().setAttribute("current_user", "admin");
		System.out.println(JSON.toJSONString(Result.success()));
		return JSON.toJSONString(Result.success());
	}
	
	@ResponseBody
	@RequestMapping(value="str.pub")
	public String str(HttpServletRequest req) {
		return "Hello World ! <a href=\"#\">你好世界！</a>";
	}
	
	@ResponseBody
	@RequestMapping(value="out.pub", produces={"text/json; charset=utf-8"})
	public String out(HttpServletRequest req) {
		System.out.println("auth");
		req.getSession().invalidate();
		System.out.println(JSON.toJSONString(Result.success()));
		return JSON.toJSONString(Result.success());
	}
	
	@RequestMapping("register")
	public String register() {
		return "reg";
	}
	
	
	
}
