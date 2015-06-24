package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IBaseRelatedUserDao;
import com.yxkong.system.model.BaseRelatedUser;
import com.yxkong.system.service.IBaseRelatedUserService;
@Service("baseRelatedUserService")
public class BaseRelatedUserServiceImpl extends MybatisBaseServiceImpl<BaseRelatedUser,String> implements IBaseRelatedUserService {
	
	@Resource
	private IBaseRelatedUserDao baseRelatedUserDao;
	@Override
	protected IMybatisBaseDao<BaseRelatedUser, String> getBaseDao() {
		return baseRelatedUserDao;
	}

}