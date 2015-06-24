package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IRegionDao;
import com.yxkong.system.model.Region;
@Repository("regionDao")
public class RegionDaoImpl extends MybatisBaseDaoImpl<Region,String> implements IRegionDao  {

}
