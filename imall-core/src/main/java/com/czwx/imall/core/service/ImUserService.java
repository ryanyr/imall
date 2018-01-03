package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.User;
import com.czwx.imall.core.model.ImUserModel;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;

/**
 * 用户Service
 *
 */
public interface ImUserService extends BaseService<User, Long> {
	/**
	 * 查询用户详细信息列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ImUserModel> listUser(Map<String, Object> params, int currentPage,
							   int pageSize);

	List<ImUserModel> listUser(Map<String, Object> params);
	
	/**
	 * 查询用户详细信息
	 * @param id
	 * @return
	 */
	ImUserModel getModelById(Long id);
	
	/**
	 * 查询所有相关的数据字典
	 * @return
	 */
	List<Map<String, Object>> findAllDic();


	/**
	 * 据uuid修改用户信息
	 * 
	 * @param paramMap
	 * @return
	 */
	boolean updateByUuid(Map<String, Object> paramMap);
	
	/**
	 * 据用户手机号查询用户
	 * @param phone
	 * @return
	 */
	User findByPhone(String phone);
	
	/**
	 * 今日注册用户数
	 * @return
	 */
	long todayCount();
	
}
