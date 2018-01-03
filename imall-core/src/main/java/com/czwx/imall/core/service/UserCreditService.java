package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.UserCredit;

import java.util.Map;

public interface UserCreditService extends BaseService<UserCredit, Long> {

    /**
     * 据条件查询用户授信信息
     *
     * @param param
     * @return
     */
    UserCredit findSelective(Map<String, Object> param);

    /**
     * 据用户名查询用户授信信息
     *
     * @param userId
     * @return
     */
    UserCredit findByUserId(long userId);

    /**
     * 更新用户授信额度
     * @param userId
     * @param amount
     * @param type
     * @return
     */
    int modifyCredit(Long userId, double amount, String type);


    int updateByUserId(UserCredit userCredit);
}
