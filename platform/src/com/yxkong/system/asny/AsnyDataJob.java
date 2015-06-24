package com.yxkong.system.asny;
/**
 * 功能说明：定时任务
 *   aplicationContext-common.xml文件中配置
 *   
 * @author ducc
 * @created 2014年6月28日 下午4:44:31
 * @updated
 */
public class AsnyDataJob {

	int i=0;
	public void execute(){
		i++;
		System.err.println("调度任务执行。。。"+i);
		
	}
}
