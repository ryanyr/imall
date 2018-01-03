package com.czwx.imall.system.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysUserRole;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {

    List<SysUserRole> queryByUserId(Long userId);

    List<SysUserRole> queryByRoleId(Long roleId);

    List<SysUserRole> queryAllUserRole();

    int addUserRole(Map<String, Object> map);

    int delUserRole(Map<String, Object> map);

    void deleteByUserId(Long userId);

}
