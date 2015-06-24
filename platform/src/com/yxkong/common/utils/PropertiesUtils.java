package com.yxkong.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("static-access")
public class PropertiesUtils implements ApplicationContextAware  {
	public  static final String KEY="propertyConfigurer";
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	/**
	 * 功能函数说明：获取属性文件中的对应key的字符串
	 * @author ducc
	 * @created 2014年6月10日 下午9:11:51
	 * @updated 
	 * @param keyName
	 * @return
	 */
	public static String parseStr(String keyName){
		CustomizedPropertyPlaceholderConfigurer  cp=(CustomizedPropertyPlaceholderConfigurer) applicationContext.getBean(KEY);
		return  cp.getContextProperty(keyName).toString();
	}
	/**
	 * 功能函数说明：获取属性文件中的对应key的int类型的值
	 * @author ducc
	 * @created 2014年6月10日 下午9:13:23
	 * @updated 
	 * @param keyName
	 * @return
	 */
	public static Integer parseInt(String keyName){
		CustomizedPropertyPlaceholderConfigurer  cp=(CustomizedPropertyPlaceholderConfigurer) applicationContext.getBean(KEY);
		return Integer.parseInt(cp.getContextProperty(keyName).toString());
	}
	/**
	 * 功能函数说明：获取属性文件中的对应key的double类型的值
	 * @author ducc
	 * @created 2014年6月10日 下午9:13:23
	 * @updated 
	 * @param keyName
	 * @return
	 */
    public static Double parseDouble(String keyName) {
        CustomizedPropertyPlaceholderConfigurer  cp=(CustomizedPropertyPlaceholderConfigurer) applicationContext.getBean(KEY);
        return Double.parseDouble(cp.getContextProperty(keyName).toString());
    }   
    /**
	 * 功能函数说明：获取属性文件中的对应key的boolean类型的值
	 * @author ducc
	 * @created 2014年6月10日 下午9:13:23
	 * @updated 
	 * @param keyName
	 * @return
	 */
	public static Boolean parseBoolean(String keyName) {
        CustomizedPropertyPlaceholderConfigurer  cp=(CustomizedPropertyPlaceholderConfigurer) applicationContext.getBean(KEY);
        boolean flag=false;
        if(cp.getContextProperty(keyName)!=null){
        	if("true".equalsIgnoreCase(cp.getContextProperty(keyName).toString())){
        		flag=true;
        	}
        }
        return flag;
    }   

}
