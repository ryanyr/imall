package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.UserAuth;
import com.czwx.imall.core.domain.UserExtraInfo;


/**
 * 
 * 用户认证信息
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserAuthService extends BaseService<UserAuth, Long> {

	/**
	 * 保存记录
	 * @param userAuth
	 * @return
	 */
	boolean save(UserAuth userAuth);


	UserAuth getByUserId(Long userId);

	boolean update(UserAuth userAuth);

}
