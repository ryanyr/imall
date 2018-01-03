package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserCoupon;

import java.util.List;
import java.util.Map;

/**
 * 客户优惠券Dao
 */
@RDBatisDao
public interface UserCouponMapper extends BaseMapper<UserCoupon,Long> {
    int deleteByPrimaryKey(Long id);

    int save(UserCoupon record);

    int insertSelective(UserCoupon record);

    UserCoupon selectByPrimaryKey(Long id);

    int updateByCouponNo(UserCoupon coupon);

    int updateByPrimaryKey(UserCoupon record);

    UserCoupon findByCouponNo(String couponNo);

    List<UserCoupon> listValid(Map<String,Object> params);

    List<UserCoupon> findOverdueCoupon(Long userId);

    List<UserCoupon> findByUserId(Long userId);

    List<UserCoupon> findEnableCoupon(Map<String,Object> params);

    List<UserCoupon> listByOrderNo(String orderNo);

}