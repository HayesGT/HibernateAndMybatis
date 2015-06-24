package com.yxkong.common.web.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.yxkong.common.utils.SpringMvcUtil;

public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass()); 
	protected abstract  String getPrefix();
	protected  final String ADD=getPrefix()+"add";
	protected  final String EDIT=getPrefix()+"add";
	protected  final String VIEW=getPrefix()+"view";
	protected  final String LIST=getPrefix()+"list";
	
	@InitBinder  
    protected void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, false));  
    }  
    @ExceptionHandler
    public String exception(HttpServletRequest request, HttpServletResponse response, Exception e) {  
    	 logger.error(this.getClass()+" is errory, errorType="+e.getClass(),e);
    	 e.printStackTrace();
         //如果是json格式的ajax请求
         if (request.getHeader("accept").indexOf("application/json") > -1
                 || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) { 
            response.setStatus(500);
            response.setContentType("application/json;charset=utf-8");   
            SpringMvcUtil.responseWriter(response, e.getMessage());
            return null;
         }
         else{//如果是普通请求
            request.setAttribute("exceptionMsg", e.getMessage());  
            // 根据不同的异常类型可以返回不同界面
            if(e instanceof SQLException) 
                return "sqlerror";   
            else if(e instanceof NullPointerException)
            	return "null";
            else 
                return "error";  
        }
    }  
}
