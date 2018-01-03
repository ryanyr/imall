package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.BankList;
import com.czwx.imall.core.domain.CreditCard;
import com.czwx.imall.core.mapper.CreditCardMapper;
import com.czwx.imall.core.mapper.BankListMapper;
import com.czwx.imall.core.mapper.CreditCardMapper;
import com.czwx.imall.core.mapper.UserMapper;
import com.czwx.imall.core.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 信用卡卡ServiceImpl
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("creditCardService")
@Transactional(rollbackFor = Exception.class)
public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long> implements CreditCardService {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);
   
    @Resource
    private CreditCardMapper creditCardMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private BankListMapper bankListMapper;
    


	@Override
	public boolean save(CreditCard CreditCard) {
		int result = creditCardMapper.save(CreditCard);
		if (result > 0) {
			return true;
		}
		return false;
	}



	@Override
	public CreditCard getCreditCardByUserId(Long userId) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("bankType", "1");
			paramMap.put("useNow", "1");
			return creditCardMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	@Override
	public CreditCard findSelective(Map<String, Object> paramMap) {
		return creditCardMapper.findSelective(paramMap);
	}

	@Override
	public int cancelAuthSign(CreditCard card) {
		return 0;
	}


	@Override
	public boolean updateSelective(Map<String, Object> paramMap) {
		int result = creditCardMapper.updateSelective(paramMap);
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
	public boolean update(CreditCard CreditCard) {
		int result = creditCardMapper.update(CreditCard);
		if (result > 0L) {
			return true;
		}
		return false;
	}


	@Override
	public int insert(CreditCard record) {
		return 0;
	}

	@Override
	public int updateById(CreditCard record) {
		return 0;
	}

	@Override
	public BaseMapper<CreditCard, Long> getMapper() {
		return creditCardMapper;
	}

	@Override
	public CreditCard getById(Long aLong) {
		return null;
	}
}
