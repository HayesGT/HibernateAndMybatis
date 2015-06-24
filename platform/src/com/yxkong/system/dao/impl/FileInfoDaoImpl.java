package com.yxkong.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.yxkong.common.dao.impl.MybatisBaseDaoImpl;
import com.yxkong.system.dao.IFileInfoDao;
import com.yxkong.system.model.FileInfo;
@Repository("fileInfoDao")
public class FileInfoDaoImpl extends MybatisBaseDaoImpl<FileInfo,String> implements IFileInfoDao  {

}
