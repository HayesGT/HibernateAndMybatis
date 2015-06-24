package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IBaseRelatedResourceDao;
import com.yxkong.system.model.BaseRelatedResource;
@Repository("baseRelatedResourceDao")
public class BaseRelatedResourceDaoImpl extends MybatisBaseDaoImpl<BaseRelatedResource,String> implements IBaseRelatedResourceDao  {

}
