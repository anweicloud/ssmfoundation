package com.anwei.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anwei.common.result.Result;

@RequestMapping("pub")
@Controller
public class LoginController {
	
	@RequestMapping("login")
	public String login(String str) {
		System.out.println(">>>>>>>>>>>>>>>>> " + str);
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="auth")
	public Result auth(HttpServletRequest req) {
		System.out.println("auth");
		req.getSession().setAttribute("current_user", "admin");
		return Result.success();
	}
	
	@ResponseBody
	@RequestMapping(value="logout")
	public Result logout(HttpServletRequest req) {
		System.out.println("logout");
		req.getSession().invalidate();
		return Result.success();
	}
	
	@RequestMapping("reg")
	public String register() {
		return "reg";
	}
	
	@ResponseBody
	@RequestMapping(value="reg2")
	public Map<String, Object> reg2() {
		Map<String, Object> map = new HashMap<>();
		map.put("code", 1);
		map.put("msg", "Success 你好");
		return map;
	}
	
	@ResponseBody
	@RequestMapping("reg3")
	public Result reg3() {
		return Result.success("Heoo");
	}
	
}
