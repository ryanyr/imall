package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserCredit;
import java.util.Map;

/**
 * 客户授信额度Dao
 */
@RDBatisDao
public interface UserCreditMapper extends BaseMapper<UserCredit,Long> {

    int deleteByPrimaryKey(Long id);

    int insert(UserCredit record);

    int insertSelective(UserCredit record);

    UserCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCredit record);

    int updateByUserId(UserCredit record);

    UserCredit findByUserId(Long userId);

    int updateCreditAmount(Map<String, Object> params);
}