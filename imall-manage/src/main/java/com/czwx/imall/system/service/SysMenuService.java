package com.czwx.imall.system.service;

import com.czwx.imall.system.domain.SysMenu;
import java.util.List;
import java.util.Map;

public interface SysMenuService {

	List<SysMenu> queryMenu(String userId);

	List<Map<String,Object>> queryAllMenu(Long userId);

	List<SysMenu> queryMenuByUser(Long userId);

	//我的工作台 》快捷菜单
	List<SysMenu> queryShortCutMenu(Map<String, Object> map);

	List<SysMenu> listSysMenu();

	List<Map<String, Object>> fetchAllMenu();

	List<Map<String, Object>> fetchRoleMenuHas(Long roleId);

	SysMenu getSysMenu(Long menuId);

    int addMenu(Map<String, Object> menuMap);

    int updateMenu(Map<String, Object> menuMap);
}
