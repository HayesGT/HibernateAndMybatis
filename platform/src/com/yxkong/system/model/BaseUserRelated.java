package com.yxkong.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.BaseEntity;

/**
 * 功能说明：用户组织机构关联表
 * @author ducc
 * @updated
 */
@Entity
@Table(name = "F_BaseUserRelated")
public class BaseUserRelated extends BaseEntity {
	private static final long serialVersionUID = 2200237312900240415L;
	@Column
	private String userId;
    @Column
	private String orgId;
    /**是否主岗位，默认为0，主岗位为1*/
    @Column
	private Integer isMainPosition=0;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Integer getIsMainPosition() {
		return isMainPosition;
	}
	public void setIsMainPosition(Integer isMainPosition) {
		this.isMainPosition = isMainPosition;
	}
	
}
