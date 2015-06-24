package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseRoleDao;
import com.yxkong.system.model.BaseRole;
@Repository("baseRoleDao")
public class BaseRoleDaoImpl extends MybatisBaseDaoImpl<BaseRole,String> implements IBaseRoleDao  {
}
