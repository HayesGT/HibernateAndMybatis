package com.yxkong.tencent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.IdEntity;
import com.yxkong.common.model.BaseEntity;
@Table
@Entity(name="F_QQUSEINFO")
public class QQUseInfo extends BaseEntity {
	//用户openId用来唯一标识用户
	@Column
	private String openId;
	
    //用户在QQ空间的昵称
	@Column
    private String nickname;
	
	//性别。 如果获取不到则默认返回"男"
	@Column
    private String gender;
	
    //标识用户是否为黄钻用户（0：不是；1：是）
	@Column
    private boolean vip;
	@Column
	private boolean yellowYearVip;
	
    //黄钻等级
	@Column
    private int level;
	
    //大小为30×30像素的QQ空间头像URL
	@Column
    private String figureurl30;
	
    //大小为50×50像素的QQ空间头像URL
	@Column
    private String figureurl50;
	
    //大小为100×100像素的QQ空间头像URL
	@Column
    private String figureurl100;
    //关联系统用户id
    private String userId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
	public boolean isYellowYearVip() {
		return yellowYearVip;
	}
	public void setYellowYearVip(boolean yellowYearVip) {
		this.yellowYearVip = yellowYearVip;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getFigureurl30() {
		return figureurl30;
	}
	public void setFigureurl30(String figureurl30) {
		this.figureurl30 = figureurl30;
	}
	public String getFigureurl50() {
		return figureurl50;
	}
	public void setFigureurl50(String figureurl50) {
		this.figureurl50 = figureurl50;
	}
	public String getFigureurl100() {
		return figureurl100;
	}
	public void setFigureurl100(String figureurl100) {
		this.figureurl100 = figureurl100;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
    
}
