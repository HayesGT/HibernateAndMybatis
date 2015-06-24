package com.yxkong.system.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxkong.common.service.IHibernateBaseService;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.web.controller.SpringHibernateController;
import com.yxkong.system.constant.Constant;
import com.yxkong.system.model.ThemeSetting;
import com.yxkong.system.service.IThemeSettingService;
import com.yxkong.system.web.vo.AuthenToken;
@Controller
@RequestMapping("themeSetting")
public class ThemeSettingController extends SpringHibernateController<ThemeSetting,String> {
	private static final String PREFIX="themeSetting/";
	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	@Resource(name="themeSettingService")
	private IThemeSettingService themeSettingService;
	@Override
	protected IHibernateBaseService<ThemeSetting, String> getBaseService() {
		return themeSettingService;
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@ModelAttribute ThemeSetting item,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			AuthenToken authenToken = (AuthenToken) request.getSession().getAttribute(Constant.AUTHENTOKEN);
			if(authenToken!=null){
				//保存时，
				if(StringUtil.isEmpty(item.getId())){
					item.setCreator(authenToken.getUserId());
				}else{
					item.setUpdator(authenToken.getUserId());
				}
				item.setUpdated(new Date());
			}
			getBaseService().saveorUpdate(item);
			if(authenToken!=null){
				authenToken.setThemeSetting(item);
			}
			map.put("flag", "succ");
			map.put("msg", "保存成功！");
			map.put("id", item.getId());
		} catch (Exception e) {
			map.put("flag", "error");
			map.put("msg", "保存失败！");
			e.printStackTrace();
		}
		return map;
	}

}
