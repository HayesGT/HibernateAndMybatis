package com.yxkong.system.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.utils.UUIDUtil;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.system.model.BaseRole;
import com.yxkong.system.service.IBaseRoleService;

@Controller
@RequestMapping("/baserole")
public class BaseRoleController extends SpringMybatisController<BaseRole,String> {
	private  final String PREFIX="baserole/";
    @Resource
	private IBaseRoleService baseRoleService;
	@Override
	protected IMybatisBaseService<BaseRole, String> getBaseService() {
		return baseRoleService;
	}

	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	
	@Override
	public Map<String,Object> save(@ModelAttribute BaseRole item) {
		Map<String,Object> map=new HashMap<String, Object>();
    	try {
			if(StringUtil.isNotEmpty(item.getId())){
				baseRoleService.update(item);
			}else{
				item.setCode(UUIDUtil.uuid());
				item.setCode(UUIDUtil.uuid());
				baseRoleService.save(item);
			}
			map.put("flag", "succ");
			map.put("msg", "操作成功！");
			map.put("id", item.getId());
		} catch (Exception e) {
			map.put("flag", "error");
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
    	return map;
	}
	@RequestMapping(value="roleTree")
	public String roleTree(){
		return PREFIX+"/roleTree";
	} 
}
