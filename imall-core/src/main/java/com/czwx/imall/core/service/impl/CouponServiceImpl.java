package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.Order;
import com.czwx.imall.core.domain.OrderRepayPlan;
import com.czwx.imall.core.domain.RepayLog;
import com.czwx.imall.core.domain.UserCoupon;
import com.czwx.imall.core.mapper.OrderMapper;
import com.czwx.imall.core.mapper.OrderRepayPlanMapper;
import com.czwx.imall.core.mapper.UserCouponMapper;
import com.czwx.imall.core.service.CouponService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("couponService")
@Transactional(rollbackFor = Exception.class)
public class CouponServiceImpl extends BaseServiceImpl<UserCoupon, Long> implements CouponService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private UserCouponMapper couponMapper;

    @Override
    public BaseMapper<UserCoupon, Long> getMapper() {
        return couponMapper;
    }

    @Override
    public Page<UserCoupon> findEnable(int page,int pageSize,Map<String, Object> params) {
        PageHelper.startPage(page, pageSize);
        return (Page<UserCoupon>)couponMapper.findEnableCoupon(params);
    }

    @Override
    public UserCoupon findSelective(Map<String, Object> params) {
        return couponMapper.findSelective(params);
    }

    @Override
    public boolean save(UserCoupon userCoupon) {
       int i= couponMapper.save(userCoupon);
       if(i>0){
           return true;
       }
       return false;
    }

    @Override
    public UserCoupon findCouponDetail(Long id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserCoupon> findOverdueCoupon(int page,int pageSize,Long userId) {
        PageHelper.startPage(page, pageSize);
        return (Page<UserCoupon>)couponMapper.findOverdueCoupon(userId);
    }

    @Override
    public UserCoupon findByCouponNo(String counponNo) {
        return couponMapper.findByCouponNo(counponNo);
    }

    @Override
    public List<UserCoupon> listValid(Map<String, Object> params) {
        return couponMapper.listValid(params);
    }

    @Override
    public List<UserCoupon> findByUserId(Long userId) {
        return couponMapper.findByUserId(userId);
    }

}
