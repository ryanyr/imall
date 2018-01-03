package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.Order;
import com.czwx.imall.core.form.OrderForm;
import com.czwx.imall.core.model.ManageOrderModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单明细Dao
 */
@RDBatisDao
public interface OrderMapper extends BaseMapper<Order,Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Order order);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Order order);

	int updateByOrderNo(Order order);

	int updateById(Order order);

    Order findByOrderNo(String orderNo);

    List<ManageOrderModel> listOrderModel(Map<String, Object> searchMap);

    /**
     * 审核中
     * @param params
     * @return
     */
    List<OrderForm> listOrder(Map<String, Object> params);

    /**
     * 为还款
     * @param params
     * @return
     */
    List<OrderForm> listOnOrder(Map<String, Object> params);

    /**
     * 已还
     * @param params
     * @return
     */
    List<OrderForm> listHadPayOrder(Map<String, Object> params);

    /**
     * 根据用户ID订单号查询
     * @param params
     * @return
     */
    OrderForm findOrderById(Map<String, Object> params);

    /**
     * 根据用户ID订单号查询
     * @param orderNo
     * @return
     */
    OrderForm findOrderByOrderNo(String orderNo);

    long selectApply(String dateNowStr);

    long selectPass(String dateNowStr);

    BigDecimal selectLoanAmount(String dateNowStr);

    long selectOverdue(String dateNowStr);

}