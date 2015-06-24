package com.yxkong.system.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.BaseEntity;
/**
 * 主题设置
 * @author yxkong
 *
 */
@Entity
@Table(name = "F_THEMESETTING")
public class ThemeSetting extends BaseEntity {
	private static final long serialVersionUID = 7628467723674872442L;
	//主题颜色
	private String themeColor;
	//主题样式
	private String themeStyle;
	//布局
	private String layout;
	//头部设置
	private String header;
	//顶部下拉菜单样式
	private String topMenuDropdown;
	//侧边模式
	private String sidebarMode;
	//侧边二级菜单显示方式
	private String sidebarMenu;
	//侧边样式
	private String sidebarStyle;
	//侧边位置
	private String sidebarPosition;
	//底部
	private String footer;
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	public String getThemeStyle() {
		return themeStyle;
	}
	public void setThemeStyle(String themeStyle) {
		this.themeStyle = themeStyle;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getTopMenuDropdown() {
		return topMenuDropdown;
	}
	public void setTopMenuDropdown(String topMenuDropdown) {
		this.topMenuDropdown = topMenuDropdown;
	}
	public String getSidebarMode() {
		return sidebarMode;
	}
	public void setSidebarMode(String sidebarMode) {
		this.sidebarMode = sidebarMode;
	}
	public String getSidebarMenu() {
		return sidebarMenu;
	}
	public void setSidebarMenu(String sidebarMenu) {
		this.sidebarMenu = sidebarMenu;
	}
	public String getSidebarStyle() {
		return sidebarStyle;
	}
	public void setSidebarStyle(String sidebarStyle) {
		this.sidebarStyle = sidebarStyle;
	}
	public String getSidebarPosition() {
		return sidebarPosition;
	}
	public void setSidebarPosition(String sidebarPosition) {
		this.sidebarPosition = sidebarPosition;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	
}
