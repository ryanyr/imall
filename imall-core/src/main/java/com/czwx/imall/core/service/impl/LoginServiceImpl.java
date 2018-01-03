package com.czwx.imall.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.DateUtil;
import com.czwx.imall.core.common.util.HttpUtil;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.domain.*;
import com.czwx.imall.core.mapper.*;
import com.czwx.imall.core.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/12/13.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    public static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private SmsMapper smsMapper;
    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
    private UserInviteMapper userInviteMapper;

    @Resource
    private UserCreditMapper userCreditMapper;

    /**
     * 发送短信
     * @param type 短信类型
     * @param data	数据
     * @param phone 手机号
     * @return
     */
    @Override
    public Map sendSms(String type, Map<String, Object> data, String phone) {

        data = new HashMap<String, Object>();
        data.put("phone", phone);
        data.put("smsType", type);
        String mostTimes = Global.getValue("sms_day_most_times");
        int mostTime = JSONObject.parseObject(mostTimes).getIntValue("verifyTime");
        int times = smsMapper.countDayTime(data);
        if(times > mostTime){
            Map ret = new LinkedHashMap();
            ret.put("success", false);
            ret.put("msg", "您今日的短信验证已达上限");
            return ret;
        }
        data.clear();
        data.put("phone", phone);
        data.put("smsType", type);
        data.put("state", "10");
        Sms sms = smsMapper.findTimeMsg(data);
        if (sms != null) {
          if(StringUtil.equals("00", sms.getState())){
               Map ret = new LinkedHashMap();
               ret.put("success", false);
               ret.put("msg", "发送失败");
                return ret;
            }
        }
        boolean isSendVcode = "register".equals(type);
            if (isSendVcode) {
                int code = (int) (Math.random() * 9000) + 1000;
                if (data == null) {
                    data = new HashMap<String, Object>();
                }
                data.put("code", code);
            }
        String result = sendMsg(type, data, phone);
        logger.info(result);
        return result(result, phone, type);
    }
        /**
         * 登录
         * @param phone
         * @param code 0 错误  -1 验证码过时  1第一次登录成功  2 登录成功不是第一次
         * @return
         */

        @Override
        public int login (String phone, String code, String type,String invitationCode ){
            if (StringUtil.isBlank(phone) || StringUtil.isBlank(code)) {
                return 0;
            }
            if (!StringUtil.isPhone(phone)) {
                return 0;
            }
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("phone", phone);
            data.put("smsType", type);
            Sms sms = smsMapper.findTimeMsg(data);
            long timeLimit = Long.parseLong(Global.getValue("sms_time_limit"));
            if (sms != null) {
                Date d1 = sms.getSendTime();
                Date d2 = DateUtil.getNow();
                long diff = d2.getTime() - d1.getTime();
                if (diff > timeLimit * 60 * 1000) {
                    return -1;
                }
                if (sms.getCode().equals(code) && sms.getPhone().equals(phone)&&sms.getState().equals("10")) {
                    User user = userMapper.findByLoginName(phone);
                    if (user != null) {
                        //该用户不是第一次登录
                        user.setLoginPwd(code);
                        user.setLoginTime(new Date());
                        userMapper.updateByPrimaryKeySelective(user);
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", sms.getId());
                        map.put("state", "20");
                        smsMapper.updateSelective(map);
                        return 2;

                    } else {
                        User loginUser = new User();
                        loginUser.setInvitationCode(randomInvitationCode(6));
                        loginUser.setLoginPwd(code);
                        loginUser.setLoginTime(new Date());
                        loginUser.setLoginName(phone);
                        loginUser.setRegistTime(new Date());
                        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                        loginUser.setUuid(uuid);
                        userMapper.save(loginUser);
                        //如果是第一次给该用户初始的信用额
                        User us=userMapper.findByLoginName(phone);
                        if(us!=null){
                            UserCredit userCredit = new UserCredit();
                            userCredit.setCreateTime(new Date());
                            userCredit.setUserId(us.getId());
                            userCredit.setUsed(new BigDecimal(0));
                            userCredit.setTotal(new BigDecimal(Global.getValue("init_credit_amount")));
                            userCredit.setUnuse(new BigDecimal(Global.getValue("init_credit_amount")));
                            userCredit.setState("10");
                            userCreditMapper.save(userCredit);
                        }
                        //根据邀请码查找邀请人
                    if(tool.util.StringUtil.isNotBlank(invitationCode)){
                        User iu=userMapper.findByInvitationCode(invitationCode);
                        if(iu!=null){
                            UserInvite invite = new UserInvite();
                            //邀请人id
                            invite.setUserId(iu.getId());
                            UserBaseInfo info=userBaseInfoMapper.findByUserId(iu.getId());
                            if(info!=null){
                            invite.setUserName(info.getRealName());
                            }
                            invite.setInviteTime(new Date());
                            User usr=userMapper.findByLoginName(phone);
                            if(usr!=null){
                                invite.setInviteId(usr.getId());
                            }
                            userInviteMapper.save(invite);
                        }
                    }
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", sms.getId());
                        map.put("state", "20");
                        smsMapper.updateSelective(map);
                        return 1;
                    }
                }
            }
            return 0;
        }

    private Map result(String result,String phone,String type){
        JSONObject resultJson = JSONObject.parseObject(result);
        Map ret = new HashMap();
        Integer code;
        if (StringUtil.isNotBlank(resultJson)) {
            code = resultJson.getInteger("code");
            Sms sms = new Sms();
            User user = userMapper.findByLoginName(phone);
            if(user!=null){
                //查到了  说明不是第一次登录
                ret.put("state","2");
            }else{
                //是第一次登录
                ret.put("state","1");
            }
            if (code == 200) {

                JSONObject data = resultJson.getJSONObject("data");
                String orderNo = StringUtil.isNull(data.getString("orderNo"));
                JSONObject params = data.getJSONObject("params");
                String tempCode = params.getString("code");

                sms.setPhone(phone);
                sms.setSendTime(DateUtil.getNow());
                sms.setContent(resultJson.toString());
                sms.setRespTime(DateUtil.getNow());
                sms.setResp("短信已发送");
                sms.setSmsType(type);
                sms.setCode(StringUtil.isNull(tempCode));
                sms.setOrderNo(orderNo);
                sms.setState("10");//10短信未使用
                sms.setVerifyTime(0);
                smsMapper.save(sms);
               ret.put("success",true);
               ret.put("msg","短信发送成功");
            } else {
                sms.setPhone(phone);
                sms.setSendTime(DateUtil.getNow());
                sms.setContent(resultJson.getString("message"));
                sms.setRespTime(DateUtil.getNow());
                sms.setResp("短信发送失败");
                sms.setSmsType(type);
                sms.setCode("");
                sms.setOrderNo("");
                sms.setState("00"); //发送失败是00  发送成功是10 和 20  其中未使用验证码是10  已经使用验证过是20
                sms.setVerifyTime(0);
                smsMapper.save(sms);
                ret.put("success",false);
                ret.put("msg","短信发送失败");
            }
        }
        return ret;

    }

    private String sendMsg(String type, Map<String, Object> data, String mobile) {
        StringBuilder tplValue = new StringBuilder();
        if (data != null && data.size() > 0) {
            tplValue.append("{");

            Set<String> keySet = data.keySet();
            Iterator<String> it = keySet.iterator();
            String key = "";
            while (it.hasNext()) {
                key = it.next();
                tplValue.append(key).append(":").append(data.get(key));
                if (it.hasNext()) {
                    tplValue.append(",");
                }
            }
            tplValue.append("}");
        }

        Map<String, String> param = new HashMap<String, String>();
        param.put("busi_id","100002");   //"manage":100001  "app":100002
        param.put("busi_key","c33367701511b4f6020ec61ded352059");  //busKey:654321
        param.put("sms_type", type);
        param.put("mobile", mobile);
        param.put("tpl_value", tplValue.toString());

        String url = Global.getValue("sms_apiService");
        String result="";
        if("dev".equals(Global.getValue("app_environment"))){
            result=Global.getValue("sms_success");
        }else{
            result = HttpUtil.doPost(url,param);
        }


        return result;
    }


//    @Override
//    public Map<String,Object> findByPhone(String phone) {
//        User user=userMapper.findByLoginName(phone);
//
//       Map<String,Object> result = new HashMap<>();
//        if(!StringUtil.isPhone(phone) ){
//            result.put("msg", "你输入的手机号有误");
//            return result;
//        }
//
//        if(user!=null){
//            result.put("success",true);
//            result.put(Constant.RESPONSE_DATA, "已激活");
//        }else{
//            result.put("success",false);
//            result.put(Constant.RESPONSE_DATA, "未激活");
//        }
//            return result;
//    }

    private String randomInvitationCode(int len) {
        while (true) {
            String str = randomNumAlph(len);
            if ( userMapper.findByInvitationCode(str)== null) {
                return str;
            }
        }
    }



    private static String randomNumAlph(int len) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        byte[][] list = {
                {48, 57},
                {97, 122},
                {65, 90}
        };
        for (int i = 0; i < len; i++) {
            byte[] o = list[random.nextInt(list.length)];
            byte value = (byte) (random.nextInt(o[1] - o[0] + 1) + o[0]);
            sb.append((char) value);
        }
        return sb.toString();
    }
}
