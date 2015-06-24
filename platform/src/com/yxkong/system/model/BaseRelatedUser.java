package com.yxkong.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.BaseEntity;

/**
 * 能说明：用户关联表
 * @author ducc
 * @created 2014年6月16日 上午5:59:47
 * @updated
 */
@Entity
@Table(name = "F_BaseRelatedUser")
public class BaseRelatedUser extends BaseEntity {
	private static final long serialVersionUID = 4367435305721376488L;
	@Column
	private String roleId;
	@Column
	private String userId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
