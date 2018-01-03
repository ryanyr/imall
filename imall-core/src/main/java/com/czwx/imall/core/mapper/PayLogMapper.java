package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.PayLog;

/**
 * 支付记录Dao
 */
@RDBatisDao
public interface PayLogMapper extends BaseMapper<PayLog,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(PayLog record);

    int insertSelective(PayLog record);

    PayLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayLog record);

    int updateByPrimaryKey(PayLog record);


}