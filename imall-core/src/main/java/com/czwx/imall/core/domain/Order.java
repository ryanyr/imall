package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体
 */
public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private Long userId;

    private String orderNo;

    private BigDecimal amount;

    private BigDecimal realAmount;

    private Integer timeLimit;

    private BigDecimal fee;

    private BigDecimal serviceFee;

    private BigDecimal interest;

    private String state;

    private BigDecimal policyAmount;

    private String insuranceCompany;

    private String policyImg1;

    private String policyImg2;

    private String policyImg3;

    private String policyImg4;

    private String policyImg5;

    private String policyHolder;

    private String client;

    private String address;

    private Integer channelId;

    private String ip;

    private String remark;

    private Date createTime;

    private Date loanTime;

    private Date repayTime;

    private Date updateTime;
	
	private String policyImg;

	private String borrowType;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public BigDecimal getPolicyAmount() {
        return policyAmount;
    }

    public void setPolicyAmount(BigDecimal policyAmount) {
        this.policyAmount = policyAmount;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany == null ? null : insuranceCompany.trim();
    }

    public String getPolicyImg1() {
        return policyImg1;
    }

    public void setPolicyImg1(String policyImg1) {
        this.policyImg1 = policyImg1 == null ? null : policyImg1.trim();
    }

    public String getPolicyImg2() {
        return policyImg2;
    }

    public void setPolicyImg2(String policyImg2) {
        this.policyImg2 = policyImg2 == null ? null : policyImg2.trim();
    }

    public String getPolicyImg3() {
        return policyImg3;
    }

    public void setPolicyImg3(String policyImg3) {
        this.policyImg3 = policyImg3 == null ? null : policyImg3.trim();
    }

    public String getPolicyImg4() {
        return policyImg4;
    }

    public void setPolicyImg4(String policyImg4) {
        this.policyImg4 = policyImg4 == null ? null : policyImg4.trim();
    }

    public String getPolicyImg5() {
        return policyImg5;
    }

    public void setPolicyImg5(String policyImg5) {
        this.policyImg5 = policyImg5 == null ? null : policyImg5.trim();
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(String policyHolder) {
        this.policyHolder = policyHolder == null ? null : policyHolder.trim();
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
	
	public String getPolicyImg() {
        return policyImg;
    }

    public void setPolicyImg(String policyImg) {
        this.policyImg = policyImg;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }
}