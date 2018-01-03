package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.OrderContract;

/**
 * 合同Dao
 */
@RDBatisDao
public interface OrderContractMapper extends BaseMapper<OrderContract,Long> {
    int insert(OrderContract record);

    int insertSelective(OrderContract record);
}