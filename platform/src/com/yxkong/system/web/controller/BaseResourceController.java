package com.yxkong.system.web.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxkong.common.page.Page;
import com.yxkong.common.page.PageHelper;
import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.utils.GsonUtil;
import com.yxkong.common.utils.SpringMvcUtil;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.utils.UUIDUtil;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.common.web.vo.PageBean;
import com.yxkong.system.model.BaseResource;
import com.yxkong.system.service.IBaseResourceService;

@Controller
@RequestMapping("baseresc")
public class BaseResourceController extends SpringMybatisController<BaseResource,String> {
	private static final String PREFIX="baseresc/";
    @Resource
	private IBaseResourceService baseResourceService;
	@Override
	protected IMybatisBaseService<BaseResource, String> getBaseService() {
		return baseResourceService;
	}
	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	@RequestMapping("list1")
	protected String list1(HttpServletRequest request,Model model,@ModelAttribute BaseResource item){
		String indexObj = request.getParameter("page.index");
		String pageSizeObj = request.getParameter("page.pageSize");
		String pageTotal=request.getParameter("page.total");
		PageBean page=new PageBean();
		if(StringUtil.isNotEmpty(indexObj)){
			page.setIndex(Integer.parseInt((indexObj)));
		}
		if(StringUtil.isNotEmpty(pageSizeObj)){
			page.setPageSize(Integer.parseInt(pageSizeObj));
		}
		if(StringUtil.isNotEmpty(pageTotal)){
			page.setTotal(Integer.parseInt(pageTotal));
		}
		PageHelper.startPage(page.getIndex(), page.getPageSize());
		item.setTreeLevel(1);
		item.setStatus(null);
		List<BaseResource> list=getBaseService().findListBy(item);
		page.setTotal(((Page<BaseResource>) list).getTotal());
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		return PREFIX+"list1";
	} 
	/**
	 * 功能说明：根据pId异步加载子节点
	 * @author ducc
	 * @created 2014年8月2日 上午10:28:12
	 * @updated 
	 * @param pid
	 * @return
	 */
	@RequestMapping("findListByPid")
	@ResponseBody
	public List<BaseResource> findListByPid(@RequestParam("pId") String pId){
		BaseResource item=new BaseResource();
		item.setpId(pId);
		item.setStatus(null);
		return baseResourceService.findListBy(item);
	}
    @RequestMapping("add/{pId}")
    public String add(Model model,@PathVariable String pId){
    	if(StringUtil.isEmpty(pId)||"undefined".equals(pId)){
    		pId="0";
    	}
    	BaseResource item=new BaseResource();
    	
    	item.setpId(pId);
    	model.addAttribute("item", item);
    	return PREFIX+"add";
    }
    @RequestMapping("add1/{pId}")
    public String add1(Model model,@PathVariable String pId){
    	if(StringUtil.isEmpty(pId)||"undefined".equals(pId)){
    		pId="0";
    	}
    	BaseResource item=new BaseResource();
    	item.setpId(pId);
    	try {
			model.addAttribute("parents", baseResourceService.findSiblingsById(pId, BaseResource.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.addAttribute("item", item);
    	return PREFIX+"add1";
    }
    @RequestMapping(value="/edit1/{id}")
	public String edit1(Model model,@PathVariable("id") String id){
    	try {
			model.addAttribute("parents", baseResourceService.findParentSiblingsById(id, BaseResource.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("item", getBaseService().findById(id));
		return PREFIX+"add1";
	}
    @Override
    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(@ModelAttribute BaseResource item){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
			if(StringUtil.isNotEmpty(item.getId())){
				baseResourceService.update(item);
			}else{
				item.setCode(UUIDUtil.uuid());
				baseResourceService.saveTreeNode(item);
//				baseResourceService.save(item);
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
    @RequestMapping("findRescTree")
    public String findRescTree(Model model){
    	List<Map<String, Object>> rescTree;
		try {
			rescTree = baseResourceService.findTreeAll(BaseResource.class);
			model.addAttribute("jsonData",GsonUtil.toJson(rescTree));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return PREFIX+"rescTree";
    }
    @RequestMapping("deleteRecursiveById")
    public void deleteRecursiveById(HttpServletResponse response,@RequestParam("id") String id){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
    		baseResourceService.deleteRecursiveById(id, BaseResource.class);
			map.put("flag", "succ");
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			logger.error(id+" deleteRecursiveById is error",e);
			map.put("flag", "error");
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
    	SpringMvcUtil.responseJSONWriter(response, map);
    }
    @RequestMapping("changeStatus")
    public void changeStatus(HttpServletResponse response,@RequestParam("id")String id,@RequestParam("status") Integer status){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
    		if(status==null){
    			status=0;
    		}else{
    			if(status==1){
    				status=0;
    			}else{
    				status=1;
    			}
    		}
    		baseResourceService.updateStatus(id, status, BaseResource.class);
			map.put("flag", "succ");
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			logger.error(id+" deleteRecursiveById is error",e);
			map.put("flag", "error");
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
    	SpringMvcUtil.responseJSONWriter(response, map);
    }
	
}
