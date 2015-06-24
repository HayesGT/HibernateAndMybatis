package com.yxkong.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yxkong.common.model.BaseEntity;

/**
 * 功能说明：用户实体
 * @author ducc
 * @created 2014年6月16日 上午6:01:02
 * @updated
 */
@Entity
@Table(name = "F_BaseUser")
public class BaseUser extends BaseEntity {
	private static final long serialVersionUID = -3622242509485780726L;
	/**用户登录名*/
	@JsonProperty
	@Column
	private String loginName;
	/**登录密码*/
	@JsonProperty
	@Column
	private String password;
	/**用户昵称*/
	@JsonProperty
	@Column
	private String nickName;
	/**用户邮箱*/
	@JsonProperty
	@Column
	private String email;
	/**性别(0女，1男)*/
	@JsonProperty
	@Column
	private Integer sex;
	/**年龄*/
	@JsonProperty
	@Column
	private Integer age;
	/**出生年月日*/
	@JsonProperty
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")//该注解将页面上上的字符串1988-01-01转换成date
	private Date birthday;
	/**身份证号*/
	@JsonProperty
	@Column
	private String idCard;
	/**手机号*/
	@JsonProperty
	@Column
	private String mobile;
	/**电话号码*/
	@JsonProperty
	@Column
	private String telPhone;
	/**省份*/
	@JsonProperty
	@Column
	private String province;
	/**市*/
	@JsonProperty
	@Column
	private String city;
	/**县*/
	@JsonProperty
	@Column
	private String county;
	/**地址*/
	@JsonProperty
	@Column
	private String address;
	/**上次登录时间*/
	@JsonProperty
	@Temporal(TemporalType.TIMESTAMP)
	private Date preLoginTime;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getPreLoginTime() {
		return preLoginTime;
	}
	public void setPreLoginTime(Date preLoginTime) {
		this.preLoginTime = preLoginTime;
	}
    
	
	
}
