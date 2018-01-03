package com.czwx.imall.system.domain;

import java.util.Date;

public class SysUserRole {
	private Long id;	//主键
	private Long userId;		//用户ID
	private Long roleId;		//角色代表的ID
	private Date createTime;		//创建的时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
