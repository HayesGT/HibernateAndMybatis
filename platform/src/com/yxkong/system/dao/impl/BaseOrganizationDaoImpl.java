package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseOrganizationDao;
import com.yxkong.system.model.BaseOrganization;
@Repository("baseOrganizationDao")
public class BaseOrganizationDaoImpl extends MybatisBaseDaoImpl<BaseOrganization,String> implements IBaseOrganizationDao  {
}
