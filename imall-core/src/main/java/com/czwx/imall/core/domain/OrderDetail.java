package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单明细实体
 */
public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private Long orderId;

    private BigDecimal amount;

    private BigDecimal penaltyAmountRatio;

    private Integer feeLevel;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPenaltyAmountRatio() {
        return penaltyAmountRatio;
    }

    public void setPenaltyAmountRatio(BigDecimal penaltyAmountRatio) {
        this.penaltyAmountRatio = penaltyAmountRatio;
    }

    public Integer getFeeLevel() {
        return feeLevel;
    }

    public void setFeeLevel(Integer feeLevel) {
        this.feeLevel = feeLevel;
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