package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.exception.BussinessException;
import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.UserCredit;
import com.czwx.imall.core.mapper.UserCreditMapper;
import com.czwx.imall.core.service.UserCreditService;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service("userCreditService")
@Transactional(rollbackFor = Exception.class)
public class UserCreditServiceImpl extends BaseServiceImpl<UserCredit, Long> implements UserCreditService {

    @Resource
    private UserCreditMapper userCreditMapper;

    @Override
    public BaseMapper<UserCredit, Long> getMapper() {
        return userCreditMapper;
    }

    @Override
    public UserCredit findSelective(Map<String, Object> param) {
        return userCreditMapper.findSelective(param);
    }

    @Override
    public UserCredit findByUserId(long userId) {
        return userCreditMapper.findByUserId(userId);
    }

    @Override
    public int modifyCredit(Long userId, double amount, String type) {
        Map<String, Object> params = new HashMap<String, Object>();
        UserCredit credit = userCreditMapper.findByUserId(userId);
        if (credit != null) {
            params.put("id", credit.getId());
            if ("used".equals(type)) {
                params.put("used", amount);
                params.put("unuse", - amount);
            } else {
                params.put("used", - amount);
                params.put("unuse", amount);
            }
            int result = userCreditMapper.updateCreditAmount(params);

            if(result != 1){
                throw new BussinessException("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
            }
            return result;
        } else {
            throw new BussinessException("更新额度失败，未找到用户额度信息，userId:" + userId);
        }
    }

    @Override
    public int updateByUserId(UserCredit userCredit) {
        return userCreditMapper.updateByUserId(userCredit);
    }


}
