package com.czwx.imall.system.model;

import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class SysUserModel extends SysUser {
    /**
     * 用户角色集合
     */
    List<SysRole> roles;

    public static SysUserModel instance(SysUser sysUser) {
        SysUserModel sysUserModel = new SysUserModel();
        BeanUtils.copyProperties(sysUser, sysUserModel);
        return sysUserModel;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
