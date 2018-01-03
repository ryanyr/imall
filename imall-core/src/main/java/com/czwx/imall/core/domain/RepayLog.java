package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款记录实体
 */
public class RepayLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private Long orderId;

    private Long planId;

    private BigDecimal amount;

    private Integer penaltyDay;

    private BigDecimal penaltyAmount;

    private BigDecimal extensionAmount;

    private String repayWay;

    private String repayAccount;

    private String serialNumber;

    private BigDecimal remitAmount;

    private String remitRemark;

    private BigDecimal couponAmount;

    private String couponNo;

    private String repayVoucher;

    private Date payTime;

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

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public BigDecimal getExtensionAmount() {
        return extensionAmount;
    }

    public void setExtensionAmount(BigDecimal extensionAmount) {
        this.extensionAmount = extensionAmount;
    }

    public String getRepayWay() {
        return repayWay;
    }

    public void setRepayWay(String repayWay) {
        this.repayWay = repayWay == null ? null : repayWay.trim();
    }

    public String getRepayAccount() {
        return repayAccount;
    }

    public void setRepayAccount(String repayAccount) {
        this.repayAccount = repayAccount == null ? null : repayAccount.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public BigDecimal getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(BigDecimal remitAmount) {
        this.remitAmount = remitAmount;
    }

    public String getRemitRemark() {
        return remitRemark;
    }

    public void setRemitRemark(String remitRemark) {
        this.remitRemark = remitRemark == null ? null : remitRemark.trim();
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getRepayVoucher() {
        return repayVoucher;
    }

    public void setRepayVoucher(String repayVoucher) {
        this.repayVoucher = repayVoucher;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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