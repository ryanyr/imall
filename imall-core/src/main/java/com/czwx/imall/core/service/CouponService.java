package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.UserCoupon;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CouponService extends BaseService<UserCoupon, Long> {

    Page<UserCoupon> findEnable(int page,int pageSize,Map<String,Object> params);

    UserCoupon findSelective(Map<String,Object> params);

    boolean save(UserCoupon userCoupon);
    UserCoupon  findCouponDetail(Long id);
    Page<UserCoupon> findOverdueCoupon(int page,int pageSize,Long userId);

    UserCoupon findByCouponNo(String counponNo);

    List<UserCoupon> listValid(Map<String,Object> params);

    List<UserCoupon> findByUserId(Long userId);
}
