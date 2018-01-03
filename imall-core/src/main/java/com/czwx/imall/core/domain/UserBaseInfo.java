package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详细信息实体
 * 
 * @author jdd
 * @version 1.0.0
 * @date 2017-02-21 13:44:30 
 * Copyright 杭州融都科技股份有限公司 arc All Rights Reserved
 * 官方网站：www.erongdu.com 
 *  
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class UserBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;

	private Long bankId;

	private Long creditId;

	public Long getCreditId() {
		return creditId;
	}

	public void setCreditId(Long creditId) {
		this.creditId = creditId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	private String phone;

	private String realName;

	private Integer age;

	private Integer sex;

	private String nation;

	private String idNo;

	private String idAddr;

	private String livingImg;

	private String frontImg;

	private String backImg;

	private String workingImg;

	private String certificateNo;

	private String companyName;

	private String title;

	private String titleImg;

	private Date createTime;

	private Date updateTime;

	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo == null ? null : idNo.trim();
	}

	public String getIdAddr() {
		return idAddr;
	}

	public void setIdAddr(String idAddr) {
		this.idAddr = idAddr == null ? null : idAddr.trim();
	}

	public String getLivingImg() {
		return livingImg;
	}

	public void setLivingImg(String livingImg) {
		this.livingImg = livingImg == null ? null : livingImg.trim();
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg == null ? null : frontImg.trim();
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg == null ? null : backImg.trim();
	}

	public String getWorkingImg() {
		return workingImg;
	}

	public void setWorkingImg(String workingImg) {
		this.workingImg = workingImg == null ? null : workingImg.trim();
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo == null ? null : certificateNo.trim();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg == null ? null : titleImg.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}
}