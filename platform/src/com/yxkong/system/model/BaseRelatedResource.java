package com.yxkong.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.BaseEntity;

/**
 * 功能说明：关联资源
 * @author ducc
 * @created 2014年6月16日 上午6:13:46
 * @updated
 */
@Entity
@Table(name = "F_BaseRelatedResource")
public class BaseRelatedResource extends BaseEntity {
	private static final long serialVersionUID = -7991523097870219616L;
	/**关联id，（用户，角色）*/
	@Column
	private String relatedId;
	/**资源id*/
	@Column
	private String resourceId;
	/**关联类型（角色，用户）*/
	@Column
	private Integer relatedType;
	public String getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getRelatedType() {
		return relatedType;
	}
	public void setRelatedType(Integer relatedType) {
		this.relatedType = relatedType;
	}
	
	
}
