package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.system.domain.SysRoleMenu;
import com.czwx.imall.system.mapper.SysRoleMapper;
import com.czwx.imall.system.mapper.SysRoleMenuMapper;
import com.czwx.imall.system.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {

	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public BaseMapper<SysRole, Long> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public Page<SysRole> listSysRoleInfo(Map<String, Object> searchMap, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<SysRole> list = sysRoleMapper.listSysRoleInfo(searchMap);
		Page<SysRole> page = (Page<SysRole>) list;
		return page;
	}

	@Override
	public List<Map<String, Object>> getRoleInfoList(Map<String, Object> map) {
		return sysRoleMapper.getRoleInfoList(map);
	}
	//<!-- 获取所有审核角色 根据排序号排序 -->
	public List<SysRole> queryAllRoleInfoSort(Map<String, Object> map){
		return sysRoleMapper.queryAllRoleInfoSort(map);
	}
	@Override
	public SysRole addRoleInfo(SysRole roleInfo) {
		return sysRoleMapper.addRoleInfo(roleInfo);
	}

	@Override
	public int addRolePerm(Map<String, Object> map) {
		return sysRoleMapper.addRolePerm(map);
	}

	@Override
	public SysRole getRoleInfo(Long roleId) {
		return sysRoleMapper.getRoleInfo(roleId);
	}
	@Override
	public SysRole getRoleInfoBySequence(String sequence) {
		return sysRoleMapper.getRoleInfoBySequence(sequence);
	}

	@Override
	public void saveOrUpdateRoleMenus(Long roleId, List<Long> menuIds) {
		sysRoleMenuMapper.deleteByRoleId(roleId);
		for (Long menuId : menuIds) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setMenuId(menuId);
			rm.setRoleId(roleId);
			sysRoleMenuMapper.save(rm);
		}
	}

	@Override
	public List<Map<String, Object>> fetchAllRoles() {
		return sysRoleMapper.fetchAllRoles();
	}

	@Override
	public void saveRole(SysRole sysRole) {
		sysRole.setCreateTime(new Date());
		sysRoleMapper.save(sysRole);
	}

}
