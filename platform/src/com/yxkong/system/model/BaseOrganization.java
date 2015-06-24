package com.yxkong.system.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yxkong.common.model.SimpleTreeBaseEntity;
/**
 * 功能说明：组织结构表
 * @author ducc
 * @created 2014年8月9日 上午6:31:53
 * @updated
 */
@Entity
@Table(name="F_BaseOrganization")
public class BaseOrganization extends SimpleTreeBaseEntity {
	
	private static final long serialVersionUID = 4809231353019422964L;
	@Transient
	protected String url;
	@Transient
	protected String icon;

	@Transient
	@Override
	public String getUrl() {
		return url;
	}
	@Transient
	@Override
	public void setUrl(String url) {
		this.url = url;
	}
	@Transient
	@Override
	public String getIcon() {
		return icon;
	}
	@Transient
	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
