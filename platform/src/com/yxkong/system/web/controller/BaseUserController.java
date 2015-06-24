package com.yxkong.system.web.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxkong.common.dao.SQLAdapter;
import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.system.model.BaseUser;
import com.yxkong.system.service.IBaseUserService;

@Controller
@RequestMapping("/baseuser")
public class BaseUserController extends SpringMybatisController<BaseUser,String> {
	private  final String PREFIX="baseuser/";
	@Resource
	private IBaseUserService baseUserService;
	@Override
	protected IMybatisBaseService<BaseUser, String> getBaseService() {
		return baseUserService;
	}
	@Override
	protected String getPrefix() {
		return PREFIX;
	} 
	@RequestMapping("toList")
	public String toList(){
	    return PREFIX+"toList";	
	}
	@RequestMapping(value="list1")
	public String list1(){
		return PREFIX+"list1";
	}
	@RequestMapping(value="listall")
	public String listall(Model model){
		SQLAdapter sqlAdapter=new SQLAdapter("select * from f_baseuser ");
		model.addAttribute("list", baseUserService.findListBySqlAdapter("findBySql",sqlAdapter));
		model.addAttribute("length", 13);
		return PREFIX+"listall";
	}
	@RequestMapping(value="listAjax")
	@ResponseBody
    public Map<String,Object> listAjax(HttpServletResponse response,HttpServletRequest request){
		BaseUser user=new BaseUser();
		return ReturnDataTableAjaxPost( user, null);
	}
	
}
