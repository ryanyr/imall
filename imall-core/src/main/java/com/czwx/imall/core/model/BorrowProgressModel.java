package com.czwx.imall.core.model;

import com.czwx.imall.core.domain.BorrowProgress;

public class BorrowProgressModel extends BorrowProgress {
	
	private static final long serialVersionUID = 1L;
	
	/** 申请成功待审核 */
	public static final String PROGRESS_APPLY = "10";
	/** 人工审核通过*/
	public static final String PROGRESS_PERSON_PASS = "20";
	/** 人工审核不通过 */
	public static final String PROGRESS_PERSON_REFUSE = "21";
	/** 待放款审核*/
	public static final String PROGRESS_LOAN_REVIEW = "30";
	/** 放款审核通过 */
	public static final String PROGRESS_LOAN_PASS = "31";
	/** 放款审核不通过*/
	public static final String PROGRESS_LOAN_REFUSE = "32";
	/** 放款成功 */
	public static final String PROGRESS_LOAN_SUCCESS = "40";
	/** 放款失败 */
	public static final String PROGRESS_LOAN_FAIL = "41";
	/** 还款成功 */
	public static final String PROGRESS_REPAY_SUCCESS = "50";
	/** 逾期减免 */
	public static final String PROGRESS_REPAY_REMISSION_SUCCESS = "51";
	/** 逾期 */
	public static final String PROGRESS_REPAY_OVERDUE = "60";
	/** 坏账 */
	public static final String PROGRESS_BILL_BAD = "90";

	private String msg;
	
	private String type;
	
	private String createTimeStr;
	
	/**
	 * 状态描述
	 */
	private String str;
	
	private String alter(String state) {
		String stateStr = "";
		if (PROGRESS_APPLY.equals(state)) {
			stateStr = "申请提交成功";
		}else if (PROGRESS_PERSON_PASS.equals(state)) {
			stateStr = "审核中";
		}else if (PROGRESS_LOAN_PASS.equals(state)
				||PROGRESS_PERSON_PASS.equals(state)) {
			stateStr = "审核通过";
		}else if (PROGRESS_PERSON_REFUSE.equals(state)
				||PROGRESS_LOAN_REFUSE.equals(state)) {
			stateStr = "审核未通过";
		}else if (PROGRESS_LOAN_SUCCESS.equals(state)) {
			stateStr = "已打款";
		}else if (PROGRESS_REPAY_SUCCESS.equals(state)
				||PROGRESS_REPAY_REMISSION_SUCCESS.equals(state)){
			stateStr="已还款";
		}else if (PROGRESS_REPAY_OVERDUE.equals(state)){
			stateStr="已逾期";
		}else if (PROGRESS_BILL_BAD.equals(state)){
			stateStr="坏账";
		}else{
			stateStr=state;
		}
		return stateStr;
	}


	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the createTimeStr
	 */
	public String getCreateTimeStr() {
		return createTimeStr;
	}

	/**
	 * @param createTimeStr the createTimeStr to set
	 */
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}



	/**
	 * @return the str
	 */
	public String getStr() {
		return str;
	}


	/**
	 * @param str the str to set
	 */
	public void setStr(String str) {
		this.str = alter(str);
	}

	
}
