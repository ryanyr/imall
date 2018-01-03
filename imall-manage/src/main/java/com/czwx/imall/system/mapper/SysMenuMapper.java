package com.czwx.imall.system.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysMenu;
import java.util.List;
import java.util.Map;

@RDBatisDao
public interface SysMenuMapper extends BaseMapper<SysMenu,Long> {

    List<SysMenu> queryMenuInfo(Long userId);

    List<Map<String,Object>> queryAllMenu(Long userId);

    //我的工作台 》快捷菜单
    List<SysMenu> queryShortCutMenu(Map<String, Object> map);

    List<SysMenu> listSysMenu();

    List<Map<String, Object>> fetchAllMenu();

    List<Map<String,Object>> fetchRoleMenuHas(Long roleId);

    int insertmap(Map<String, Object> menuMap);

    int updateMenu(Map<String, Object> menuMap);

}
