package com.yxkong.system.service;

import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.system.model.BaseUser;


public interface IBaseUserService extends IMybatisBaseService<BaseUser,String> {
    /**
     * 功能说明：测试事务
     * @author ducc
     * @created 2014年6月22日 下午3:07:44
     * @updated 
     * @param item
     * @throws Exception
     */
	void exeSave(BaseUser item)throws Exception;

}
