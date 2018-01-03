package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.system.domain.SysMenu;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.mapper.SysMenuMapper;
import com.czwx.imall.system.mapper.SysUserMapper;
import com.czwx.imall.system.service.SysMenuService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Long> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public BaseMapper<SysMenu, Long> getMapper() {
        return sysMenuMapper;
    }

    @Override
    public List<SysMenu> queryMenu(String userName) {
        SysUser user = null;
        user= sysUserMapper.queryUserByName(userName);
        if(user==null){	// 如果没查到用户返回空数据
            return new ArrayList<SysMenu>();
        }
        return sysMenuMapper.queryMenuInfo(user.getUserId());
    }

    @Override
    public List<Map<String, Object>> queryAllMenu(Long userId) {
        return sysMenuMapper.queryAllMenu(userId);
    }

    @Override
    public List<SysMenu> queryMenuByUser(Long userId) {
        return sysMenuMapper.queryMenuInfo(userId);
    }

    @Override
    public List<SysMenu> queryShortCutMenu(Map<String, Object> map) {
        return sysMenuMapper.queryShortCutMenu(map);
    }

    @Override
    public List<SysMenu> listSysMenu() {
        return sysMenuMapper.listSysMenu();
    }

    public List<Map<String, Object>> fetchAllMenu() {
        return sysMenuMapper.fetchAllMenu();
    }

    @Override
    public List<Map<String, Object>> fetchRoleMenuHas(Long roleId) {
        return sysMenuMapper.fetchRoleMenuHas(roleId);
    }

    @Override
    public SysMenu getSysMenu(Long menuId) {
        return sysMenuMapper.findByPrimary(menuId);
    }

    @Override
    public int addMenu(Map<String, Object> menuMap) {
        return sysMenuMapper.insertmap(menuMap);
    }

    @Override
    public int updateMenu(Map<String, Object> menuMap) {
        return sysMenuMapper.updateMenu(menuMap);
    }
}
