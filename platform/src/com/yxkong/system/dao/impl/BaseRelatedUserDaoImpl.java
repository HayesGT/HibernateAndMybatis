package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseRelatedUserDao;
import com.yxkong.system.model.BaseRelatedUser;
@Repository("baseRelatedUserDao")
public class BaseRelatedUserDaoImpl extends MybatisBaseDaoImpl<BaseRelatedUser,String> implements IBaseRelatedUserDao  {
}
