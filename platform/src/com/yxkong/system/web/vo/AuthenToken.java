package com.yxkong.system.web.vo;

import java.util.Date;
import java.util.List;

import com.yxkong.system.model.BaseResource;
import com.yxkong.system.model.ThemeSetting;

public class AuthenToken {
	//登录用户id
	private String userId;
	//登录用户qq的openId
	private String openId;
	//登录名称
    private String loginName;
    //用户昵称
	private String nickName;
	//用户性别
	private String gender;
	//用户登录时间
	private Date loginTime;
	//用户上次登录时间
	private Date preLoginTime;
	//用户菜单
	private List<BaseResource> listResc;
	//主题设置
	private ThemeSetting themeSetting;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public ThemeSetting getThemeSetting() {
		return themeSetting;
	}
	public void setThemeSetting(ThemeSetting themeSetting) {
		this.themeSetting = themeSetting;
	}
	public Date getPreLoginTime() {
		return preLoginTime;
	}
	public void setPreLoginTime(Date preLoginTime) {
		this.preLoginTime = preLoginTime;
	}
	public List<BaseResource> getListResc() {
		return listResc;
	}
	public void setListResc(List<BaseResource> listResc) {
		this.listResc = listResc;
	}
	
	
}
