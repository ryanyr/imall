package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.domain.BankCard;
import com.czwx.imall.core.domain.InsuranceList;
import com.czwx.imall.core.domain.User;
import com.czwx.imall.core.mapper.BankCardMapper;
import com.czwx.imall.core.mapper.InsuranceListMapper;
import com.czwx.imall.core.mapper.UserBaseInfoMapper;
import com.czwx.imall.core.mapper.UserMapper;
import com.czwx.imall.core.model.ManagerUserModel;
import com.czwx.imall.core.service.UserBaseInfoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.UserBaseInfo;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户详情表ServiceImpl
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:08:04
 * Copyright 杭州融都科技股份有限公司  cl All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 
@Service("userBaseInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserBaseInfoServiceImpl extends BaseServiceImpl<UserBaseInfo, Long> implements UserBaseInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserBaseInfoServiceImpl.class);
	
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
	private BankCardMapper bankCardMapper;

	@Resource
	private UserMapper userMapper;

    @Resource
	private InsuranceListMapper insuranceListMapper;
 
	@Override
	public BaseMapper<UserBaseInfo, Long> getMapper() {
		return  userBaseInfoMapper;
	}
	
	@Override
	public UserBaseInfo findByUserId(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		UserBaseInfo baseInfo = null;
		try {
			baseInfo = userBaseInfoMapper.findSelective(paramMap);
		} catch (Exception e) {
			logger.error("查询用户基本信息异常", e);
		}

		return baseInfo;
	}

	@Override
	public User selectUser(Long userId) {
		return userMapper.selectByUserId(userId);
	}

	@Override
	public UserBaseInfo findSelective(Map<String, Object> paramMap) {
		return userBaseInfoMapper.findSelective(paramMap);
	}

	@Override
	public List<Map<String, Object>> getDictsCache(String type) {
		return userBaseInfoMapper.getDictsCache(type);
	}

	@Override
	public ManagerUserModel getBaseModelByUserId(Long userId) {
		return userBaseInfoMapper.getBaseModelByUserId(userId);
	}

	@Override
	public int updateState(long id, String state,String blackReason,String blackDesc) {
		int i = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", id);
		UserBaseInfo base=userBaseInfoMapper.findSelective(paramMap);
		if (base != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", base.getId());
			map.put("state", state);
			if(StringUtils.isNotBlank(blackReason)){
				map.put("blackReason", blackReason);
				map.put("blackDesc", blackDesc);
			}
			i = userBaseInfoMapper.updateSelective(map);
		}
		return i;
	}

	@Override
	public boolean updateSelective(Map<String, Object> paramMap) {
		int result = userBaseInfoMapper.updateSelective(paramMap);
		if(result >0L){
			return true;
		}
		return false;
	}

	@Override
	public boolean save(UserBaseInfo info) {
		int result = userBaseInfoMapper.save(info);
		if(result >0L){
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getUserInfo(String userId) {

		//查询用户的基本信息
		try{
			Map<String,Object> ret = userBaseInfoMapper.getUserInfo(userId);
			//封装到Map
			return ret;
		}catch (Exception e) {
			logger.error("查询用户基本信息异常", e);
		}
		return null;
	}

	@Override
	public Boolean updateData(UserBaseInfo userBaserInfo) {
		int result = userBaseInfoMapper.updateByPrimaryKey(userBaserInfo);
		if(result >0L){
			return true;
		}
		return false;
	}

	@Override
	public List<InsuranceList> getInsuranceList() {
		return insuranceListMapper.getInsuranceList();
	}


}




