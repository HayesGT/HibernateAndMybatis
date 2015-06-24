package com.yxkong.common.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 功能说明：统一定义id的entity基类
 *     基类统一定义id的属性名称、数据类型、列名映射及生成策略
 *     子类可重载getId()函数重定义id的列名映射和生成策略
 *     本系统采用UUID主键策略
 * @author ducc
 * @created 2014年6月12日 下午3:06:02
 * @updated
 */
//JPA 基类的标识
/**
 * @JsonAutoDetect（作用在类上）来开启/禁止自动检测
 * 	 @fieldVisibility:字段的可见级别
		ANY:任何级别的字段都可以自动识别
		NONE:所有字段都不可以自动识别
		NON_PRIVATE:非private修饰的字段可以自动识别
		PROTECTED_AND_PUBLIC:被protected和public修饰的字段可以被自动识别
		PUBLIC_ONLY:只有被public修饰的字段才可以被自动识别
		DEFAULT:同PUBLIC_ONLY
 	@JsonIgnore
		作用在字段或方法上，用来完全忽略被注解的字段和方法对应的属性，即便这个字段或方法可以被自动检测到或者还有其他的注解
 */
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, creatorVisibility = Visibility.NONE)
@MappedSuperclass
public abstract class IdEntity implements Serializable {
	private static final long serialVersionUID = 7544925369672141340L;
	@Id
	@JsonProperty
	@DocumentId
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	protected String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 重写equals方法
	 * 
	 * @param obj
	 *            对象
	 * @return 是否相等
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!IdEntity.class.isAssignableFrom(obj.getClass())) {
			return false;
		}
		IdEntity other = (IdEntity) obj;
		return getId() != null ? getId().equals(other.getId()) : false;
	}

	/**
	 * 重写hashCode方法
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
	/**dataTable 全局搜索值，用户封装，不持久化到数据库*/
	@Transient
	private String searchValue;
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
