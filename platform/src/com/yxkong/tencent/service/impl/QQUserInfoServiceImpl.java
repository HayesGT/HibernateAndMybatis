package com.yxkong.tencent.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.tencent.dao.IQQUserInfoDao;
import com.yxkong.tencent.model.QQUseInfo;
import com.yxkong.tencent.service.IQQUserInfoService;
@Service("qQUserInfoService")
public class QQUserInfoServiceImpl extends MybatisBaseServiceImpl<QQUseInfo,String> implements IQQUserInfoService {

	@Resource(name="qQUserInfoDao")
	private IQQUserInfoDao qQuserInfoDao;
	@Override
	protected IMybatisBaseDao<QQUseInfo, String> getBaseDao() {
		return qQuserInfoDao;
	}
	

}