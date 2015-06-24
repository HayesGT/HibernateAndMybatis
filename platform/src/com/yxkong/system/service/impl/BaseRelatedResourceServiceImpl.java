package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IBaseRelatedResourceDao;
import com.yxkong.system.model.BaseRelatedResource;
import com.yxkong.system.service.IBaseRelatedResourceService;
@Service("baseRelatedResourceService")
public class BaseRelatedResourceServiceImpl extends MybatisBaseServiceImpl<BaseRelatedResource,String> implements IBaseRelatedResourceService {

    @Resource
    private IBaseRelatedResourceDao baseRelatedResourceDao;
	@Override
	protected IMybatisBaseDao<BaseRelatedResource, String> getBaseDao() {
		return baseRelatedResourceDao;
	}
    
}