package com.yxkong.system.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.system.model.Region;
import com.yxkong.system.service.IRegionService;

@Controller
@RequestMapping("/region")
public class RegionController extends SpringMybatisController<Region,String> {
	private  final String PREFIX="region/";
	@Resource
	private IRegionService regionService;
	@Override
	protected IMybatisBaseService<Region, String> getBaseService() {
		return regionService;
	}
	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	@RequestMapping(value="list/{pId}",method=RequestMethod.POST)
	public @ResponseBody List<Region> list(HttpServletResponse response,@PathVariable String pId){
		if(StringUtil.isEmpty(pId)){
			pId="0";
		}
		Region item=new Region();
		item.setParentId(pId);
		return regionService.findListBy(item);
	}
	@RequestMapping(value="/listByIds",method=RequestMethod.POST)
	public @ResponseBody List<Region> listByIds(HttpServletResponse response,@RequestParam("ids") String[] ids){
		return regionService.selectList("findListByIds", ids);
		
	}
	@RequestMapping(value="listall")
	public String listAll(Model model,@ModelAttribute Region item){
		logger.error("调用了列表方法");
		List<Region> findList = regionService.findList();
		model.addAttribute("item", item);
		model.addAttribute("list",findList);
		return "region/listall";
	}
	@RequestMapping(value="list1")
	public String list1(){
		return "region/list1";
	}
	@RequestMapping(value="list2")
	public String list2(){
		return "region/list2";
	}
	/**
	 * 功能说明：datable ajax get获取数据
	 * @author ducc
	 * @created 2014年7月12日 下午8:23:57
	 * @updated 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="listAjax")
	@ResponseBody
	public Map<String,Object> listAjax(HttpServletResponse response,HttpServletRequest request){
		logger.error("调用了列表方法");
		Region region=new Region();
		return ReturnDataTableGet(region,null);
	}
	/**
	 * 功能说明：datatable ajax post 获取数据
	 * @author ducc
	 * @created 2014年7月12日 下午8:23:10
	 * @updated 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="listAjax1")
	@ResponseBody
	public Map<String,Object> listAjax1(HttpServletResponse response,HttpServletRequest request ){
		logger.error("调用了列表方法");
		Region region=new Region();
		return ReturnDataTableAjaxPost( region, null);
	}
	

}
