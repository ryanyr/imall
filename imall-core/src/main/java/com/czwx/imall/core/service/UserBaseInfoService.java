package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.InsuranceList;
import com.czwx.imall.core.domain.User;
import com.czwx.imall.core.model.ManagerUserModel;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.czwx.imall.core.domain.UserBaseInfo;


/**
 * 用户详情表Service
 * 
 * @author lyang
 * @version 1.0.0
 * @date 2017-02-14 11:08:04
 * Copyright 杭州融都科技股份有限公司  arc All Rights Reserved
 * 官方网站：www.erongdu.com
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserBaseInfoService extends BaseService<UserBaseInfo, Long> {

	/**
	 * 据userId查询用户基本信息
	 * 
	 * @param userId
	 * @return
	 */
	UserBaseInfo findByUserId(Long userId);

	/**
	 * 据userId查询用户
	 *
	 * @param userId
	 * @return
	 */
	User selectUser(Long userId);

	/**
	 * 据条件查询用户基本信息
	 * 
	 * @param paramMap
	 * @return
	 */
	UserBaseInfo findSelective(Map<String, Object> paramMap);

	List<Map<String, Object>> getDictsCache(String type);
	
	/**
	 * 查询信息
	 * @param id
	 * @return
	 */
	ManagerUserModel getBaseModelByUserId(Long userId);

	/**
	 * 添加取现黑名单
	 * @param id
	 * @param state
	 * @param blackReason
	 * @param blackDesc
	 * @return
	 */
	int updateState(long id, String state,String blackReason,String blackDesc);
	
	/**
	 * 修改用户基本信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateSelective(Map<String, Object> paramMap);

	boolean save(UserBaseInfo info);

	Map<String,Object> getUserInfo(String userId);


	Boolean updateData(UserBaseInfo userBaserInfo);

	List<InsuranceList> getInsuranceList();
	
}
