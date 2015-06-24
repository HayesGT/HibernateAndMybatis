package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IBaseRoleDao;
import com.yxkong.system.model.BaseRole;
import com.yxkong.system.service.IBaseRoleService;
@Service("baseRoleService")
public class BaseRoleServiceImpl extends MybatisBaseServiceImpl<BaseRole,String> implements IBaseRoleService {
	
	@Resource
	private IBaseRoleDao baseRoleDao;
	@Override
	protected IMybatisBaseDao<BaseRole, String> getBaseDao() {
		return baseRoleDao;
	}

}