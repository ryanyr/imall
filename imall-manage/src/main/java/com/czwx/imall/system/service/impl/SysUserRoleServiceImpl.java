package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.system.domain.SysUserRole;
import com.czwx.imall.system.mapper.SysUserRoleMapper;
import com.czwx.imall.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole, Long> implements SysUserRoleService {

	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public BaseMapper<SysUserRole, Long> getMapper() {
		return sysUserRoleMapper;
	}

	@Override
	public List<SysUserRole> queryByUserId(Long userId){
		return sysUserRoleMapper.queryByUserId(userId);
	}

	@Override
	public List<SysUserRole> queryByRoleId(Long roleId) {
		return sysUserRoleMapper.queryByRoleId(roleId);
	}

	@Override
	public List<SysUserRole> queryAllUserRole() {
		return sysUserRoleMapper.queryAllUserRole();
	}

	@Override
	public int addUserRole(Map<String,Object> map) {
		sysUserRoleMapper.delUserRole(map);
		return sysUserRoleMapper.addUserRole(map);
	}

}
