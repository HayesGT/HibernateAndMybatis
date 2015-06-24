package com.yxkong.system.service;

import java.util.List;

import com.yxkong.common.service.IMybatisSimpleTreeService;
import com.yxkong.common.web.vo.ZTree;
import com.yxkong.system.model.BaseResource;


public interface IBaseResourceService extends IMybatisSimpleTreeService<BaseResource,String> {
    /**
     * 功能说明：获取左侧菜单
     * @author ducc
     * @created 2014年7月25日 上午11:36:48
     * @updated 
     * @param resc
     * @return
     */
	public List<BaseResource> findListTree(BaseResource resc);
	/**
	 * 功能说明：获取资源树
	 * @author ducc
	 * @created 2014年7月25日 上午11:36:34
	 * @updated 
	 * @return
	 */
    public List<ZTree> findRescTree();
}
