package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserExtraInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long userId;

    private BigDecimal loanAmount;

    private String houseOwnership;

    private String houseExtension;

    private String houseMortgage;

    private String carMortgage;

    private String accumulationFund;

    private String socialSecurity;

    private String salary;

    private String insuranceInfo;

    private String state;

    private Date updateTime;

    private Date createTime;

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

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getHouseOwnership() {
        return houseOwnership;
    }

    public void setHouseOwnership(String houseOwnership) {
        this.houseOwnership = houseOwnership == null ? null : houseOwnership.trim();
    }

    public String getHouseExtension() {
        return houseExtension;
    }

    public void setHouseExtension(String houseExtension) {
        this.houseExtension = houseExtension == null ? null : houseExtension.trim();
    }

    public String getHouseMortgage() {
        return houseMortgage;
    }

    public void setHouseMortgage(String houseMortgage) {
        this.houseMortgage = houseMortgage == null ? null : houseMortgage.trim();
    }

    public String getCarMortgage() {
        return carMortgage;
    }

    public void setCarMortgage(String carMortgage) {
        this.carMortgage = carMortgage == null ? null : carMortgage.trim();
    }

    public String getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(String accumulationFund) {
        this.accumulationFund = accumulationFund == null ? null : accumulationFund.trim();
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity == null ? null : socialSecurity.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getInsuranceInfo() {
        return insuranceInfo;
    }

    public void setInsuranceInfo(String insuranceInfo) {
        this.insuranceInfo = insuranceInfo == null ? null : insuranceInfo.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}