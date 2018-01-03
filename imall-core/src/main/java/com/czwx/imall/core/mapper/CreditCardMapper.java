package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.CreditCard;
import com.czwx.imall.core.domain.CreditCard;

import java.util.List;
import java.util.Map;

/**
 * 客户信用卡Dao
 */
@RDBatisDao
public interface CreditCardMapper extends BaseMapper<CreditCard,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(CreditCard record);

    int insertSelective(CreditCard record);

    CreditCard selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditCard record);

    int updateByPrimaryKey(CreditCard record);
    /**
         * 签约银行信息接口
         */
        List<Map<String,Object>> getSignBankInfo(Long userId);

        /**
         * 修改银行卡签约使用状态
         * @param params
         * @return
         */
        void updateSignUseStatus(Map<String, Object> params);



    }
