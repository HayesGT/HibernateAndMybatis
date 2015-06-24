package com.yxkong.tencent.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.tencent.dao.IQQUserInfoDao;
import com.yxkong.tencent.model.QQUseInfo;
@Repository("qQUserInfoDao")
public class QQUserInfoDaoImpl extends MybatisBaseDaoImpl<QQUseInfo,String> implements IQQUserInfoDao  {

}
