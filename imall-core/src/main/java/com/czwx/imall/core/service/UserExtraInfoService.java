package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.UserExtraInfo;
import java.util.List;
import java.util.Map;


/**
 * 
 * 用户额外信息
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserExtraInfoService extends BaseService<UserExtraInfo, Long> {

	/**
	 * 保存记录
	 * @param
	 * @return
	 */
	boolean save(UserExtraInfo userExtraInfo);


	UserExtraInfo getByUserId(Long userId);

	boolean update(UserExtraInfo userExtraInfo);

}
