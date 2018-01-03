package com.czwx.imall.core.service;
import java.util.Map;

/**
 * 用户登录接口
 * Created by Administrator on 2017/12/13.
 */

public interface LoginService  {

    /**
     * 根据手机判断是否是第一次登录
     * @param phone
     * @return
     */
//    Map<String,Object> findByPhone(String phone);

    /**
     * 同步短信发送通用接口
     * @param type 短信类型
     * @param data	数据
     * @param phone 手机号
     * @return
     */
    Map sendSms(String type, Map<String, Object> data, String phone);


    /**
     * 用户通过验证码登录
     * @param phone
     * @param code
     * @param type
     * @return
     */
    int login(String phone, String code, String type, String inviteCode);




}
