package com.yxkong.system.service;

import java.util.List;

import com.yxkong.common.service.IMybatisSimpleTreeService;
import com.yxkong.common.web.vo.ZTree;
import com.yxkong.system.model.BaseOrganization;


public interface IBaseOrganizationService extends IMybatisSimpleTreeService<BaseOrganization,String> {
   
	/**
	 * 功能说明：获取资源树
	 * @author ducc
	 * @created 2014年7月25日 上午11:36:34
	 * @updated 
	 * @return
	 */
    public List<ZTree> findOrgTree();
}
