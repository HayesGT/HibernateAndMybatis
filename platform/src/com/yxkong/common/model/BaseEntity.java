package com.yxkong.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 功能说明：基础业务对象（必要的公共属性、逻辑删除数据的统一配置）
 * @author ducc
 * @created 2014年6月12日 下午3:10:07
 * @updated
 */
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
@MappedSuperclass
public abstract class BaseEntity  extends IdEntity  {
	
	private static final long serialVersionUID = -6194098095694242569L;
	/**ID"属性名称*/
	public static final String  ID_PROPERTY_NAME="id";
	/**创建人属性名称*/
	public static final String CREATOR_PROPERTY_NAME="creator";
	/**更新人属性名称*/
	public static final String UPDATOR_PROPERTY_NAME="updator";
	/**启用状态属性名称*/
	public static final String STATUS_PROPERTY_NAME="status";
	/**删除状态属性名称*/
	public static final String DELSTATUE_PROPERTY_NAME="delStatue";
	

	/** 信息录入者标识id */
	@JsonProperty
	@Field(store = Store.YES, index = Index.YES,analyze=Analyze.NO)
	@DateBridge(resolution = Resolution.SECOND)
	@Column(length=100)
	protected String creator;
	
	/**更新人标识id*/
	@Column(length=100)
	protected String updator;
	
	/**创建时间*/
	@JsonProperty
	@Field(store = Store.YES, index = Index.YES,analyze=Analyze.NO)
	@DateBridge(resolution = Resolution.SECOND)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	protected Date created =new Date();
	
	/**更新时间*/
	@JsonProperty
	@Field(store = Store.YES, index = Index.YES,analyze=Analyze.NO)
	@DateBridge(resolution = Resolution.SECOND)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;
	
	@JsonProperty
	@Field(store = Store.YES, index = Index.YES,analyze=Analyze.NO)
	@Min(0)
	@Column(columnDefinition="INT default 0")
	private Integer sort=1;
	
	@Column(length = 2000)
	protected String remark;
	
	 /** 实体的状态 1启用，0禁用 */
	@JsonProperty
    @Column(nullable = false, length = 1,columnDefinition="INT default 1")
    protected Integer status=1;
	
    /**删除标记1已删除 0未删除 默认0*/
	@JsonProperty
	@Column(length=1,columnDefinition="INT default 0")
	protected Integer delStatue=0;
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDelStatue() {
		return delStatue;
	}
	public void setDelStatue(Integer delStatue) {
		this.delStatue = delStatue;
	}
	/**
	 * 实现compareTo方法
	 * 
	 * @param orderEntity
	 *            排序对象
	 * @return 比较结果
	 */
	public int compareTo(BaseEntity baseEntity) {
		return new CompareToBuilder().append(getSort(), baseEntity.getSort()).append(getId(), baseEntity.getSort()).toComparison();
	}
}
