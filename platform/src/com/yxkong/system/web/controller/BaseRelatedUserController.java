package com.yxkong.system.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.system.model.BaseRelatedUser;
import com.yxkong.system.service.IBaseRelatedUserService;

@Controller
@RequestMapping("/baserelateduser")
public class BaseRelatedUserController extends SpringMybatisController<BaseRelatedUser,String> {
	private  final String PREFIX="baserelateduser/";
    @Resource(name="")
	private IBaseRelatedUserService baseRelatedUserService;
	@Override
	protected IMybatisBaseService<BaseRelatedUser, String> getBaseService() {
		return baseRelatedUserService;
	}

	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	@RequestMapping("saveRelated")
	public void saveRelated(HttpServletResponse response,@RequestParam("uIds")String[] uIds,@RequestParam("roleId") String roleId){
		if(uIds!=null&&uIds.length>0){
			List<BaseRelatedUser> list=new ArrayList<BaseRelatedUser>();
			BaseRelatedUser u=null;
			for(String uId:uIds){
				u=new BaseRelatedUser();
				u.setUserId(uId);
				u.setRoleId(roleId);
				list.add(u);
			}
			if(!list.isEmpty()){
				baseRelatedUserService.batchSave(list);
			}
		}
	}
	
}
