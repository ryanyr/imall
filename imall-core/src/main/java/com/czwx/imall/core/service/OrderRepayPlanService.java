package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.OrderRepayPlan;
import com.czwx.imall.core.model.ManageRepayPlanModel;

import com.github.pagehelper.Page;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

public interface OrderRepayPlanService extends BaseService<OrderRepayPlan, Long> {

    Page<ManageRepayPlanModel> listRepayPlan(Map<String, Object> searchMap, int current, int pageSize);

    OrderRepayPlan getPlanByOrderId(long orderId);

    int updateByOrderId(OrderRepayPlan repayPlan);

}
