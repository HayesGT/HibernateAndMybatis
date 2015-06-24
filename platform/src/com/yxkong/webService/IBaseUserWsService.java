package com.yxkong.webService;

import java.util.List;

import javax.jws.WebService;

import com.yxkong.system.model.BaseUser;


@WebService
public interface IBaseUserWsService {

	public String sayHello(String userName);
	public List<BaseUser> findUses();
	
}
