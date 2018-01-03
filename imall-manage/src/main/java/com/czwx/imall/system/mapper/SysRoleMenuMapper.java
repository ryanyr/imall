package com.czwx.imall.system.mapper;


import com.czwx.imall.core.common.exception.PersistentDataException;
import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysRoleMenu;
import java.util.List;

/**
 * 
 * 角色菜单关系DAO
 */
@RDBatisDao
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu,Long> {
	
	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * @param roleId 角色ID
	 */
	void deleteByRoleId(long roleId);
	
	/**
	 * 角色菜单关联信息查询 LIST
	 * @param roleId  角色ID
	 * @return 角色菜单关系列表
	 */
	List<SysRoleMenu> getRoleMenuList(long roleId) throws PersistentDataException;
}
