package com.anwei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anwei.common.result.Result;

/**
 * Mapping不要以.pub结尾，否则你懂得
 * @author Anwei
 * @date 2018年9月14日
 */
@Controller
@RestController
@RequestMapping("rest")
public class RestApiController {
	
	@RequestMapping("result")
	public Result result() {
		return Result.success("你好 RestFull");
	}
	
}
