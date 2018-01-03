package com.czwx.imall.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.BankCard;
import com.czwx.imall.core.domain.BankList;
import com.czwx.imall.core.mapper.BankCardMapper;
import com.czwx.imall.core.mapper.BankListMapper;
import com.czwx.imall.core.mapper.UserMapper;
import com.czwx.imall.core.service.BankCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import tool.util.StringUtil;



/**
 * 银行卡ServiceImpl
 * @version 1.0.0
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("bankCardService")
@Transactional(rollbackFor = Exception.class)
public class BankCardServiceImpl extends BaseServiceImpl<BankCard, Long> implements BankCardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BankCardServiceImpl.class);
   
    @Resource
    private BankCardMapper bankCardMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private BankListMapper bankListMapper;
    
	@Override
	public BaseMapper<BankCard, Long> getMapper() {
		return bankCardMapper;
	}

	@Override
	public boolean save(BankCard bankCard) {
		int result = bankCardMapper.save(bankCard);
		if (result > 0) {
			return true;
		}
		return false;
	}



	@Override
	public BankCard getBankCardByUserId(Long userId) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("useNow", "1");
			return bankCardMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public BankCard findSelective(Map<String, Object> paramMap) {
		return bankCardMapper.findSelective(paramMap);
	}

	@Override
	public int cancelAuthSign(BankCard card) {
		return 0;
	}


	@Override
	public boolean updateSelective(Map<String, Object> paramMap) {
		int result = bankCardMapper.updateSelective(paramMap);
		if (result > 0L) {
			return true;
		}
		return false;
	}


	
	/**
	 * 获取银行列
	 * @return
	 */
	@Override
	public List<BankList> getBankList() {
		return bankListMapper.getBankList();
	}

	@Override
	public boolean update(BankCard bankCard) {
		int result = bankCardMapper.update(bankCard);
		if (result > 0L) {
			return true;
		}
		return false;
	}


	@Override
	public int insert(BankCard record) {
		return 0;
	}

	@Override
	public int updateById(BankCard record) {
		return 0;
	}

	@Override
	public BankCard getById(Long aLong) {
		return null;
	}
}
