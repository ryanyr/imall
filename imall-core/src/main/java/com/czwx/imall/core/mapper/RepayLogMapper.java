package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.RepayLog;
import com.czwx.imall.core.model.ManageRepayLogModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 还款记录Dao
 */
@RDBatisDao
public interface RepayLogMapper extends BaseMapper<RepayLog,Long> {
    int insert(RepayLog record);

    int insertSelective(RepayLog record);

    int updateByOrderId(RepayLog repayLog);

    RepayLog selectByOrderId(Long orderId);

    List<ManageRepayLogModel> listRepayLog(Map<String, Object> searchMap);

    BigDecimal selectRepayment(String dateNowStr);
}