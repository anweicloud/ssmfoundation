package com.anwei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anwei.common.enums.ResultCode;
import com.anwei.common.result.Result;
import com.anwei.common.web.HttpUtil;
import com.anwei.shiro.bo.UserOnlineBo;
import com.anwei.shiro.session.CustomSessionManager;

@Controller
public class AuthcController extends BaseController {
	
	@Autowired
	CustomSessionManager customSessionManager;
	
	@RequestMapping(value="online")
	public ModelAndView online(){
		List<UserOnlineBo> list = customSessionManager.getAllUser();
		return new ModelAndView(jsp("user/online"),"list",list);
	}
	
	@RequestMapping("sessionstatus")
	public String sessionstatus(HttpServletRequest request, HttpServletResponse response, boolean status, String sessionIds) {
		customSessionManager.changeSessionStatus(status, sessionIds);
		return thymeleaf("sessionstatus");
	}

	@RequestMapping("unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
		
		if (HttpUtil.isAjax(request)) {
			HttpUtil.responeWriter(response, Result.failure(ResultCode.PERMISSION_NO_ACCESS));
			return null;
		}
		
		return thymeleaf("unauthorized");
		
	}
	
}
