package com.czwx.imall.system.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysRole;
import java.util.List;
import java.util.Map;

@RDBatisDao
public interface SysRoleMapper  extends BaseMapper<SysRole,Long> {

    List<SysRole> listSysRoleInfo(Map<String, Object> searchMap);

    List<Map<String,Object>> getRoleInfoList(Map<String, Object> map);
    //<!-- 获取所有审核角色 根据排序号排序 -->
    List<SysRole> queryAllRoleInfoSort(Map<String, Object> map) ;
    SysRole addRoleInfo(SysRole roleInfo);

    int addRolePerm(Map<String, Object> map);

    SysRole getRoleInfo(Long roleId);

    int updateRoleInfo(SysRole roleInfo);

    int delRolePerm(int roleId);

    SysRole getRoleInfoBySequence(String sequence);

    List<SysRole> queryRolesByUserId(Long userId);

    List<Map<String, Object>> fetchAllRoles();
}
