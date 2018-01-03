package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.OrderRepayPlan;
import com.czwx.imall.core.mapper.*;
import com.czwx.imall.core.model.ManageRepayPlanModel;
import com.czwx.imall.core.service.OrderRepayPlanService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("orderRepayPlanService")
@Transactional(rollbackFor = Exception.class)
public class OrderRepayPlanServiceImpl extends BaseServiceImpl<OrderRepayPlan, Long> implements OrderRepayPlanService {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepayPlanServiceImpl.class);

    @Resource
    private OrderRepayPlanMapper repayPlanMapper;

    @Override
    public BaseMapper<OrderRepayPlan, Long> getMapper() {
        return repayPlanMapper;
    }

    @Override
    public Page<ManageRepayPlanModel> listRepayPlan(Map<String, Object> searchMap, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<ManageRepayPlanModel> list = repayPlanMapper.listRepayPlan(searchMap);
        return (Page<ManageRepayPlanModel>) list;

    }

    @Override
    public OrderRepayPlan getPlanByOrderId(long orderId) {
        return repayPlanMapper.selectByOrderId(orderId);
    }

    @Override
    public int updateByOrderId(OrderRepayPlan repayPlan) {
        return repayPlanMapper.updateByOrderId(repayPlan);
    }
}
