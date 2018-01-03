package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserMakeMoneyInfo;
@RDBatisDao
public interface UserMakeMoneyInfoMapper extends BaseMapper<UserMakeMoneyInfo,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(UserMakeMoneyInfo record);

    int insertSelective(UserMakeMoneyInfo record);

    UserMakeMoneyInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMakeMoneyInfo record);

    int updateByPrimaryKey(UserMakeMoneyInfo record);
}