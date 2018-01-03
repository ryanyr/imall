package com.czwx.imall.core.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> listCouponNo = new ArrayList<>();

    private long orderId;

    private long userId;

    private String orderNo;

    private BigDecimal amount;

    private BigDecimal policyAmount;

    private BigDecimal realAmount;

    private BigDecimal serviceFee;

    private int timeLimit;

    private Date createTime;

    private String cardNo;

    private BigDecimal repayAmount;

    private Date repayTime;

    private Date realRepayTime;

    private BigDecimal realRepayAmount;

    private BigDecimal interest;

    private String state;

    private String borrowType;

    private int remitAmount;
    //保单照片最多4张

    public String getPolicyImg1() {
        return policyImg1;
    }

    public void setPolicyImg1(String policyImg1) {
        this.policyImg1 = policyImg1;
    }

    public String getPolicyImg2() {
        return policyImg2;
    }

    public void setPolicyImg2(String policyImg2) {
        this.policyImg2 = policyImg2;
    }

    public String getPolicyImg3() {
        return policyImg3;
    }

    public void setPolicyImg3(String policyImg3) {
        this.policyImg3 = policyImg3;
    }

    private String policyImg;

    private String policyImg1;

    private String policyImg2;

    private String policyImg3;

    private String insuranceCompany;

    private String client;

    private int channelId;

    private String couponNo;

    private String brrowType;

    public List<String> getListCouponNo() {
        return listCouponNo;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPolicyAmount() {
        return policyAmount;
    }

    public void setPolicyAmount(BigDecimal policyAmount) {
        this.policyAmount = policyAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public Date getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    public Date getRealRepayTime() {
        return realRepayTime;
    }

    public void setRealRepayTime(Date realRepayTime) {
        this.realRepayTime = realRepayTime;
    }

    public BigDecimal getRealRepayAmount() {
        return realRepayAmount;
    }

    public void setRealRepayAmount(BigDecimal realRepayAmount) {
        this.realRepayAmount = realRepayAmount;
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
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public int getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(int remitAmount) {
        this.remitAmount = remitAmount;
    }

    public String getPolicyImg() {
        return policyImg;
    }

    public void setPolicyImg(String policyImg) {
        this.policyImg = policyImg;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getBrrowType() {
        return brrowType;
    }

    public void setBrrowType(String brrowType) {
        this.brrowType = brrowType;
    }
}
