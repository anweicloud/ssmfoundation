package com.anwei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anwei.common.enums.ResultCode;
import com.anwei.common.result.Result;
import com.anwei.common.web.HttpUtil;

@Controller
public class AuthcController extends BaseController {

	@RequestMapping("unauthorized")
	public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
		
		if (HttpUtil.isAjax(request)) {
			HttpUtil.responeWriter(response, Result.failure(ResultCode.PERMISSION_NO_ACCESS));
			return null;
		}
		
		return thymeleaf("unauthorized");
		
	}
	
}
