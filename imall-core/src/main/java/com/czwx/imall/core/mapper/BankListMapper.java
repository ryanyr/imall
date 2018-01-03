package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.BankList;

import java.util.List;
import java.util.Map;

/**
 * 银行编码Dao
 */
@RDBatisDao
public interface BankListMapper extends BaseMapper<BankList,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(BankList record);

    int insertSelective(BankList record);

    BankList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankList record);

    int updateByPrimaryKey(BankList record);

    List<BankList> getBankList();
}