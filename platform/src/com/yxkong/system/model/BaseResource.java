package com.yxkong.system.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yxkong.common.model.SimpleTreeBaseEntity;

/**
 * 功能说明：系统菜单，按钮资源表
 * @author ducc
 * @created 2014年6月14日 下午12:33:04
 * @updated
 */
@Entity
@Table(name = "F_BaseResource")
public class BaseResource extends SimpleTreeBaseEntity {
	private static final long serialVersionUID = -9153029988013767370L;
	/**1表示菜单，0表示按钮*/
	@Column   
	private Integer rescType;

	public Integer getRescType() {
		return rescType;
	}

	public void setRescType(Integer rescType) {
		this.rescType = rescType;
	}
	@Transient
	private List<BaseResource> childrens;

	public void addChildren(BaseResource resc){
		if(this.childrens==null){
			this.childrens=new ArrayList<BaseResource>();
			this.childrens.add(resc);
		}else{
			this.childrens.add(resc);
		}
	}
	public List<BaseResource> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<BaseResource> childrens) {
		this.childrens = childrens;
	}
	
	
}
