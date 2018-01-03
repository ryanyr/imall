package com.czwx.imall.core.service;

import java.util.List;
import java.util.Map;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.BankCard;
import com.czwx.imall.core.domain.BankList;


/**
 * 银行卡Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-15 14:37:14
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface BankCardService extends BaseService<BankCard, Long> {

	/**
	 * 保存记录
	 * @param bankCard
	 * @return
	 */
	boolean save(BankCard bankCard);
	
	/**
	 * 据userId查询银行卡信息
	 * 
	 * @param userId
	 * @return
	 */
	BankCard getBankCardByUserId(Long userId);

	/**
	 * 据条件查询银行卡信息
	 * 
	 * @param paramMap
	 * @return
	 */
	BankCard findSelective(Map<String, Object> paramMap);

	/**
	 * 解约并修改银行卡
	 * 
	 * @param card
	 * @return
	 */
	int cancelAuthSign(BankCard card);
	
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

	boolean update(BankCard bankCard);

}
