package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseUserRelatedDao;
import com.yxkong.system.model.BaseUserRelated;
@Repository("baseUserRelatedDao")
public class BaseUserRelatedDaoImpl extends MybatisBaseDaoImpl<BaseUserRelated,String> implements IBaseUserRelatedDao  {
}
