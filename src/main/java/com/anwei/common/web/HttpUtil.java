package com.anwei.common.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class HttpUtil {
	/**
	 * 判断当前请求是否为异步请求.
	 */
	public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader(HttpHeaders.X_REQUESTED_WITH));
    }
	
	public static void responeWriter(HttpServletResponse response, Object result) {
//		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = null;
		try {
			pw=response.getWriter();
			pw.write( JSON.toJSONString(result) );
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pw.close();
		}
	}
}
