package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IBaseUserRelatedDao;
import com.yxkong.system.model.BaseUserRelated;
import com.yxkong.system.service.IBaseUserRelatedService;
@Service("baseUserRelatedService")
public class BaseUserRelatedServiceImpl extends MybatisBaseServiceImpl<BaseUserRelated,String> implements IBaseUserRelatedService {
	
	@Resource
	private IBaseUserRelatedDao baseUserRelatedDao;
	@Override
	protected IMybatisBaseDao<BaseUserRelated, String> getBaseDao() {
		return baseUserRelatedDao;
	}

}