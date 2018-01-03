package com.czwx.imall.core.service.impl;
import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.UserExtraInfo;
import com.czwx.imall.core.mapper.UserExtraInfoMapper;
import com.czwx.imall.core.service.UserExtraInfoService;
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
 
@Service("userExtraInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserExtraInfoServiceImpl extends BaseServiceImpl<UserExtraInfo, Long> implements UserExtraInfoService {

	private static final Logger logger = LoggerFactory.getLogger(UserExtraInfoServiceImpl.class);

	@Resource
	private UserExtraInfoMapper userExtraInfoMapper;


	@Override
	public BaseMapper<UserExtraInfo, Long> getMapper() {
		return userExtraInfoMapper;
	}

	@Override
	public boolean save(UserExtraInfo UserExtraInfo) {
		int result = userExtraInfoMapper.insert(UserExtraInfo);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserExtraInfo getByUserId(Long userId) {
		return userExtraInfoMapper.findByUserId(userId);
	}


	@Override
	public boolean update(UserExtraInfo UserExtraInfo) {
		int result = userExtraInfoMapper.update(UserExtraInfo);
		if (result > 0L) {
			return true;
		}
		return false;
	}


}
