package com.yxkong.common.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * 功能说明：spring 工具类
 * @author ducc
 * @created 2014年6月23日 下午8:51:19
 * @updated
 */
public class ServiceUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ServiceUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	 * 功能说明：从applicationcontext中获取bean
	 * @author ducc
	 * @created 2014年6月23日 下午8:52:20
	 * @updated 
	 * @param beanName
	 * @return
	 */
	public static Object getService(String beanName) {
		return applicationContext.getBean(beanName);
	}

}
