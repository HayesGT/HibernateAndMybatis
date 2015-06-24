package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IBaseUserDao;
import com.yxkong.system.model.BaseUser;
import com.yxkong.system.service.IBaseUserService;
@Service("baseUserService")
public class BaseUserServiceImpl extends MybatisBaseServiceImpl<BaseUser,String> implements IBaseUserService {

	@Resource
	private IBaseUserDao baseUserDao;
	@Override
	protected IMybatisBaseDao<BaseUser, String> getBaseDao() {
		return baseUserDao;
	}
	@Override
	public void exeSave(BaseUser item) throws Exception {
		getBaseDao().save(item);
		try {
			//int i=1/0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	
	

}