package com.anwei.controller;
 
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anwei.entity.common.GenericResult;
import com.anwei.entity.permission.User;
import com.anwei.exception.CustomException;
import com.anwei.service.UserService;
 
/**
 * 
 * @date 2018年8月7日	
 * @author Anwei
 *
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
 
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable int userId){
		LOGGER.info("查询用户：" + userId);
		User userInfo = userService.load(userId);
		modelMap.addAttribute("userInfo", userInfo);
		return jsp("user/showInfo");
	}
	
	@ResponseBody 
	@RequestMapping("showInfos")
	public List<User> showUserInfos(){
		LOGGER.info("查询用户全部用户");
		List<User> userInfos = userService.findAll();
		return userInfos;
	}
	
	@RequestMapping("gr")
	public @ResponseBody GenericResult<List<User>> gr(){
		LOGGER.info("查询用户全部用户");
		GenericResult<List<User>> re = new GenericResult<List<User>>("001", "操作成功");
		re.setData(userService.findAll());
		return re;
	}
	
	@RequestMapping( value="reg", method=RequestMethod.GET )
	public ModelAndView signup( Integer pageNo ) throws CustomException {
		System.out.println(pageNo); 
		ModelAndView mav = new ModelAndView("user/signup");
		mav.addObject("xxl", 1223);
		mav.addObject("list", userService.findAll());
		return mav;
	}
}
