package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.OrderDetail;

/**
 * 订单明细Dao
 */
@RDBatisDao
public interface OrderDetailMapper extends BaseMapper<OrderDetail,Long> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}