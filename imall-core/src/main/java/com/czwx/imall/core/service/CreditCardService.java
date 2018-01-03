package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.BankList;
import com.czwx.imall.core.domain.CreditCard;

import java.util.List;
import java.util.Map;


/**
 * 信用卡Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-15 14:37:14
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface CreditCardService extends BaseService<CreditCard, Long> {

	/**
	 * 保存记录
	 * @param CreditCard
	 * @return
	 */
	boolean save(CreditCard CreditCard);
	
	/**
	 * 据userId查询银行卡信息
	 * 
	 * @param userId
	 * @return
	 */
	CreditCard getCreditCardByUserId(Long userId);

	/**
	 * 据条件查询银行卡信息
	 * 
	 * @param paramMap
	 * @return
	 */
	CreditCard findSelective(Map<String, Object> paramMap);

	/**
	 * 解约并修改银行卡
	 * 
	 * @param card
	 * @return
	 */
	int cancelAuthSign(CreditCard card);
	
	/**
	 * 修改银行卡信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);
	
	/**
	 * 获取可用银行列表
	 * @return
	 */
	List<BankList> getBankList();

	boolean update(CreditCard CreditCard);

}
