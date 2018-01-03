package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户信息认证实体
 */
public class UserAuth implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private String certificateState;

    private String certificateImg;

    private Date createTime;

    private Date updateTime;

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

    public String getCertificateState() {
        return certificateState;
    }

    public void setCertificateState(String certificateState) {
        this.certificateState = certificateState == null ? null : certificateState.trim();
    }

    public String getCertificateImg() {
        return certificateImg;
    }

    public void setCertificateImg(String certificateImg) {
        this.certificateImg = certificateImg == null ? null : certificateImg.trim();
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
}