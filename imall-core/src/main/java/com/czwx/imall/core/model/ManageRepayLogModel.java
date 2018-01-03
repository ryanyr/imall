package com.czwx.imall.core.model;

import com.czwx.imall.core.domain.PayLog;
import com.czwx.imall.core.domain.RepayLog;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ManageRepayLogModel extends RepayLog {
    private String realName;
    private String phone;
    private String orderNo;
    private BigDecimal amount;
    private BigDecimal repayAmount;

    public static ManageRepayLogModel instance(RepayLog repayLog) {
        ManageRepayLogModel repayLogModel = new ManageRepayLogModel();
        BeanUtils.copyProperties(repayLog, repayLogModel);
        return repayLogModel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }
}
