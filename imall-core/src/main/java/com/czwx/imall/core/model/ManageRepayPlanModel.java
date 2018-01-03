package com.czwx.imall.core.model;

import com.czwx.imall.core.domain.Order;
import com.czwx.imall.core.domain.OrderRepayPlan;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ManageRepayPlanModel extends OrderRepayPlan {
    private String realName;
    private String phone;
    private String orderNo;
    private BigDecimal repayAmount;

    public static ManageRepayPlanModel instance(Order order) {
        ManageRepayPlanModel orderModel = new ManageRepayPlanModel();
        BeanUtils.copyProperties(order, orderModel);
        return orderModel;
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

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

}
