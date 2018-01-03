package com.czwx.imall.core.service;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.exception.BussinessException;
import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.Order;
import com.czwx.imall.core.domain.Product;
import com.czwx.imall.core.domain.UserCredit;
import com.czwx.imall.core.form.OrderForm;
import com.czwx.imall.core.model.ManageOrderModel;
import com.github.pagehelper.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService extends BaseService<Order, Long> {

    JSONObject saveOrder(Order order, Product product,List<String> listCouponNo,UserCredit credit)throws BussinessException;

    int accessLevel(BigDecimal amount, Product product);

    int updateByOrderId(Order order);

    /**
     * 审核中
     * @param page
     * @param pageSize
     * @param params
     * @return
     */
    Page<OrderForm> pagelistOrder(int page, int pageSize, Map<String,Object> params);

    /**
     * 借款中
     * @param page
     * @param pageSize
     * @param params
     * @return
     */
    Page<OrderForm> pagelistOnOrder(int page, int pageSize, Map<String,Object> params);

    /**
     * 已还
     * @param page
     * @param pageSize
     * @param params
     * @return
     */
    Page<OrderForm> pagelistHadPayOrder(int page, int pageSize, Map<String,Object> params);

    /**
     * 根据用户ID订单号查询
     * @param params
     * @return
     */
    OrderForm findOrderById(Map<String, Object> params);
    OrderForm findOrderByOrderNo(Map<String, Object> params);

    Page<ManageOrderModel> listOrderModel(Map<String, Object> searchMap, int current, int pageSize);

    ManageOrderModel getModelByOrderId(Long id);

    /**
     * 人工审核
     * @param order
     * @param auditState
     * @param auditRemark
     */
    void reviewAudit(ManageOrderModel order, String auditState, String auditRemark);

    /**
     * 放款审核
     * @param order
     * @param auditState
     * @param auditRemark
     */
    void reviewLoanAudit(ManageOrderModel order, String auditState, String auditRemark);

    Order selectByPrimaryKey(Long id);
}

