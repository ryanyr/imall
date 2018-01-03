package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.Channel;

/**
 * 渠道Dao
 */
@RDBatisDao
public interface ChannelMapper extends BaseMapper<Channel,Long> {
    int deleteByPrimaryKey(Long id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}