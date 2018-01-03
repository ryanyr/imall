package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.InsuranceList;

import java.util.List;
import java.util.Map;

/**
 * 保险公司Dao
 */
@RDBatisDao
public interface InsuranceListMapper extends BaseMapper<InsuranceList,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(InsuranceList record);

    int insertSelective(InsuranceList record);

    InsuranceList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsuranceList record);

    int updateByPrimaryKey(InsuranceList record);

    List<InsuranceList> getInsuranceList();
}