package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseUserDao;
import com.yxkong.system.model.BaseUser;
@Repository("baseUserDao")
public class BaseUserDaoImpl extends MybatisBaseDaoImpl<BaseUser,String> implements IBaseUserDao  {

}
