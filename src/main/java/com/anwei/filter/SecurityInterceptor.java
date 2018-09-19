package com.anwei.filter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.anwei.common.enums.ResultCode;
import com.anwei.common.network.IpUtils;
import com.anwei.common.result.Result;
import com.anwei.common.web.HttpUtil;

/**
 * 1.此拦截器用于拦截所有请求，用于登录权限验证
 * 2.拦截 带 moduleId 参数的请求，在渲染视图之前返回 模块权限值
 * @author Anwei
 * @date 2018年8月9日
 */

@Component
public class SecurityInterceptor implements HandlerInterceptor{
//    @Autowired
//    SysUserRolePermService sysUserRolePermService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("SecurityInterceptor preHandle:"+request.getContextPath()+","+request.getRequestURI()+","+request.getMethod());
       
        String ip = IpUtils.getRemoteAddr(request);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> ip: " + ip);
        String method = request.getMethod();
        /* CSRF
        if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("DELETE") || method.equalsIgnoreCase("PUT")) {
            String csrf_token = request.getParameter("csrf_token");
            System.out.println(csrf_token + "   1222222222222222222222222222222222222222222222");
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0 && csrf_token != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("csrf_token")) {
                        if (Integer.valueOf(csrf_token) == cookie.getValue().hashCode()) {
                            return true;
                        }
                    }
                }
            }
        }*/
        HttpSession session = request.getSession();
        if (session.getAttribute("current_user") == null) {
            System.out.println("AuthorizationException:未登录！"+request.getMethod());
            if (HttpUtil.isAjax(request)) {
            	response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(Result.failure(ResultCode.USER_NOT_LOGGED_IN)));
                out.flush();
                out.close();
            } else if("POST".equalsIgnoreCase(method)) {
                response.setContentType("text/html; charset=utf-8");  
                PrintWriter out = response.getWriter();
                out.write("<h1>请先登录</h1>");
                out.flush();
                out.close();
            } else {
                response.sendRedirect(request.getContextPath()+"/pub/login"); 
            }
            return false;
        }
        
//        AcctUser user = (AcctUser) session.getAttribute("current_user");
        // 验证权限
//        if (user != null) {
//        	List<String> permission = user.getPermission();
        	
//        }
        /*
        Object obj = request.getParameter(Helper.PARAM_FUNCTION_ID);
        if(obj==null) return true;//没有带功能参数不需要验证
        int functionId = Integer.parseInt(obj.toString());
        String rs = sysUserRolePermService.permissionValidate(functionId);
        System.out.println("校验结果:"+rs);
        if(rs.trim().isEmpty()){
            return true;//正常通过
        }else{
            response.setContentType("text/html; charset=utf-8");  
            PrintWriter out = response.getWriter();   
            out.write(JSON.toJSONString(new Result(false,rs)));
            out.flush();
            out.close();
            return false;
        }*/
		return true;
    }
    //模块权限值
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        /*
        Object obj = request.getParameter(Helper.PARAM_MODULE_ID);
        System.out.println(Helper.PARAM_MODULE_ID+":"+obj);
        if(obj == null) return;//如果没有moduleId 参数，否则什么都不做，否则返回模块权限值
        System.out.println("SecurityInterceptor postHandle:"+request.getContextPath()+","+request.getRequestURI()+","+request.getMethod());
        SysUser loginUser = (SysUser)request.getSession().getAttribute(Helper.SESSION_USER);
        int value = sysUserRolePermService.getModulePerm(loginUser.getUserid(),Integer.parseInt(obj.toString()));
        modelAndView.addObject(Helper.MVALUE,value);
        */
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
 
    }
 
}
