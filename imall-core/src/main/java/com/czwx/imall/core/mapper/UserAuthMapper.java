package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserAuth;

/**
 * 客户信息认证Dao
 */
@RDBatisDao
public interface UserAuthMapper extends BaseMapper<UserAuth,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    UserAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);

    UserAuth selectByUserId(Long userId);
}