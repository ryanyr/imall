package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.BankCard;

import java.util.List;
import java.util.Map;

/**
 * 客户银行卡Dao
 */
@RDBatisDao
public interface BankCardMapper extends BaseMapper<BankCard,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
    /**
         * 签约银行信息接口
         */
        List<Map<String,Object>> getSignBankInfo(Long userId);

        /**
         * 修改银行卡签约使用状态
         * @param params
         * @return
         */
        void updateSignUseStatus(Map<String,Object> params);
    }
