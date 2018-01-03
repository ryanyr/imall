package com.czwx.imall.core.model;

import com.czwx.imall.core.domain.OrderRepayPlan;

public class OrderRepayPlanModel extends OrderRepayPlan {

    /** 还款状态 -已还款 */
    public static final String STATE_REPAY_YES = "10";

    /** 还款状态 - 未还款 */
    public static final String STATE_REPAY_NO = "20";

    /** 延期状态 -已延期 */
    public static final String STATE_EXTENSION_REPAY_YES = "10";

    /** 延期状态 - 未延期 */
    public static final String STATE_EXTENSION_REPAY_NO = "20";
}
