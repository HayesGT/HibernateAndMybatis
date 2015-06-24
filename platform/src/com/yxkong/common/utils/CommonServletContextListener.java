package com.yxkong.common.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 功能说明：设置初始化参数到ServletContext
 * @author ducc
 * @created 2014年6月12日 上午11:50:17
 * @updated
 */
public class CommonServletContextListener implements ServletContextListener {
	public static final String CONTEXT_PATH = "contextPath";
    private ServletContext context = null;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//设置context上下文
        this.context = sce.getServletContext(); 
        this.context.setAttribute(CONTEXT_PATH, this.context.getContext("/"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
