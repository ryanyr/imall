package com.czwx.imall.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 借款进度表实体
 */
 public class BorrowProgress implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private Long id;

	/**
	 * 关联用户id
	 */
	private Long userId;

	/**
	 * 借款信息id
	 */
	private Long orderId;

	/**
	 * 借款进度状态
	 */
	private String state;

	/**
	 * 进度描述
	 */
	private String remark;
	
	/**
	 * 借款备注
	 */
	private String borrowRemark;

	/**
	 * 进度生成时间
	 */
	private Date createTime;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

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

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBorrowRemark() {
		return borrowRemark;
	}

	public void setBorrowRemark(String borrowRemark) {
		this.borrowRemark = borrowRemark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}