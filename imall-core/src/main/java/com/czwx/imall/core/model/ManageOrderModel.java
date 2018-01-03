package com.czwx.imall.core.model;

import com.czwx.imall.core.domain.Order;
import org.springframework.beans.BeanUtils;

public class ManageOrderModel extends Order {
    private String realName;
    private String phone;
    private String idNo;

    public static ManageOrderModel instance(Order order) {
        ManageOrderModel orderModel = new ManageOrderModel();
        BeanUtils.copyProperties(order, orderModel);
        return orderModel;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
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

    public String getStateStr(){
        String stateStr = this.getState();

        if (OrderModel.STATE_PRE.equals(this.getState())) {
            stateStr = "申请成功待审核";
        }
        else if (OrderModel.STATE_AUDIT_PASS.equals(this.getState())) {
            stateStr = "人工审核通过";
        }
        else if (OrderModel.STATE_AUDIT_REFUSED.equals(this.getState())) {
            stateStr = "人工审核不通过";
        }
        else if (OrderModel.STATE_NEED_REVIEW.equals(this.getState())) {
            stateStr = "待放款审核";
        }
        else if (OrderModel.STATE_PASS.equals(this.getState())) {
            stateStr = "放款审核通过";
        }
        else if (OrderModel.STATE_REFUSED.equals(this.getState())) {
            stateStr = "放款审核不通过";
        }
        else if (OrderModel.STATE_REPAY.equals(this.getState())) {
            stateStr = "放款成功";
        }
        else if (OrderModel.STATE_REPAY_FAIL.equals(this.getState())) {
            stateStr = "放款失败";
        }
        else if (OrderModel.STATE_FINISH.equals(this.getState())) {
            stateStr = "还款成功";
        }
        else if (OrderModel.STATE_REMISSION_FINISH.equals(this.getState())) {
            stateStr = "还款成功-金额减免";
        }
        else if (OrderModel.STATE_REPAY_PRO.equals(this.getState())) {
            stateStr = "还款中";
        }
        else if (OrderModel.STATE_POSTPONE.equals(this.getState())) {
            stateStr = "已延期";
        }
        else if (OrderModel.STATE_DELAY.equals(this.getState())) {
            stateStr = "已逾期";
        }
        else if (OrderModel.STATE_BAD.equals(this.getState())) {
            stateStr = "坏账";
        }
        return stateStr;
    }
}
