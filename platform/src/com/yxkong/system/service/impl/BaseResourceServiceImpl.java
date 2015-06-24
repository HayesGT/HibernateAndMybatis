package com.yxkong.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisSimpleTreeServiceImpl;
import com.yxkong.common.utils.CollectionUtil;
import com.yxkong.common.web.vo.ZTree;
import com.yxkong.system.dao.IBaseResourceDao;
import com.yxkong.system.model.BaseResource;
import com.yxkong.system.service.IBaseResourceService;
@Service("baseResourceService")
public class BaseResourceServiceImpl extends MybatisSimpleTreeServiceImpl<BaseResource,String> implements IBaseResourceService {
	@Resource
	private IBaseResourceDao baseResourceDao;
	@Override
	protected IMybatisBaseDao<BaseResource, String> getBaseDao() {
		return baseResourceDao;
	}
	@Override
	public List<BaseResource> findListTree(BaseResource resc) {
		//这里可以做权限控制，看某个人有哪些权限
		resc.setStatus(1);
		List<BaseResource> list=baseResourceDao.findListBy(resc);
		//进行排序
		List<BaseResource> sortRescList = CollectionUtil.sortCollection(list, "sort", true);
		List<BaseResource> parent=new ArrayList<BaseResource>();
		for(BaseResource r:sortRescList){
			if(r.getTreeLevel()==1){
				for(BaseResource s:sortRescList){
					if(r.getId().equals(s.getpId())){
						r.addChildren(s);
					}
				}
				parent.add(r);
			}
		}
		return parent;
	}
	@SuppressWarnings("unused")
	@Override
	public List<ZTree> findRescTree() {
		try {
			List<Map<String,Object>> findTreeAll = findTreeAll(BaseResource.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	



}