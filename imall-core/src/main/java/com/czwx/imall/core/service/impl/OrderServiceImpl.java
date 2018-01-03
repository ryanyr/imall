package com.czwx.imall.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.exception.BussinessException;
import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.common.util.DateUtil;
import com.czwx.imall.core.common.util.MoneyUtil;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.domain.*;
import com.czwx.imall.core.domain.OrderDetail;
import com.czwx.imall.core.domain.OrderRepayPlan;
import com.czwx.imall.core.form.OrderForm;
import com.czwx.imall.core.mapper.*;
import com.czwx.imall.core.model.BorrowProgressModel;
import com.czwx.imall.core.model.ManageOrderModel;
import com.czwx.imall.core.model.OrderModel;
import com.czwx.imall.core.model.OrderRepayPlanModel;
import com.czwx.imall.core.service.BorrowProgressService;
import com.czwx.imall.core.service.OrderService;
import com.czwx.imall.core.service.ProductService;
import com.czwx.imall.core.service.UserCreditService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("orderService")
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends BaseServiceImpl<Order, Long> implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private OrderRepayPlanMapper orderRepayPlanMapper;

    @Resource
    private UserCreditMapper userCreditMapper;

    @Resource
    private UserCouponMapper userCouponMapper;

    @Resource
    private BorrowProgressMapper borrowProgressMapper;

    @Resource
    private ProductService productService;

    @Resource
    private UserCreditService userCreditService;

    @Resource
    private BorrowProgressService borrowProgressService;

    @Override
    public BaseMapper<Order, Long> getMapper() {
        return orderMapper;
    }

    /**
     * 保存借款信息
     */
    @Override
    public JSONObject saveOrder(Order order, Product product, List<String> listCouponNo, UserCredit credit) throws BussinessException {
        //校验参数
        if (order == null) throw new BussinessException("order is null");

        //获取费用等级
        int level = accessLevel(order.getAmount(), product);
        if (level == 0) {
            throw new BussinessException("FeeLevel is error");
        }
        //创建订单明细
        OrderDetail detail = new OrderDetail();
        detail.setUserId(order.getUserId());
        detail.setAmount(order.getAmount());
        detail.setFeeLevel(level);
        detail.setCreateTime(new Date());
        detail.setUpdateTime(new Date());

        //保存订单 订单明细 还款计划
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setState(OrderModel.STATE_PRE);
        orderMapper.save(order);
        detail.setOrderId(order.getId());
        orderDetailMapper.save(detail);

        //修改用户授信可用额度
        credit.setUnuse(credit.getUnuse().subtract(order.getAmount()));
        credit.setUsed(credit.getUsed().add(order.getAmount()));
        userCreditMapper.updateByUserId(credit);

        if (listCouponNo != null) {
            for (String couponNo : listCouponNo) {
                UserCoupon coupon = new UserCoupon();
                coupon.setCouponNo(couponNo);
                coupon.setState("20");
                coupon.setOrderNo(order.getOrderNo());
                userCouponMapper.updateByCouponNo(coupon);
            }
        }

        // 借款进度
        saveProgressState(order, OrderModel.STATE_PRE, "");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNo", order.getOrderNo());
        jsonObject.put("amount", order.getAmount());
        jsonObject.put("timeLimit", order.getTimeLimit());
        jsonObject.put("serviceFee", order.getServiceFee());
        if (order.getPolicyAmount() != null) {
            jsonObject.put("policyAmount", order.getPolicyAmount());
        }
        if (order.getPolicyImg() != null) {
            jsonObject.put("policyImg", order.getPolicyImg());
        }
        return jsonObject;
    }

    public void saveProgressState(Order borrow, String state, String borrowRemark) {
        BorrowProgress borrowProgress = new BorrowProgress();
        borrowProgress.setOrderId(borrow.getId());
        borrowProgress.setUserId(borrow.getUserId());
        if (state.equals(OrderModel.STATE_PRE)) {
            borrowProgress.setRemark("借款"
                    + StringUtil.isNull(borrow.getAmount())
                    + "元，期限"
                    + borrow.getTimeLimit()
                    + "天，综合费用"
                    + StringUtil.isNull(borrow.getFee()) + "元，"
                    + OrderModel.convertBorrowRemark(state));
        } else {
            borrowProgress.setRemark(OrderModel.convertBorrowRemark(state));
        }
        borrowProgress.setState(state);
        borrowProgress.setCreateTime(DateUtil.getNow());
        borrowProgress.setBorrowRemark(borrowRemark);
        borrowProgressMapper.save(borrowProgress);
    }

    /**
     * 借款等级计算
     *
     * @param amount
     * @param product
     * @return
     */
    @Override
    public int accessLevel(BigDecimal amount, Product product) {
        int am = amount.intValue();
        int feeAmountStart = product.getFeeAmountStart().intValue();
        int level = 1 + (am - feeAmountStart) / feeAmountStart + ((am - feeAmountStart) % feeAmountStart == 0 ? 0 : 1);
        if (level > 5) {
            return 0;
        } else {
            return level;
        }
    }

    @Override
    public int updateByOrderId(Order order) {
        return orderMapper.updateById(order);
    }

    /**
     * 未还订单列表展示
     *
     * @param page
     * @param pageSize
     * @param params
     * @return
     */
    @Override
    public Page<OrderForm> pagelistOrder(int page, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(page, pageSize);

        List<OrderForm> list = orderMapper.listOrder(params);
        return (Page<OrderForm>) list;
    }

    /**
     * 再贷订单列表展示
     *
     * @param page
     * @param pageSize
     * @param params
     * @return
     */
    @Override
    public Page<OrderForm> pagelistOnOrder(int page, int pageSize, Map<String, Object> params) {

        PageHelper.startPage(page, pageSize);

        List<OrderForm> list = orderMapper.listOnOrder(params);
        return (Page<OrderForm>) list;
    }

    /**
     * 已换订单列表展示
     *
     * @param page
     * @param pageSize
     * @param params
     * @return
     */
    @Override
    public Page<OrderForm> pagelistHadPayOrder(int page, int pageSize, Map<String, Object> params) {
        PageHelper.startPage(page, pageSize);

        List<OrderForm> list = orderMapper.listHadPayOrder(params);
        return (Page<OrderForm>) list;
    }

    /**
     * 订单号获取订单信息
     *
     * @param params
     * @return
     */
    @Override
    public OrderForm findOrderById(Map<String, Object> params) {

        OrderForm model = orderMapper.findOrderById(params);
        //校验参数
        if (model == null) throw new BussinessException("model is null");
        Product product = productService.getProduct();
        BigDecimal ratio = product.getPenaltyAmountRatio();
        OrderRepayPlan repayPlan = orderRepayPlanMapper.selectByOrderId(model.getOrderId());
        //计算是否逾期
        Date rDate = model.getRepayTime();
        Date dDate = new Date();
        int extensionDay = 0;
        long intervalMilli = dDate.getTime() - rDate.getTime();
        if (intervalMilli > 0 && intervalMilli < (24 * 60 * 60 * 1000)) {
            extensionDay = 1;
        } else if (intervalMilli >= (24 * 60 * 60 * 1000)) {
            extensionDay = (int) (intervalMilli / (24 * 60 * 60 * 1000)) + ((intervalMilli - (24 * 60 * 60 * 1000)) % (24 * 60 * 60 * 1000) == 0 ? 0 : 1);
        }

        BigDecimal interest = BigDecimal.ZERO;
        if (extensionDay > 0 && repayPlan.getExtensionState().equals("20")) {

            //更新还款计划
            OrderRepayPlan plan = new OrderRepayPlan();
            plan.setOrderId(model.getOrderId());
            plan.setExtensionState("10");
            plan.setExtensionDay(extensionDay);
            plan.setExtensionAmount(model.getServiceFee());
            plan.setAmount(model.getRepayAmount().add(model.getServiceFee()));

            //延期更新订单状态
            Order order = new Order();
            order.setOrderNo(model.getOrderNo());
            order.setState(OrderModel.STATE_POSTPONE);
            order.setUpdateTime(new Date());
            orderMapper.updateByOrderNo(order);

            if (extensionDay > model.getTimeLimit()) {
                //计算利息
                interest = MoneyUtil.calcInterest(model.getAmount(), ratio, extensionDay - model.getTimeLimit());
                //利息不得超过本金
                if (interest.compareTo(model.getAmount()) > 0) {
                    interest = model.getAmount();
                }
                model.setState(OrderModel.STATE_DELAY);
                model.setInterest(interest);

                //逾期更新订单状态
                Order order1 = new Order();
                order1.setOrderNo(model.getOrderNo());
                order1.setState(OrderModel.STATE_DELAY);
                order1.setInterest(interest);
                order1.setUpdateTime(new Date());
                orderMapper.updateByOrderNo(order1);

                plan.setPenaltyAmount(interest);
                plan.setPenaltyDay(extensionDay - model.getTimeLimit());
                plan.setCreateTime(new Date());
                plan.setAmount(plan.getAmount().add(interest));
            }
            model.setRepayAmount(plan.getAmount());
            model.setServiceFee(model.getServiceFee().add(plan.getExtensionAmount()));
            orderRepayPlanMapper.updateByOrderId(plan);
        }
        model.setInterest(interest);
        return model;
    }

    @Override
    public OrderForm findOrderByOrderNo(Map<String, Object> params) {
        OrderForm model = orderMapper.findOrderById(params);
        return  model;
    }

    @Override
    public Page<ManageOrderModel> listOrderModel(Map<String, Object> searchMap, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<ManageOrderModel> list = orderMapper.listOrderModel(searchMap);
        return (Page<ManageOrderModel>) list;
    }

    @Override
    public ManageOrderModel getModelByOrderId(Long id) {
        ManageOrderModel model = new ManageOrderModel();
        Order order = orderMapper.selectByPrimaryKey(id);
        if (order == null) {
            logger.error("查询的订单不存在");
        } else {
            model = ManageOrderModel.instance(order);
        }

        return model;
    }

    @Override
    public void reviewAudit(ManageOrderModel orderModel, String auditState, String auditRemark) {
        if (OrderModel.STATE_PRE.equals(orderModel.getState())
                && (OrderModel.STATE_AUDIT_PASS.equals(auditState) || OrderModel.STATE_AUDIT_REFUSED.equals(auditState))) {
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("id", orderModel.getId());
            updateMap.put("state", auditState);
            updateMap.put("remark", auditRemark);
            orderMapper.updateSelective(updateMap);

            if (OrderModel.STATE_AUDIT_REFUSED.equals(auditState)) { // 拒绝 恢复授信额度
                userCreditService.modifyCredit(orderModel.getUserId(), orderModel.getAmount().doubleValue(), "unuse");
            }

            // 借款进度
            saveProgressState(orderModel, auditState, auditRemark);
        }
    }

    @Override
    public void reviewLoanAudit(ManageOrderModel orderModel, String auditState, String auditRemark) {
        if (OrderModel.STATE_AUDIT_PASS.equals(orderModel.getState())
                && (OrderModel.STATE_PASS.equals(auditState) || OrderModel.STATE_REFUSED.equals(auditState))) {
            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("id", orderModel.getId());
            updateMap.put("state", auditState);
            updateMap.put("remark", auditRemark);
            updateMap.put("loanTime", DateUtil.getNow());
            orderMapper.updateSelective(updateMap);

            if (OrderModel.STATE_PASS.equals(auditState)) {
                orderRepayPlanMapper.deleteByOrderId(orderModel.getId());

                //创建还款计划
                OrderRepayPlan plan = new OrderRepayPlan();
                plan.setUserId(orderModel.getUserId());
                plan.setAmount(orderModel.getAmount().add(orderModel.getServiceFee()));
                plan.setState(OrderRepayPlanModel.STATE_REPAY_NO);
                plan.setExtensionDay(orderModel.getTimeLimit());
                plan.setExtensionAmount(orderModel.getServiceFee());
                plan.setExtensionState(OrderRepayPlanModel.STATE_EXTENSION_REPAY_NO);
                plan.setCreateTime(new Date());
                plan.setUpdateTime(new Date());

                // 设置应还时间
                String repay = DateUtil.dateStr2(DateUtil.rollDay(DateUtil.getNow(),(orderModel.getTimeLimit()) - 1));
                repay = repay + " 23:59:59";
                plan.setRepayTime(DateUtil.valueOf(repay, "yyyy-MM-dd HH:mm:ss"));

                List<UserCoupon> couponList = userCouponMapper.listByOrderNo(orderModel.getOrderNo());
                int discount = 0;
                if (couponList != null) {
                    for (UserCoupon coupon : couponList) {
                        discount += coupon.getAmount();
                    }
                    plan.setAmount(plan.getAmount().subtract(new BigDecimal(discount)));
                }

                plan.setOrderId(orderModel.getId());
                orderRepayPlanMapper.save(plan);
            } else { // 拒绝 恢复授信额度
                //修改用户授信可用额度
                userCreditService.modifyCredit(orderModel.getUserId(), orderModel.getAmount().doubleValue(), "unuse");
            }

            // 借款进度
            saveProgressState(orderModel, auditState, auditRemark);
        }

    }

    @Override
    public Order selectByPrimaryKey(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }
}