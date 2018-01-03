package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款计划实体
 */
public class OrderRepayPlan implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private Long orderId;

    private BigDecimal amount;

    private Date repayTime;

    private String state;

    private Integer extensionDay;

    private BigDecimal extensionAmount;

    private String extensionState;

    private Integer penaltyDay;

    private BigDecimal penaltyAmount;

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

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getExtensionDay() {
        return extensionDay;
    }

    public void setExtensionDay(Integer extensionDay) {
        this.extensionDay = extensionDay;
    }

    public BigDecimal getExtensionAmount() {
        return extensionAmount;
    }

    public void setExtensionAmount(BigDecimal extensionAmount) {
        this.extensionAmount = extensionAmount;
    }

    public String getExtensionState() {
        return extensionState;
    }

    public void setExtensionState(String extensionState) {
        this.extensionState = extensionState == null ? null : extensionState.trim();
    }

    public Integer getPenaltyDay() {
        return penaltyDay;
    }

    public void setPenaltyDay(Integer penaltyDay) {
        this.penaltyDay = penaltyDay;
    }

    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
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