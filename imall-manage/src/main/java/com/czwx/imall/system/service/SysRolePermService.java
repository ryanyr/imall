package com.czwx.imall.system.service;

import com.czwx.imall.system.domain.SysPerm;
import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.manage.common.exception.ServiceException;
import java.util.List;
import java.util.Map;

public interface SysRolePermService {
	List<Map<String,Object>> getRoleInfoList(Map<String, Object> map) throws ServiceException;

	SysRole addRoleInfo(SysRole roleInfo) throws ServiceException;

	int addRolePerm(Map<String, Object> map) throws ServiceException;

	List<SysPerm> getPermList() throws ServiceException;

	SysRole getRoleInfo(Long roleId) throws ServiceException;

	List<Map<String, Object>> getRolePerm(int roleId) throws ServiceException;

	int delRolePerm(Map<String, Object> map) throws ServiceException;

	int updateRolePerm(SysRole roleInfo, String permIds) throws ServiceException;
}
