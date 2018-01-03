package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.UserMakeMoneyInfo;
/**
 * 
 * 用户额外信息
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public interface UserMakeMoneyInfoService extends BaseService<UserMakeMoneyInfo, Long> {

	/**
	 * 保存记录
	 * @param
	 * @return
	 */
	boolean save(UserMakeMoneyInfo userMakeMoneyInfo);



	boolean update(UserMakeMoneyInfo userMakeMoneyInfo);

}
