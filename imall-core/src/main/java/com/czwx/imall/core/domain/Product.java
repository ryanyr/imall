package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品实体
 */
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private String prodNo;

    private String prodName;

    private BigDecimal penaltyAmountRatio;

    private Integer feeLevelCount;

    private BigDecimal feeLevelAmtStart;

    private BigDecimal feeLevelAmt;

    private BigDecimal feeAmountStart;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo == null ? null : prodNo.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public BigDecimal getPenaltyAmountRatio() {
        return penaltyAmountRatio;
    }

    public void setPenaltyAmountRatio(BigDecimal penaltyAmountRatio) {
        this.penaltyAmountRatio = penaltyAmountRatio;
    }

    public Integer getFeeLevelCount() {
        return feeLevelCount;
    }

    public void setFeeLevelCount(Integer feeLevelCount) {
        this.feeLevelCount = feeLevelCount;
    }

    public BigDecimal getFeeLevelAmtStart() {
        return feeLevelAmtStart;
    }

    public void setFeeLevelAmtStart(BigDecimal feeLevelAmtStart) {
        this.feeLevelAmtStart = feeLevelAmtStart;
    }

    public BigDecimal getFeeLevelAmt() {
        return feeLevelAmt;
    }

    public void setFeeLevelAmt(BigDecimal feeLevelAmt) {
        this.feeLevelAmt = feeLevelAmt;
    }

    public BigDecimal getFeeAmountStart() {
        return feeAmountStart;
    }

    public void setFeeAmountStart(BigDecimal feeAmountStart) {
        this.feeAmountStart = feeAmountStart;
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