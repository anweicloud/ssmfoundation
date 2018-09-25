package com.anwei.exception;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.anwei.common.enums.ResultCode;
import com.anwei.common.result.Result;
import com.anwei.common.web.HttpUtil;
 
/**
 * spring mvc 全局处理异常捕获 根据请求区分ajax和普通请求，分别进行响应.
 * 第一、异常信息输出到日志中。
 * 第二、截取异常详细信息的前50个字符，写入日志表中t_s_log。
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
//	@Autowired
//	private SystemService systemService;
	//记录日志信息
	private static final Logger log = Logger
			.getLogger(GlobalExceptionResolver.class);
	//记录数据库最大字符长度
	private static final int WIRTE_DB_MAX_LENGTH = 1500;
	//记录数据库最大字符长度
	private static final short LOG_LEVEL = 6;
	//记录数据库最大字符长度
	private static final short LOG_OPT = 3;
	/**
	 * 对异常信息进行统一处理，区分异步和同步请求，分别处理
	 */
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
        boolean isajax = HttpUtil.isAjax(request);
        System.out.println(isajax);
        Throwable deepestException = deepestException(ex);
        return processException(request, response, handler, deepestException, isajax);
	}
	
	/**
	 * 获取最原始的异常出处，即最初抛出异常的地方
	 */
    private Throwable deepestException(Throwable e){
        Throwable tmp = e;
        int breakPoint = 0;
        while(tmp.getCause()!=null){
            if(tmp.equals(tmp.getCause())){
                break;
            }
            tmp=tmp.getCause();
            breakPoint++;
            if(breakPoint>1000){
                break;
            }
        } 
        return tmp;
    }
	/**
	 * 处理异常.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @param isajax
	 * @return
	 */
	private ModelAndView processException(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Throwable ex, boolean isajax) {
		//步骤一、异常信息记录到日志文件中.
		log.error("全局处理异常捕获:", ex);
		//步骤二、异常信息记录截取前50字符写入数据库中.
		logDb(ex);
		//步骤三、分普通请求和ajax请求分别处理.
		if(isajax){
			return processAjax(request,response,handler,ex);
		}else{
			return processNotAjax(request,response,handler,ex);
		}
	}
	/**
	 * 异常信息记录截取前50字符写入数据库中
	 * @param ex
	 */
	private void logDb(Throwable ex) {
		//String exceptionMessage = getThrowableMessage(ex);
		String exceptionMessage = "错误异常: "+ex.getClass().getSimpleName()+",错误描述："+ex.getMessage();
		if(StringUtils.isNotEmpty(exceptionMessage)){
			if(exceptionMessage.length() > WIRTE_DB_MAX_LENGTH){
				exceptionMessage = exceptionMessage.substring(0,WIRTE_DB_MAX_LENGTH);
			}
		}
//		systemService.addLog(exceptionMessage, LOG_LEVEL, LOG_OPT);
	}
	/**
	 * ajax异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @return
	 */
	private ModelAndView processAjax(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Throwable deepestException){
		ModelAndView empty = new ModelAndView();
		HttpUtil.responeWriter(response, Result.failure(ResultCode.SYSTEM_INNER_ERROR));
		empty.clear();
		return empty;
	}
	
	/**
	 * 普通页面异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @return
	 */
	private ModelAndView processNotAjax(HttpServletRequest request,
			HttpServletResponse response, Object handler, Throwable ex) {
//		String exceptionMessage = getThrowableMessage(ex);
		String exceptionMessage = getMessage(ex);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", exceptionMessage);
		return new ModelAndView("error/500", model);
	}
	
	private String getMessage(Throwable ex) {
		return (ex instanceof CustomException)? ex.getMessage() : ResultCode.SYSTEM_INNER_ERROR.message();
		
	}
	
	/**
	 * 返回错误信息字符串
	 * 
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public String getThrowableMessage(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}
}
