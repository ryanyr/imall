package com.czwx.imall.system.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysRolePerm;
import java.util.List;
import java.util.Map;

@RDBatisDao
public interface SysRolePermMapper extends BaseMapper<SysRolePerm,Long> {

    List<Map<String, Object>> getRolePerm(int roleId);

    int delRolePerm(int roleId);
}
