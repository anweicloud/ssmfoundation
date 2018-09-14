package com.anwei.common.web;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	/**
	 * 判断当前请求是否为异步请求.
	 */
	public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader(HttpHeaders.X_REQUESTED_WITH));
    }
}
