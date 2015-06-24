package com.yxkong.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yxkong.common.dao.IMybatisBaseDao;
import com.yxkong.common.service.impl.MybatisBaseServiceImpl;
import com.yxkong.system.dao.IFileInfoDao;
import com.yxkong.system.model.FileInfo;
import com.yxkong.system.service.IFileInfoService;
@Service("fileInfoService")
public class FileInfoServiceImpl extends MybatisBaseServiceImpl<FileInfo,String> implements IFileInfoService {
	
	@Resource
	private IFileInfoDao fileInfoDao;
	@Override
	protected IMybatisBaseDao<FileInfo, String> getBaseDao() {
		return fileInfoDao;
	}

}