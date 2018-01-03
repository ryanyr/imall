package com.czwx.imall.system.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.manage.common.exception.ServiceException;
import com.czwx.imall.manage.common.helper.PasswordHelper;
import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.domain.SysUserRole;
import com.czwx.imall.system.mapper.SysRoleMapper;
import com.czwx.imall.system.mapper.SysUserMapper;
import com.czwx.imall.system.mapper.SysUserRoleMapper;
import com.czwx.imall.system.model.SysUserModel;
import com.czwx.imall.system.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public BaseMapper<SysUser, Long> getMapper() {
        return sysUserMapper;
    }

    @Override
    public Page<SysUser> listSysUser(Map<String, Object> searchMap, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<SysUser> list = sysUserMapper.listSysUserInfo(searchMap);
        Page<SysUser> page = (Page<SysUser>) list;
        return page;
    }

    @Override
    public SysUser getUserById(Long userId) throws ServiceException {
        return sysUserMapper.findByPrimary(userId);
    }

    @Override
    public SysUserModel getModelById(Long userId) throws ServiceException {
        SysUser sysUser = sysUserMapper.findByPrimary(userId);
        if (sysUser == null) {
            logger.error("查询的用户不存在");
        } else {
            SysUserModel sysUserModel = SysUserModel.instance(sysUser);
            List<SysRole> roles = sysRoleMapper.queryRolesByUserId(userId);
            sysUserModel.setRoles(roles);

            return sysUserModel;
        }
        return null;
    }

    @Override
    public List<SysUser> getUsers(int userId) throws ServiceException {
        List<SysUser> list = new ArrayList<SysUser>();
        list = sysUserMapper.getUsers(userId);
        return list;
    }
    @Override
    public List<SysUser> getUsers(Map<String,Object> map) throws ServiceException {
        List<SysUser> list = new ArrayList<SysUser>();
        list = sysUserMapper.getUsers(map);
        return list;
    }
    @Override
    public SysUser queryUserInfo(String name) {
        return sysUserMapper.queryUserByName(name);
    }

    @Override
    public int queryUserInfo(String userId, String password) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<SysUser> list = sysUserMapper.getUsers(Integer.parseInt(userId));
        SysUser userInfo = list.get(0);
        SysUser userInfo2 = new SysUser();
        userInfo2.setUserId(Long.parseLong(userId));
        userInfo2.setSalt(userInfo.getSalt());
        userInfo2.setPassword(password);
        map.put("userId", userId);
        try {
            passwordHelper.encryptPassword2(userInfo2);
            map.put("password", userInfo2.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUserMapper.queryUserInfo(map);
    }
    @Override
    public int updateUser(SysUser userDO) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId", userDO.getUserId());
        map.put("password", userDO.getPassword());
        map.put("salt", userDO.getSalt());
        map.put("updateTime", "updateTime");
        return sysUserMapper.updateUser(map);
    }

    @Override
    public void updateSysUser(SysUser sysUser, List<Long> roleList) {
        sysUserRoleMapper.deleteByUserId(sysUser.getUserId());
        for (Long roleId : roleList) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(sysUser.getUserId());
            ur.setRoleId(roleId);
            sysUserRoleMapper.save(ur);
        }
    }

    @Override
    public int updateUser2(Map<String,Object> map) {
        return sysUserMapper.updateUser(map);
    }

    @Override
    public SysUser addUserInfo(SysUser infoDO) {
        return sysUserMapper.addUserInfo(infoDO);
    }

    @Override
    public int addUserMenu(Map<String,Object> map) {
        sysUserMapper.delUserMenu(map);
        return sysUserMapper.addUserMenu(map);
    }

    @Override
    public int delUserInfo(Map<String, Object> map) {
        return sysUserMapper.delUserInfo(map);
    }

    @Override
    public void addSysUser(SysUser user, List<Long> userRoleList) {
        user.setPassword("111111"); // 默认密码为111111
        passwordHelper.encryptPassword(user);
        sysUserMapper.save(user);

        for (Long roleId : userRoleList) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            sysUserRoleMapper.save(ur);
        }
    }

    @Override
    public int checkPassword(Long userId, String oldPassword) {
        SysUser sysUser = sysUserMapper.findByPrimary(userId);
        SysUser userInfo2 = new SysUser();
        userInfo2.setUserId(userId);
        userInfo2.setSalt(sysUser.getSalt());
        userInfo2.setPassword(oldPassword);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        try {
            passwordHelper.encryptPassword2(userInfo2);
            map.put("password", userInfo2.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysUserMapper.queryUserByUserIdAndPassword(map);
    }
}
