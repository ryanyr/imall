package com.czwx.imall.system.domain;

import java.util.Date;

public class SysPerm {
    private int permId; // 权限id
    private String permName; // 权限名称
    private String permString; // 权限标识
    private String creator; //创建人
    private Date createTime; // 创建时间
    public int getPermId() {
        return permId;
    }
    public void setPermId(int permId) {
        this.permId = permId;
    }
    public String getPermName() {
        return permName;
    }
    public void setPermName(String permName) {
        this.permName = permName;
    }
    public String getPermString() {
        return permString;
    }
    public void setPermString(String permString) {
        this.permString = permString;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
