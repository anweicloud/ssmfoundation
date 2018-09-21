package com.anwei.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class BaseController {
	
	public static final String VIEW_THYMELEAF = "thymeleaf/";
	public static final String VIEW_JSP = "jsp/";
	
	/**
	 * 返回thymeleaf视图
	 * @param url
	 * @return
	 */
	public String thymeleaf(String url) {
		return VIEW_THYMELEAF.concat(url);
	}
	
	/**
	 * 返回JSP视图
	 * @param url
	 * @return
	 */
	public String jsp(String url) {
		return VIEW_JSP.concat(url);
	}
	
	/**
	 * 获取Session
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
	/**
	 * 重定向到RequestMapping地址
	 * @param redirectUrl
	 * @param params
	 * @return
	 */
	public ModelAndView redirect(String redirectUrl, Map<String, Object> params) {
		ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
		view.addAllObjects(params);
		return view;
	}
}
