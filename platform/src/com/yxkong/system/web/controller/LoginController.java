package com.yxkong.system.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.utils.StringUtil;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.system.constant.Constant;
import com.yxkong.system.model.BaseResource;
import com.yxkong.system.model.BaseUser;
import com.yxkong.system.model.ThemeSetting;
import com.yxkong.system.service.IBaseResourceService;
import com.yxkong.system.service.IBaseUserService;
import com.yxkong.system.service.IThemeSettingService;
import com.yxkong.system.web.vo.AuthenToken;

@Controller
@RequestMapping("/login")
public class LoginController extends SpringMybatisController<BaseUser,String> {
	private  final String PREXIF="/";
	@Resource
	private IBaseUserService baseUserService;
	@Resource(name="themeSettingService")
	private IThemeSettingService themeSettingService;
	@Override
	protected IMybatisBaseService<BaseUser, String> getBaseService() {
		return baseUserService;
	}
	@Resource(name="baseResourceService")
	private IBaseResourceService resourceService;
	@Override
	protected String getPrefix() {
		return PREXIF;
	}
	@RequestMapping("tologin")
	public String logion(){
		return "/login";
	}
	@RequestMapping("loginout")
	public String loginout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("AuthenToken");
		request.getSession().setMaxInactiveInterval(0);
		request.getSession().invalidate();
		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie:cookies){
				cookie.setValue(null);
				cookie.setMaxAge(0);  
	            cookie.setPath("/");//根据你创建cookie的路径进行填写      
	            response.addCookie(cookie);  
			}
		}
		return "/login";
	}
	/**
	 * 功能说明：系统登录
	 * @author ducc
	 * @created 2014年7月7日 上午9:12:15
	 * @updated 
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest req,HttpServletResponse res,@RequestParam("loginName") String loginName,@RequestParam("password") String password,@RequestParam(value="rememberPwd",required=false) String rememberPwd){
		Map<String, Object> map=new HashMap<String, Object>();
		BaseUser item=new BaseUser();
		item.setLoginName(loginName);
		item.setPassword(password);
		BaseUser user = baseUserService.findBy("loginBy", item);
		if(user!=null){
			//用户信息保存到session
			AuthenToken authenToken=new AuthenToken();
			//获取资源
			BaseResource resc=new BaseResource();
        	authenToken.setListResc(resourceService.findListTree(resc));
			authenToken.setNickName(user.getNickName());
			authenToken.setGender("女");
			if(user.getSex()==1){
				authenToken.setGender("男");
			}
			authenToken.setLoginTime(new Date());
			authenToken.setLoginName(user.getLoginName());
			authenToken.setUserId(user.getId());
			//获取用户主题配置
			ThemeSetting themeSetting = themeSettingService.get("8a48826d4b2aeab1014b2aed23530000");
			authenToken.setThemeSetting(themeSetting);
			req.getSession().setAttribute(Constant.AUTHENTOKEN, authenToken);
			if(StringUtil.isNotEmpty(rememberPwd)&&"yes".equals(rememberPwd)){
				//保存cookie一个月
				Cookie cookie = new Cookie("userId", user.getId());
				cookie.setMaxAge(30*24*60*60);
				cookie.setPath("/");
				res.addCookie(cookie);
		    }
			map.put("flag", "succ");
			map.put("msg", "登录成功！");
		}else{
			map.put("flag", "error");
			map.put("msg", "登录失败，请您重新登录！");
		}
		return map;
	}	
	
}
