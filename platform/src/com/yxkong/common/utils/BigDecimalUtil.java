package com.yxkong.common.utils;

import java.math.BigDecimal;

public class BigDecimalUtil {
	/**
	 * 将BigDecimal转成Long  null 转为0
	 * @author dcc
	
	 * @param obj
	 * @return
	 */
	public static Long BigDecimal2Long(Object obj){
		if(obj==null){
			return 0l;
		}
		BigDecimal bd=(BigDecimal)obj;
		return bd.longValue();
	} 
	/**
	 * 将BigDecimal转成Intger  null 转为0
	 * @author dcc
	
	 * @param obj
	 * @return
	 */
	public static Integer bigDecimal2Integer(Object obj){
		if(obj==null){
			return 0;
		}
		BigDecimal bd=(BigDecimal)obj;
		return bd.intValue();
	} 
}
