package com.yxkong.webService.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.yxkong.system.model.BaseUser;
import com.yxkong.system.service.IBaseUserService;
import com.yxkong.webService.IBaseUserWsService;
@WebService(targetNamespace = "http://webservice.5ycode.com/", portName = "BaseUserWsServiceImplPort", serviceName = "baseUserWsService")
public class BaseUserWsServiceImpl implements IBaseUserWsService {

	@Resource
	private IBaseUserService baseUserService;
	@Override
	public String sayHello(@WebParam(name = "userName")String userName) {
		return userName+":您好！";
	}

	@Override
	public List<BaseUser> findUses() {
//		List<BaseUser> list=new ArrayList<BaseUser>();
//		BaseUser u=new BaseUser();
//		u.setLoginName("yxkong");
//		u.setNickName("鱼翔空");
//		u.setEmail("yuxiangkong@hotmail.com");
//		BaseUser u1=new BaseUser();
//		u1.setLoginName("ducc");
//		u1.setNickName("杜聪聪");
//		u1.setEmail("5ycode@outlook.com");
//		list.add(u);
//		list.add(u1);
//		return list;
		return baseUserService.findList();
	}

}
