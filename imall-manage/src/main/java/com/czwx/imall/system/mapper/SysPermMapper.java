package com.czwx.imall.system.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysPerm;
import java.util.List;

@RDBatisDao
public interface SysPermMapper extends BaseMapper<SysPerm,Long> {

    List<SysPerm> queryPermInfo(Long userId);

    List<SysPerm> queryPermInfoList();
}
