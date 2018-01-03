package com.czwx.imall.system.service;

import com.czwx.imall.core.common.exception.PersistentDataException;
import com.czwx.imall.core.common.exception.ServiceException;
import com.czwx.imall.system.domain.SysRoleMenu;
import java.util.List;


/**
 * 
 * 角色菜单关联信息service接口
 */
public interface SysRoleMenuService {
	/**
	 * 角色菜单关联信息查询
	 * @param roleId 角色ID
	 * @return 角色List
	 * @throws ServiceException 
	 * @throws PersistentDataException 
	 */
	List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException, PersistentDataException;
	
}
