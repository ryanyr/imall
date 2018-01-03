package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.system.domain.SysPerm;
import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.system.domain.SysRolePerm;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.mapper.SysPermMapper;
import com.czwx.imall.system.mapper.SysRoleMapper;
import com.czwx.imall.system.mapper.SysRolePermMapper;
import com.czwx.imall.system.service.SysRolePermService;
import com.czwx.imall.manage.common.constants.SessionConstants;
import com.czwx.imall.manage.common.exception.ServiceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysRolePermService")
public class SysRolePermServiceImpl extends BaseServiceImpl<SysRolePerm, Long> implements SysRolePermService {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysPermMapper sysPermMapper;
	@Autowired
	private SysRolePermMapper sysRolePermMapper;

	@Override
	public BaseMapper<SysRolePerm, Long> getMapper() {
		return sysRolePermMapper;
	}

	@Override
	public List<Map<String, Object>> getRoleInfoList(Map<String,Object> map) throws ServiceException {
		return sysRoleMapper.getRoleInfoList(map);
	}

	@Override
	public SysRole addRoleInfo(SysRole roleInfo) throws ServiceException {
		return sysRoleMapper.addRoleInfo(roleInfo);
	}
	
	@Override
	public int updateRolePerm(SysRole roleInfo,String permIds) throws ServiceException{
		int count = 0 ;
		count = sysRoleMapper.updateRoleInfo(roleInfo);
		if(count>0){
			sysRoleMapper.delRolePerm(roleInfo.getRoleId().intValue());
			if(!permIds.equals("")){
				String [] permIdsArray = permIds.split(",");
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("roleId", roleInfo.getRoleId());
				SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(SessionConstants.CURRENT_USER);
				map.put("creator", sysUser.getUserName());
				map.put("permIds", permIdsArray);
				count = sysRoleMapper.addRolePerm(map);
			}
		}
		
		return count;
	}
	
	@Override
	public int addRolePerm(Map<String, Object> map) throws ServiceException {
		return sysRoleMapper.addRolePerm(map);
	}

	@Override
	public List<SysPerm> getPermList() throws ServiceException {
		return sysPermMapper.queryPermInfoList();
	}

	@Override
	public SysRole getRoleInfo(Long roleId) throws ServiceException {
		return sysRoleMapper.getRoleInfo(roleId);
	}

	@Override
	public List<Map<String, Object>> getRolePerm(int roleId) throws ServiceException {
		return sysRolePermMapper.getRolePerm(roleId);
	}
	
	@Override
	public int delRolePerm(Map<String, Object> map) throws ServiceException {
		return sysRolePermMapper.delRolePerm(Integer.parseInt(map.get("roleId").toString()));
	}
}
