package com.czwx.imall.system.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 用户实体
 * @author lenovo
 *
 */
public class SysUser {
	
	private Boolean locked=Boolean.FALSE;  
	private Long userId; // 用户编号
	private String userName; //用户名
	private String trueName; // 姓名
	private String password;// 密码
	private String phone; // 电话
	private String email; // 电子邮件
	private String userStatus; // 用户状态
	private Date createTime; // 创建时间
	private Date updateTime; // 更新时间
	private Date loginTime; // 最后一次登录时间
	private String salt; // 密钥
	 
	private List<SysMenu> menuList;
	private List<Map<String, Object>> roleList; // 角色
	
	public List<Map<String, Object>> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Map<String, Object>> roleList) {
		this.roleList = roleList;
	}
	public List<SysMenu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
