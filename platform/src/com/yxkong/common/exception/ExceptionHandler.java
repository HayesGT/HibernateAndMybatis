package com.yxkong.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//根据Exception类型返回不同的视图页面
		if (ex instanceof NumberFormatException) {  
            //doSomething...  
            return new ModelAndView("number");  
        } else if (ex instanceof NullPointerException) {  
            //doSomething...  
            return new ModelAndView("null");  
        } 
		return new ModelAndView("exception"); 
	}

}
