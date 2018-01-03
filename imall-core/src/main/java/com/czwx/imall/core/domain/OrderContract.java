package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同实体
 */
public class OrderContract implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private Long orderId;

    private String contractNo;

    private String contractHtmlUrl;

    private String contractPdfUrl;

    private Date signTime;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getContractHtmlUrl() {
        return contractHtmlUrl;
    }

    public void setContractHtmlUrl(String contractHtmlUrl) {
        this.contractHtmlUrl = contractHtmlUrl == null ? null : contractHtmlUrl.trim();
    }

    public String getContractPdfUrl() {
        return contractPdfUrl;
    }

    public void setContractPdfUrl(String contractPdfUrl) {
        this.contractPdfUrl = contractPdfUrl == null ? null : contractPdfUrl.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
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