package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.exception.PersistentDataException;
import com.czwx.imall.core.common.exception.ServiceException;
import com.czwx.imall.system.domain.SysRoleMenu;
import com.czwx.imall.system.mapper.SysRoleMenuMapper;
import com.czwx.imall.system.service.SysRoleMenuService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service(value = "sysRoleMenuServiceImpl")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Resource
	private SysRoleMenuMapper sysRoleMenuDao;
	
	@Override
	public List<SysRoleMenu> getRoleMenuList(Long roleId) throws ServiceException, PersistentDataException {
		return this.sysRoleMenuDao.getRoleMenuList(roleId);
	}

	public SysRoleMenuMapper getSysRoleMenuDao() {
		return sysRoleMenuDao;
	}

	public void setSysRoleMenuDao(SysRoleMenuMapper sysRoleMenuDao) {
		this.sysRoleMenuDao = sysRoleMenuDao;
	}



}
