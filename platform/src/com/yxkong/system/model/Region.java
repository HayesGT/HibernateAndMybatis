package com.yxkong.system.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxkong.common.model.IdEntity;
@Entity
@Table(name="F_Region")
public class Region extends IdEntity{
	private static final long serialVersionUID = 6738551537672088645L;
	/**地区名称*/
    private String name;
    /**父级地区编码*/
    private String parentId;
    @JsonProperty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
      

}
