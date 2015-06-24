package com.yxkong.common.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxkong.common.model.BaseEntity;
import com.yxkong.common.service.IHibernateBaseService;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.system.constant.Constant;
import com.yxkong.system.web.vo.AuthenToken;

public abstract class SpringHibernateController<T extends BaseEntity,ID extends Serializable> extends BaseController {
	protected abstract IHibernateBaseService<T,ID> getBaseService();
	/**
	 * 功能说明：通用跳转到新增页面
	 * @author ducc
	 * @updated 
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	protected String add() {
		return ADD;
	}
	/**
	 * 功能说明：通用跳转到编辑页面
	 * @author ducc
	 * @updated 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable("id") ID id){
		model.addAttribute("item", getBaseService().get(id));
		return ADD;
	}
	@RequestMapping(value="/view/{id}")
	public String view(Model model,@PathVariable("id") ID id){
		model.addAttribute("item", getBaseService().get(id));
		return VIEW;
	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@ModelAttribute T item,HttpServletRequest request){
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
	/**
	 * 功能说明：通用删除功能
	 * @author ducc
	 * @updated 
	 * @param response HttpServletResponse
	 * @param id   删除的id
	 */
	@RequestMapping(value="/del/{id}")
	@ResponseBody
	public Map<String,Object> del(@PathVariable ID id){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(id!=null){
				getBaseService().deleteById(id);
				map.put("flag", "succ");
				map.put("msg", "删除成功！");
			}else{
				map.put("flag", "error");
				map.put("msg", "请不要恶意操作！");
			}
		} catch (Exception e) {
			map.put("flag", "error");
			map.put("msg", "删除失败！");
			e.printStackTrace();
		}
		return map;
	}
}
