package com.yxkong.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yxkong.common.model.BaseEntity;

/**
 * 能说明：角色实体
 * @author ducc
 * @created 2014年6月16日 上午5:59:47
 * @updated
 */
@Entity
@Table(name = "F_BaseRole")
public class BaseRole extends BaseEntity {
	private static final long serialVersionUID = 5387837351736703975L;
	/**角色名称*/
	@Column
	private String name;
	/**角色编码*/
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
