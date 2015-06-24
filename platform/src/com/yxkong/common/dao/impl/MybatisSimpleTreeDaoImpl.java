package com.yxkong.common.dao.impl;

import java.io.Serializable;

import com.yxkong.common.dao.IMybatisSimpleTreeDao;
import com.yxkong.common.model.SimpleTreeBaseEntity;
public abstract  class MybatisSimpleTreeDaoImpl<T extends SimpleTreeBaseEntity,ID extends Serializable >  extends MybatisBaseDaoImpl<T, ID> implements IMybatisSimpleTreeDao<T,ID> {

	
}
