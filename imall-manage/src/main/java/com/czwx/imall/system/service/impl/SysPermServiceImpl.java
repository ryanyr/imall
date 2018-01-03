package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.system.domain.SysPerm;
import com.czwx.imall.system.mapper.SysPermMapper;
import com.czwx.imall.system.service.SysPermService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("sysPermService")
public class SysPermServiceImpl extends BaseServiceImpl<SysPerm, Long> implements SysPermService {

    @Resource
    private SysPermMapper sysPermMapper;

    @Override
    public BaseMapper<SysPerm, Long> getMapper() {
        return sysPermMapper;
    }

    @Override
    public List<SysPerm> queryPermInfo(Long userId) {
        return sysPermMapper.queryPermInfo(userId);
    }
}
