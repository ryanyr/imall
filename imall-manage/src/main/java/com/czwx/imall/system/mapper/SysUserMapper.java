package com.czwx.imall.system.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.system.domain.SysUser;
import java.util.List;
import java.util.Map;

@RDBatisDao
public interface SysUserMapper extends BaseMapper<SysUser,Long> {

    int queryUserByUserIdAndPassword(Map<String, Object> map);

    List<SysUser> listSysUserInfo(Map<String, Object> map);

    List<SysUser> getUsers(int userId);
    List<SysUser> getUsers(Map<String, Object> map);
    SysUser queryUserByName(String name);
    int queryUserInfo(Map<String, Object> map);
    /**
     * 添加用户
     * @param infoDO
     * @return
     */
    SysUser addUserInfo(SysUser infoDO);
    int addUserMenu(Map<String, Object> map);
    int updateUser(Map<String, Object> map);
    int delUserInfo(Map<String, Object> map);
    int delUserMenu(Map<String, Object> map);
    List<SysUser> getUsersByRoleName(String[] roleNameArray);

    /**
     *
     * @Title: queryByTrueName
     * @Description:通过真实姓名查询用户信息
     * @param trueName
     * @return
     */
    List<SysUser> queryByTrueName(String trueName);
}
