package com.czwx.imall.system.service;

import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.manage.common.exception.ServiceException;
import com.czwx.imall.system.model.SysUserModel;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;

/**
 * 系统用户管理
 */
public interface SysUserService {

    /**
     * 分页查询系统用户
     * @param searchMap
     * @return
     */
    Page<SysUser> listSysUser(Map<String, Object> searchMap, int current, int pageSize);

    /**
     * 用户查询
     * @param userId 主键ID
     * @return 用户信息
     */
    SysUser getUserById(Long userId)  throws ServiceException;

    SysUserModel getModelById(Long userId) throws ServiceException;

    List<SysUser> getUsers(int userId) throws ServiceException;
    List<SysUser> getUsers(Map<String, Object> map) throws ServiceException;

    /**
     * 根据姓名查询
     *
     * @param name
     * @return
     */
    SysUser queryUserInfo(String name);

    /**
     * 根据ID和密码查询是否存在用户 返回用户的数量
     * @return
     */
    int queryUserInfo(String userId, String password);

    /**
     * 更新用户
     *
     * @param userDO
     * @return
     */
    int updateUser(SysUser userDO); // 更新用户

    /**
     * 更新用户角色
     * @param sysUser
     * @param roleList
     */
    void updateSysUser(SysUser sysUser, List<Long> roleList);

    int updateUser2(Map<String, Object> map); // 更新用户

    /**
     * 添加用户
     * @param infoDO
     * @return
     */
    SysUser addUserInfo(SysUser infoDO);
    /**
     * 添加用户关联的菜单列表
     * @param map
     * @return
     */
    int addUserMenu(Map<String, Object> map);

    /**
     * 删除用户
     * @param map
     * @return
     */
    int delUserInfo(Map<String, Object> map);

    /**
     * 添加系统用户
     * @param user
     * @param userRoleList
     */
    void addSysUser(SysUser user, List<Long> userRoleList);

    /**
     * 校验用户密码
     * @param userId
     * @param oldPassword
     * @return
     */
    int checkPassword(Long userId, String oldPassword);
}
