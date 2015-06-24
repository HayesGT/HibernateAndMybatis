package com.yxkong.tencent.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.yxkong.common.service.IMybatisBaseService;
import com.yxkong.common.web.controller.SpringMybatisController;
import com.yxkong.system.model.BaseResource;
import com.yxkong.system.service.IBaseResourceService;
import com.yxkong.system.web.vo.AuthenToken;
import com.yxkong.tencent.model.QQUseInfo;
import com.yxkong.tencent.service.IQQUserInfoService;

@Controller
@RequestMapping("/qquser")
public class QQUserInfoController extends SpringMybatisController<QQUseInfo,String> {
	private  final String PREFIX="qquser/";
	@Resource(name="qQUserInfoService")
	private IQQUserInfoService qqUserInfoService;
	@Resource(name="baseResourceService")
	private IBaseResourceService resourceService;
	@Override
	protected IMybatisBaseService<QQUseInfo, String> getBaseService() {
		return qqUserInfoService;
	}
	@Override
	protected String getPrefix() {
		return PREFIX;
	}
	@RequestMapping("login")
	public void qqUser(HttpServletRequest req,HttpServletResponse res){
		try {
			res.sendRedirect(new Oauth().getAuthorizeURL(req));
		} catch (IOException e) {
			logger.error("qq 授权网络异常 ",e);
			e.printStackTrace();
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("loginCallBack")
	public String loginCallBack(HttpServletRequest req,HttpServletResponse res){
		try {
			AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(req);
			String accessToken   = null,
	               openID   = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
				//	  我们的网站被CSRF攻击了或者用户取消了授权
				//	 做一些数据统计工作
                System.out.print("没有获取到响应参数");
                return "redirect:/error";
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                req.getSession().setAttribute("access_token", accessToken);
                req.getSession().setAttribute("token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                QQUseInfo qui=new QQUseInfo();
                qui.setOpenId(openID);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end


                /*---------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 -----------------------*/
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                	qui.setNickname(userInfoBean.getNickname());
                	qui.setGender(userInfoBean.getGender());
                	qui.setLevel(userInfoBean.getLevel());
                	qui.setVip(userInfoBean.isVip());
                	qui.setYellowYearVip(userInfoBean.isYellowYearVip());
                	qui.setFigureurl30(userInfoBean.getAvatar().getAvatarURL30());
                	qui.setFigureurl50(userInfoBean.getAvatar().getAvatarURL50());
                	qui.setFigureurl100(userInfoBean.getAvatar().getAvatarURL100());
                	//根据openId 查询是否存在，不存在就保存，存在就更新
                	QQUseInfo item = qqUserInfoService.findBy("findByOpenId", openID);
                	if(item!=null){
                		qui.setId(item.getId());
                		qui.setCreated(item.getCreated());
                		qqUserInfoService.update(qui);
                	}else{
                		qqUserInfoService.save(qui);
                	}
                	AuthenToken authenToken=new AuthenToken();
                	authenToken.setNickName(qui.getNickname());
                	authenToken.setGender(qui.getGender());
                	authenToken.setOpenId(openID);
                	authenToken.setLoginTime(new Date());
                	req.getSession().setAttribute("AuthenToken", authenToken);
                	//如果该qq已经与某个用户绑定，则将该用户的id保存到session中
                	//获取系统资源
                	BaseResource resc=new BaseResource();
                	List<BaseResource> listResc = resourceService.findListTree(resc);//findListBy(resc);
                	req.getSession().setAttribute("listResc", listResc);
                	return "redirect:/welcome";
                } else {
                	logger.error("很抱歉，我们没能正确获取到您的信息，原因是："+userInfoBean.getMsg());
                	return "redirect:/error";
                }
            }
                /*------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ------------------------*/



//                out.println("<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>");
//                PageFans pageFansObj = new PageFans(accessToken, openID);
//                PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
//                if (pageFansBean.getRet() == 0) {
//                    out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是")  + "QQ空间97700000官方认证空间的粉丝</p>");
//                } else {
//                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + pageFansBean.getMsg());
//                }
//                out.println("<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>");
//
//
//
//                out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
//                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
//                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
//                if (weiboUserInfoBean.getRet() == 0) {
//                    //获取用户的微博头像----------------------start
//                    out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
//                    out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
//                    out.println("<image src=" + weiboUserInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
//                    //获取用户的微博头像 ---------------------end
//
//                    //获取用户的生日信息 --------------------start
//                    out.println("<p>尊敬的用户，你的生日是： " + weiboUserInfoBean.getBirthday().getYear()
//                                +  "年" + weiboUserInfoBean.getBirthday().getMonth() + "月" +
//                                weiboUserInfoBean.getBirthday().getDay() + "日");
//                    //获取用户的生日信息 --------------------end
//
//                    StringBuffer sb = new StringBuffer();
//                    sb.append("<p>所在地:" + weiboUserInfoBean.getCountryCode() + "-" + weiboUserInfoBean.getProvinceCode() + "-" + weiboUserInfoBean.getCityCode()
//                             + weiboUserInfoBean.getLocation());
//
//                    //获取用户的公司信息---------------------------start
//                    ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
//                    if (companies.size() > 0) {
//                        //有公司信息
//                        for (int i=0, j=companies.size(); i<j; i++) {
//                            sb.append("<p>曾服役过的公司：公司ID-" + companies.get(i).getID() + " 名称-" +
//                            companies.get(i).getCompanyName() + " 部门名称-" + companies.get(i).getDepartmentName() + " 开始工作年-" +
//                            companies.get(i).getBeginYear() + " 结束工作年-" + companies.get(i).getEndYear());
//                        }
//                    } else {
//                        //没有公司信息
//                    }
//                    //获取用户的公司信息---------------------------end
//
//                    out.println(sb.toString());
//
//                } else {
//                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + weiboUserInfoBean.getMsg());
//                }
//
//                out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>");
		} catch (QQConnectException e) {
			e.printStackTrace();
			return "";
		}
	}
	

}
