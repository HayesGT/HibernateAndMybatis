package com.yxkong.common.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
/**
 * 功能说明：简单树基类
 * @author ducc
 * @created 2014年6月14日 下午1:03:33
 * @updated
 */
@MappedSuperclass
public abstract class SimpleTreeBaseEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**节点名称*/
	@Column
	protected String name;
	/**节点名称code*/
	@Column
	protected String code;
	/**
	 * 节点依赖值，代表当前成绩下的节点增加过的个数，删除某个节点该值不会减，是为了生成唯一的code
	 */
	@Column Integer treeDepend;
	
    /**节点链接的目标 URL*/
	@Column
	protected String url;
	@Column
	protected String icon;
    /**节点的父级id
     * 第一层节点为"0"
     * */
	@Column
	protected String pId;
    /**节点的层级,默认为1*/
	@Column
	protected Integer treeLevel;
	/**树的左右值*/
	@Column
	protected Integer leftValue;
	@Column
	protected Integer rightValue;
	/**全路径*/
	@Column(length=500)
	private String parent;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	
	public Integer getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}
	public Integer getLeftValue() {
		return leftValue;
	}
	public void setLeftValue(Integer leftValue) {
		this.leftValue = leftValue;
	}
	public Integer getRightValue() {
		return rightValue;
	}
	public void setRightValue(Integer rightValue) {
		this.rightValue = rightValue;
	}
	public Integer getTreeDepend() {
		return treeDepend;
	}
	public void setTreeDepend(Integer treeDepend) {
		this.treeDepend = treeDepend;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}   
	   
}
