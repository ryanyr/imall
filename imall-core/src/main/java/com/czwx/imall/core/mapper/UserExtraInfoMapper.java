package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserExtraInfo;

@RDBatisDao
public interface UserExtraInfoMapper extends BaseMapper<UserExtraInfo,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(UserExtraInfo record);

    int insertSelective(UserExtraInfo record);

    UserExtraInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserExtraInfo record);

    int updateByPrimaryKey(UserExtraInfo record);

    UserExtraInfo findByUserId(Long userId);
}