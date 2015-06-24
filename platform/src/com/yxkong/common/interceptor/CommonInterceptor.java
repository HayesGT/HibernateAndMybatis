package com.yxkong.common.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yxkong.common.utils.StringUtil;
import com.yxkong.system.model.BaseResource;
import com.yxkong.system.model.BaseUser;
import com.yxkong.system.service.IBaseResourceService;
import com.yxkong.system.service.IBaseUserService;
import com.yxkong.system.web.vo.AuthenToken;

public class CommonInterceptor implements HandlerInterceptor {
	@Resource
	private IBaseUserService baseUserService;
	@Resource(name="baseResourceService")
	private IBaseResourceService resourceService;
	 /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 自动登录拦截实现
		 *    1),获取用户的session中的AuthenToken
		 *        存在：不做任何操作
		 *   不存在：
		 *    2),获取Cookie中的用户ID，存在，获取该用户的详细信息，保存到session
		 *   Cookie不存在
		 *    3),获取当前访问url
		 *    4),获取web.xml中放行的地址
		 *    5),如果访问的url不是放行的地址，跳转到登录页面
		 */
//		String parentPath=request.getContextPath();
//		HttpSession session=request.getSession();
//		AuthenToken authenToken=(AuthenToken) session.getAttribute("AuthenToken");
//		if(null==authenToken){
//			Cookie[] cookies=request.getCookies();
//			if(cookies!=null&&cookies.length>0){
//				for (Cookie cookie : cookies) {
//					if ("userId".equals(cookie.getName())) {
//						String userId = cookie.getValue();
//						BaseUser user = baseUserService.findById(userId);
//						if(user!=null){
//							authenToken=new AuthenToken();
//							authenToken.setNickName(user.getNickName());
//							authenToken.setGender("女");
//							if(user.getSex()==1){
//								authenToken.setGender("男");
//							}
//							authenToken.setLoginTime(new Date());
//							authenToken.setLoginName(user.getLoginName());
//							authenToken.setUserId(userId);
//							session.setAttribute("AuthenToken", authenToken);
//							//获取资源
//							BaseResource resc=new BaseResource();
//				        	List<BaseResource> listResc = resourceService.findListTree(resc);
//				        	session.setAttribute("listResc", listResc);
//							
//							return true;
//						}
//					}
//				}
//				/**
//				 * 如果以前登录没有选择记住密码
//				 *   如果要访问的地址不是要放行的方法，那么拦截跳转到登录页面
//				 *   是要放行的方法，放行
//				 */
//				String path = request.getRequestURI();
//				path = path.substring(path.lastIndexOf("/"));
//				String noLoginUrl = request.getSession().getServletContext().getInitParameter("noLoginUrl");
//				if(StringUtil.isNotEmpty(noLoginUrl)){
//					String[] noLoginUrlArr = noLoginUrl.split(",");
//					List<String> list = Arrays.asList(noLoginUrlArr);
//					if(list!=null&&list.contains(path)){
//						return true;
//					}
//				}
//				
//			}
//			response.sendRedirect(parentPath+"/tologin.htm");
//			return false;
//		}
		return true;
	}
	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	/** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */ 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
