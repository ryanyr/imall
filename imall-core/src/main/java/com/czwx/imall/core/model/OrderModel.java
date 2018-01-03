package com.czwx.imall.core.model;

import com.czwx.imall.core.domain.Order;

public class OrderModel extends Order {

    /** 申请成功待审核*/
    public static final String STATE_PRE = "10";
    /** 人工审核通过*/
    public static final String STATE_AUDIT_PASS = "20";
    /** 人工审核不通过 */
    public static final String STATE_AUDIT_REFUSED = "21";
    /** 待放款审核*/
    public static final String STATE_NEED_REVIEW = "30";
    /** 放款审核通过 */
    public static final String STATE_PASS = "31";
    /** 放款审核不通过*/
    public static final String STATE_REFUSED = "32";
    /** 放款成功 */
    public static final String STATE_REPAY = "40";
    /** 放款失败 */
    public static final String STATE_REPAY_FAIL = "41";
    /** 还款成功 */
    public static final String STATE_FINISH = "50";
    /** 还款成功-金额减免 */
    public static final String STATE_REMISSION_FINISH = "51";
    /**还款中*/
    public static final String STATE_REPAY_PRO = "52";
    /**已延期*/
    public static final String STATE_POSTPONE = "55";
    /**已逾期 */
    public static final String STATE_DELAY = "60";
    /** 坏账*/
    public static final String STATE_BAD = "90";

    public static String convertBorrowRemark(String state) {
        String remarkStr = " - ";
        if (OrderModel.STATE_PRE.equals(state)) {
            remarkStr = "系统审核中,请耐心等待";
        } else if (OrderModel.STATE_AUDIT_PASS.equals(state)) {
            remarkStr = "恭喜通过人工审核";
        } else if (OrderModel.STATE_AUDIT_REFUSED.equals(state)) {
            remarkStr = "很遗憾，您未通过人工审核";
        } else if (OrderModel.STATE_PASS.equals(state)){
            remarkStr = "恭喜通过放款审核";
        } else if (OrderModel.STATE_REFUSED.equals(state)){
            remarkStr = "很遗憾,您未通过放款审核";
        }  else if (OrderModel.STATE_REPAY.equals(state)) {
            remarkStr = "已打款,请查看您的提现银行卡";
        } else if (OrderModel.STATE_REPAY_FAIL.equals(state)) {
            remarkStr = "放款失败";
        } else if (OrderModel.STATE_FINISH.equals(state)) {
            remarkStr = "还款成功";
        } else if (OrderModel.STATE_REMISSION_FINISH.equals(state)) {
            remarkStr = "借款人无法支付全部借款金额,减免还款成功";
        } else if (OrderModel.STATE_DELAY.equals(state)) {
            remarkStr = "您的借款已逾期";
        } else if (OrderModel.STATE_BAD.equals(state)) {
            remarkStr = "经长时间催收,没有结果";
        }
        return remarkStr;
    }

}
