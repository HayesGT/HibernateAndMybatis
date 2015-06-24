package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IRegionDao;
import com.yxkong.system.model.Region;
import com.yxkong.system.service.IRegionService;
@Service("regionService")
public class RegionServiceImpl extends MybatisBaseServiceImpl<Region,String> implements IRegionService {

	@Resource
	private IRegionDao regionDao;
	@Override
	protected IMybatisBaseDao<Region, String> getBaseDao() {
		return regionDao;
	}
	

}