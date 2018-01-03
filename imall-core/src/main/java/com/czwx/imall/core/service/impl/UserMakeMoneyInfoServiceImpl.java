package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.UserMakeMoneyInfo;
import com.czwx.imall.core.mapper.UserMakeMoneyInfoMapper;
import com.czwx.imall.core.service.UserMakeMoneyInfoService;
import com.czwx.imall.core.service.UserMakeMoneyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 
 * 用户额外信息
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userMakeMoneyInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserMakeMoneyInfoServiceImpl extends BaseServiceImpl<UserMakeMoneyInfo, Long> implements UserMakeMoneyInfoService {

	private static final Logger logger = LoggerFactory.getLogger(UserMakeMoneyInfoServiceImpl.class);

	@Resource
	private UserMakeMoneyInfoMapper userMakeMoneyInfoMapper;


	@Override
	public BaseMapper<UserMakeMoneyInfo, Long> getMapper() {
		return userMakeMoneyInfoMapper;
	}

	@Override
	public boolean save(UserMakeMoneyInfo UserMakeMoneyInfo) {
		int result = userMakeMoneyInfoMapper.insert(UserMakeMoneyInfo);
		if (result > 0) {
			return true;
		}
		return false;
	}


	@Override
	public boolean update(UserMakeMoneyInfo UserMakeMoneyInfo) {
		int result = userMakeMoneyInfoMapper.update(UserMakeMoneyInfo);
		if (result > 0L) {
			return true;
		}
		return false;
	}


}
