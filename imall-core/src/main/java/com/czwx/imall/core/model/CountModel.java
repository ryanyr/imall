package com.czwx.imall.core.model;

import java.math.BigDecimal;

public class CountModel {

    /**
     * 当天注册数(每日数据)
     */
    private long registerCount;

    /**
     * 登录数(每日数据)
     */
    private long loginCount;

    /**
     * 借款申请数(每日数据)
     */
    private long applyCount;

    /**
     * 通过数(每日数据)
     */
    private long pass;

    /**
     * 通过率(每日数据)
     */
    private String passRate;

    /**
     * 放款量(每日数据)
     */
    private BigDecimal loanAmount;

    /**
     * 还款量(每日数据)
     */
    private BigDecimal repayment;

    /**
     * 逾期笔数(每日数据)
     */
    private long overdue;


    /**
     * 总注册量(累计数据)
     */
    private long registerSum;

    public long getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(long registerCount) {
        this.registerCount = registerCount;
    }

    public long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(long loginCount) {
        this.loginCount = loginCount;
    }

    public long getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(long applyCount) {
        this.applyCount = applyCount;
    }

    public long getPass() {
        return pass;
    }

    public void setPass(long pass) {
        this.pass = pass;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getRepayment() {
        return repayment;
    }

    public void setRepayment(BigDecimal repayment) {
        this.repayment = repayment;
    }

    public long getOverdue() {
        return overdue;
    }

    public void setOverdue(long overdue) {
        this.overdue = overdue;
    }

    public long getRegisterSum() {
        return registerSum;
    }

    public void setRegisterSum(long registerSum) {
        this.registerSum = registerSum;
    }
}
