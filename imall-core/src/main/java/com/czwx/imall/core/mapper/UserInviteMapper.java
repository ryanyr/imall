package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserInvite;

@RDBatisDao
public interface UserInviteMapper extends BaseMapper<UserInvite,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(UserInvite record);

    int insertSelective(UserInvite record);

    UserInvite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInvite record);

    int updateByPrimaryKey(UserInvite record);

    int save(UserInvite invite);
}