package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.UserAuth;
import com.czwx.imall.core.domain.UserExtraInfo;
import com.czwx.imall.core.mapper.UserAuthMapper;
import com.czwx.imall.core.mapper.UserExtraInfoMapper;
import com.czwx.imall.core.service.UserAuthService;
import com.czwx.imall.core.service.UserExtraInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 
 * 用户认证信息
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userAuthService")
@Transactional(rollbackFor = Exception.class)
public class UserAuthServiceImpl extends BaseServiceImpl<UserAuth, Long> implements UserAuthService {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

	@Resource
	private UserAuthMapper userAuthMapper;


	@Override
	public BaseMapper<UserAuth, Long> getMapper() {
		return userAuthMapper;
	}

	@Override
	public boolean save(UserAuth userAuth) {
		int result = userAuthMapper.insert(userAuth);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserAuth getByUserId(Long userId) {
		return userAuthMapper.selectByUserId(userId);
	}


	@Override
	public boolean update(UserAuth userAuth) {
		int result = userAuthMapper.update(userAuth);
		if (result > 0L) {
			return true;
		}
		return false;
	}


}
