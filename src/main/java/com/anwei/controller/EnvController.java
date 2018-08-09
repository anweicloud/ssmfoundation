package com.anwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anwei.exception.CustomException;

/**
 * 环境测试
 * @author Anwei
 * @date 2018年8月9日
 */
@Controller
@RequestMapping("env")
public class EnvController {
	
	@RequestMapping("index")
	public String index() {
		return "env/index";
	}
	
	@RequestMapping("system")
	public String system_exception() {
		Integer.parseInt("123a");
		return "system";
	}
	
	@RequestMapping("custom")
	public String custom_exception(String p) throws CustomException {
		if (p == null) {
			throw new CustomException("参数错误！");
		}
		return "custom";
	}
	
	@RequestMapping("ajax")
	public String ajax_exception(String p) throws CustomException {
		if (p == null) {
			throw new CustomException("参数错误！");
		}
		return "ajax";
	}
	
}
