package com.czwx.imall.system.service;

import com.czwx.imall.system.domain.SysUserRole;
import java.util.List;
import java.util.Map;

public interface SysUserRoleService {

	List<SysUserRole> queryByUserId(Long userId);

	List<SysUserRole> queryByRoleId(Long roleId);

	List<SysUserRole> queryAllUserRole();

	int addUserRole(Map<String, Object> map);
}
