package com.yxkong.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisSimpleTreeServiceImpl;
import com.yxkong.common.web.vo.ZTree;
import com.yxkong.system.dao.IBaseOrganizationDao;
import com.yxkong.system.model.BaseOrganization;
import com.yxkong.system.service.IBaseOrganizationService;
@Service("baseOrganizationService")
public class BaseOrganizationServiceImpl extends MybatisSimpleTreeServiceImpl<BaseOrganization,String> implements IBaseOrganizationService {
	@Resource
	private IBaseOrganizationDao baseOrganizationDao;
	@Override
	protected IMybatisBaseDao<BaseOrganization, String> getBaseDao() {
		return baseOrganizationDao;
	}
	
	@Override
	public List<ZTree> findOrgTree() {
//		return zTreeDao.findListBy("com.yxkong.system.model.BaseOrganization", "findOrgTree", null);
		try {
			this.findTreeAll(BaseOrganization.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	



}