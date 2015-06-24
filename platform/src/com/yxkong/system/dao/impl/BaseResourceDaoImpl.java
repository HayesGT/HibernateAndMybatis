package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseResourceDao;
import com.yxkong.system.model.BaseResource;
@Repository("baseResourceDao")
public class BaseResourceDaoImpl extends MybatisBaseDaoImpl<BaseResource,String> implements IBaseResourceDao  {
}
