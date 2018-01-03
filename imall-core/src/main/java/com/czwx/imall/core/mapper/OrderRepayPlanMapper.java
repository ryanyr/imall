package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.OrderRepayPlan;
import com.czwx.imall.core.model.ManageRepayPlanModel;

import java.util.List;
import java.util.Map;

/**
 * 还款计划Dao
 */
@RDBatisDao
public interface OrderRepayPlanMapper extends BaseMapper<OrderRepayPlan,Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderRepayPlan record);

    OrderRepayPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRepayPlan record);

    int updateByOrderId(OrderRepayPlan record);

    OrderRepayPlan selectByOrderId(Long orderId);

    List<ManageRepayPlanModel> listRepayPlan(Map<String, Object> searchMap);

    void deleteByOrderId(Long id);
}