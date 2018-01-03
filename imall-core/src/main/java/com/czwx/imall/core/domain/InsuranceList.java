package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 保险公司实体
 */
public class InsuranceList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    private String companyName;

    private Date createTime;

    private Date updateTime;



    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}