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
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.common.web.vo.PageBean;
import com.yxkong.system.model.BaseOrganization;
import com.yxkong.system.model.BaseUser;
import com.yxkong.system.service.IBaseOrganizationService;
import com.yxkong.system.service.IBaseUserService;

@Controller
@RequestMapping("baseorg")
public class BaseOrganizationController extends SpringMybatisController<BaseOrganization,String> {
	private static final String PREFIX="baseorg/";
    @Resource
	private IBaseOrganizationService baseOrganizationService;
    @Resource
    private IBaseUserService baseUserService;
	@Override
	protected IMybatisBaseService<BaseOrganization, String> getBaseService() {
		return baseOrganizationService;
	}
	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	@Override
	@RequestMapping("list")
	protected String list(HttpServletRequest request,Model model,@ModelAttribute BaseOrganization item){
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
		List<BaseOrganization> list=getBaseService().findListBy(item);
		page.setTotal(((Page<BaseOrganization>) list).getTotal());
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		return PREFIX+"list";
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
	public List<BaseOrganization> findListByPid(@RequestParam("pId") String pId){
		BaseOrganization item=new BaseOrganization();
		item.setpId(pId);
		item.setStatus(null);
		return baseOrganizationService.findListBy(item);
	}
	@RequestMapping("add/{pId}")
    public String add(Model model,@PathVariable String pId){
    	if(StringUtil.isEmpty(pId)||"undefined".equals(pId)){
    		pId="0";
    	}
    	BaseOrganization item=new BaseOrganization();
    	item.setpId(pId);
    	try {
			model.addAttribute("parents", baseOrganizationService.findSiblingsById(pId, BaseOrganization.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	model.addAttribute("item", item);
    	return PREFIX+"add";
    }
    @RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable("id") String id){
    	try {
			model.addAttribute("parents", baseOrganizationService.findParentSiblingsById(id, BaseOrganization.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("item", getBaseService().findById(id));
		return PREFIX+"add";
	}
    @Override
    @RequestMapping("save")
    @ResponseBody
    public Map<String,Object> save(@ModelAttribute BaseOrganization item){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
			if(StringUtil.isNotEmpty(item.getId())){
				baseOrganizationService.update(item);
			}else{
				baseOrganizationService.saveTreeNode(item);
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
    @RequestMapping("findOrgTree/{id}")
    public String findOrgTree(Model model,@PathVariable("id") String id){
    	List<Map<String, Object>> rescTree;
		try {
			if(StringUtil.isEmpty(id)){
				id="0";
			}
			List<Map<String,Object>> findChildsBypId = baseOrganizationService.findChildsBypId(id, BaseOrganization.class);
			model.addAttribute("list", findChildsBypId);
			rescTree = baseOrganizationService.findTreeAll(BaseOrganization.class);
			model.addAttribute("jsonData",GsonUtil.toJson(rescTree));
			model.addAttribute("pId", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return PREFIX+"orgTree";
    }
    @RequestMapping("orgTreeWithUser/{id}")
    public String orgTreeWithUser(Model model,@PathVariable("id") String id){
    	List<Map<String, Object>> rescTree;
    	try {
    		if(StringUtil.isEmpty(id)){
    			id="0";
    		}
    		Map<String,Object> item=new HashMap<String,Object>();
    		item.put("orgId", id);
    		List<BaseUser> users = baseUserService.findListByMap("findUsersByOrgId",item);
    		model.addAttribute("list", users);
    		rescTree = baseOrganizationService.findTreeAll(BaseOrganization.class);
    		model.addAttribute("jsonData",GsonUtil.toJson(rescTree));
    		model.addAttribute("pId", id);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return PREFIX+"orgTreeWithUser";
    }
    @RequestMapping("deleteRecursiveById")
    public void deleteRecursiveById(HttpServletResponse response,@RequestParam("id") String id){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try {
    		baseOrganizationService.deleteRecursiveById(id, BaseOrganization.class);
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
    		baseOrganizationService.updateStatus(id, status, BaseOrganization.class);
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
