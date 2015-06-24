package com.yxkong.common.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yxkong.common.model.IdEntity;
import com.yxkong.common.page.Page;
import com.yxkong.common.page.PageHelper;
import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.web.vo.PageBean;
/**
 * 功能说明：springmvc的基类，处理异常，公共方法
 * @author ducc
 * @created 2014年6月27日 上午6:19:14
 * @updated 
 * @param <T>
 */
public abstract class SpringMybatisController<T extends IdEntity,ID extends Serializable> extends BaseController {
	
	protected abstract IMybatisBaseService<T,String> getBaseService();
	
	/**
	 * 功能说明：通用列表查询方法，使用pager.ftl的处理页面分页
	 * @author ducc
	 * @updated 
	 * @param request
	 * @param model
	 * @param item
	 * @return
	 */
	@RequestMapping("list")
	protected String list(HttpServletRequest request,Model model,@ModelAttribute T item){
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
		List<T> list=getBaseService().findListBy(item);
		page.setTotal(((Page<T>) list).getTotal());
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		return LIST;
	} 
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
	public String edit(Model model,@PathVariable("id") String id){
		model.addAttribute("item", getBaseService().findById(id));
		return ADD;
	}
	@RequestMapping(value="/view/{id}")
	public String view(Model model,@PathVariable("id") String id){
		model.addAttribute("item", getBaseService().findById(id));
		return VIEW;
	}
	/**
	 * 功能说明：通用保存页面
	 * @author ducc
	 * @updated 
	 * @param response
	 * @param item 
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> save(@ModelAttribute T item){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(StringUtil.isNotEmpty(item.getId())){
				getBaseService().update(item);
			}else{
				getBaseService().save(item);
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
	/**
	 * 功能说明：通用删除功能
	 * @author ducc
	 * @updated 
	 * @param response HttpServletResponse
	 * @param id   删除的id
	 */
	@RequestMapping(value="/del/{id}")
	@ResponseBody
	public Map<String,Object> del(@PathVariable String id){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			if(StringUtil.isNotEmpty(id)){
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
	/**
	 * 功能说明：使用pager.ftl的处理页面分页
	 *   直接返回springmvc的model
	 * @author ducc
	 * @created 2014年6月28日 下午8:43:56
	 * @updated 
	 * @return
	 */
	protected Model executePage(Model model,T t){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
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
		List<T> list=getBaseService().findListBy(t);
		page.setTotal(((Page<T>) list).getTotal());
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		return model;
	}
	/**
	 * 功能说明：获取datatable传过来的数据,普通的get方法
	 * @author ducc
	 * @created 2014年6月29日 下午8:21:42
	 * @updated 
	 * @param t    查询的实体，需要的查询的值，通过实体传过来，由mybatis解析
	 * @param prefix 排序字段的前缀
	 * @return 封装datatable 需要的map数据，由@ResponseBody 转成对应的json
	 */
	protected Map<String,Object> ReturnDataTableGet(T t,String prefix){
		Map<String,Object> map=new HashMap<String, Object>();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String sEcho = request.getParameter("sEcho"); // 点击的次数
		String startNum = request.getParameter("iDisplayStart"); //从第几条数据开始检索
		String pageSizeStr = request.getParameter("iDisplayLength");//显示的行数
		String sortIndex=request.getParameter("iSortCol_0");//排序索引
		String search=request.getParameter("sSearch");//搜索值
		t.setSearchValue(search);
		String sort=request.getParameter("mDataProp_"+sortIndex);
		String dir=request.getParameter("sSortDir_0");//排序方式
		PageBean page = new PageBean();
		if(StringUtil.isNotEmpty(pageSizeStr)){
			page.setPageSize(Integer.parseInt(pageSizeStr));
		}
		if(StringUtil.isNotEmpty(startNum)){
			page.setIndex((Integer.parseInt(startNum)/page.getPageSize())+1);
		}
		
		if(StringUtil.isEmpty(sort)){
			sort="id";
		}
		if(StringUtil.isEmpty(dir)){
			dir="asc";
		}
		PageHelper.startPage(page.getIndex(), page.getPageSize());
        List<T> list=getBaseService().findListBy(t, sort, dir);
		page.setTotal(((Page<T>) list).getTotal());
		map.put("sEcho", sEcho);
		map.put("iTotalRecords", page.getTotal());
		map.put("iTotalDisplayRecords", page.getTotal());
		map.put("aaData", list);
		return map;
	}
	/**
	 * 功能说明：dataTable ajax post 请求数据的封装
	 * @author ducc
	 * @created 2014年7月4日 上午6:35:22
	 * @updated 
	 * @param t    查询的实体，需要的查询的值，通过实体传过来，由mybatis解析
	 * @param prefix 排序字段的前缀
	 * @return 封装datatable 需要的map数据，由@ResponseBody 转成对应的json
	 */
	protected Map<String,Object> ReturnDataTableAjaxPost(T t,String prefix){
		Map<String,Object> map=new HashMap<String, Object>();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String sEcho = request.getParameter("draw"); // 点击的次数
		String startNum = request.getParameter("start"); //从第几条数据开始检索
		String pageSizeStr = request.getParameter("length");//显示的行数
		String sortIndex=request.getParameter("order[0][column]");//排序索引
		String search=request.getParameter("search[value]");//搜索值
		t.setSearchValue(search);
		String sort=request.getParameter("columns["+sortIndex+"][data]");
		String dir=request.getParameter("order[0][dir]");//排序方式
		PageBean page = new PageBean();
		if(StringUtil.isNotEmpty(pageSizeStr)){
			page.setPageSize(Integer.parseInt(pageSizeStr));
		}
		if(StringUtil.isNotEmpty(startNum)){
			page.setIndex((Integer.parseInt(startNum)/page.getPageSize())+1);
		}
		
		if(StringUtil.isEmpty(sort)){
			sort="id";
		}
		if(StringUtil.isEmpty(dir)){
			dir="asc";
		}
		PageHelper.startPage(page.getIndex(), page.getPageSize());
        List<T> list=getBaseService().findListBy(t, sort, dir);
		page.setTotal(((Page<T>) list).getTotal());
		map.put("draw", sEcho);
		map.put("length",pageSizeStr);
		map.put("iTotalRecords", page.getTotal());
		map.put("iTotalDisplayRecords", page.getTotal());
		map.put("data", list);
		return map;
	}

}
