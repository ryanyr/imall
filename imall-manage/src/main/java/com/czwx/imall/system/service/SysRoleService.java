package com.czwx.imall.system.service;

import com.czwx.imall.system.domain.SysRole;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;

public interface SysRoleService {

	Page<SysRole> listSysRoleInfo(Map<String, Object> searchMap, int current, int pageSize);

	List<Map<String,Object>> getRoleInfoList(Map<String, Object> map);

	//<!-- 获取所有审核角色 根据排序号排序 -->
	List<SysRole> queryAllRoleInfoSort(Map<String, Object> map);

	SysRole addRoleInfo(SysRole roleInfo);

	int addRolePerm(Map<String, Object> map);

	SysRole getRoleInfo(Long roleId);

	SysRole getRoleInfoBySequence(String sequence);

    void saveOrUpdateRoleMenus(Long roleId, List<Long> menuIds);

	/**
	 * 获取所有角色
	 * @return
	 */
	List<Map<String, Object>> fetchAllRoles();

    void saveRole(SysRole sysRole);
}
