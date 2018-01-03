package com.czwx.imall.api.user.service;

import com.czwx.imall.api.user.bean.AppDbSession;
import com.czwx.imall.core.common.util.MapUtil;
import com.czwx.imall.core.common.util.SqlUtil;
import com.czwx.imall.api.user.bean.AppSessionBean;
import com.czwx.imall.core.model.SmsModel;
import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tool.util.BeanUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by lsk on 2016/7/27.
 */
@Service("clUserService_")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserService {
	
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AppDbSession appDbSession;

    @Resource
    protected DBService dbService;

    @Resource
    protected MybatisService mybatisService;

    @Resource
    protected SmsService smsService;

    @Resource
    private LoginService loginService;

    public UserService() {
        super();
    }

	@Transactional
    public Map registerUser(HttpServletRequest request, String phone, String pwd, String vcode, String invitationCode,
                            String registerCoordinate,String registerAddr,String regClient, String signMsg, String channelCode,String markChannel,Long drainageId) {
        try {
            if (StringUtil.isEmpty(phone) || !StringUtil.isPhone(phone) || StringUtil.isEmpty(pwd) || StringUtil.isEmpty(vcode) || pwd.length() < 32) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "参数有误");
                return ret;
            }

            Map invitor = null;
            if (!StringUtil.isEmpty(invitationCode)) {
                invitor = mybatisService.queryRec("usr.queryUserByInvitation", invitationCode);
                if (invitor == null) {
                    Map ret = new LinkedHashMap();
                    ret.put("success", false);
                    ret.put("msg", "邀请人不存在");
                    return ret;
                }
            }
//
            Map old = mybatisService.queryRec("usr.queryUserByLoginName", phone);
            if (old != null) {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "该手机号码已被注册");
                return ret;
            }
            
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            long userId = dbService.insert(SqlUtil.buildInsertSqlMap("im_user", new Object[][]{
                {"login_name", phone},
                {"login_pwd", pwd},
                {"invitation_code", randomInvitationCode(6)},
                {"regist_time", new Date()},
                {"uuid", uuid},
                {"level", 3},
                {"register_client", regClient},
                {"mark_channel", markChannel}
            }));

            dbService.insert(SqlUtil.buildInsertSqlMap("im_user_base_info", new Object[][]{
                {"user_id", userId},
                {"phone", phone},
                {"register_coordinate", registerCoordinate},
                {"register_addr", registerAddr}
            }));

            dbService.insert(SqlUtil.buildInsertSqlMap("arc_credit", new Object[][]{
            		{"user_id", userId},
            		{"consumer_no", userId},
                {"total", Global.getDouble("init_credit")},
                {"unuse", Global.getDouble("init_credit")},
                {"state", 10}
            }));
            dbService.insert(SqlUtil.buildInsertSqlMap("im_profit_amount", new Object[][]{
                {"user_id", userId},
                {"state", "10"}
            }));

            dbService.insert(SqlUtil.buildInsertSqlMap("im_user_auth", new Object[][]{
                {"user_id", userId},
                {"id_state", 10},
                {"zhima_state", 10},
                {"phone_state", 10},
                {"contact_state", 10},
                {"bank_card_state", 10},
                {"work_info_state", 10},
                {"other_info_state", 10},
            }));

            if (invitor != null) {
                dbService.insert(SqlUtil.buildInsertSqlMap("im_user_invite", new Object[][]{
                    {"invite_time", new Date()},
                    {"invite_id", userId},
                    {"invite_name", phone},
                    {"user_id", invitor.get("id")},
                    {"user_name", invitor.get("login_name")},
                }));
            }
            
            //2017.5.6 仅用于demo演示环境
//            demoUser(userId);
            
            Map result = new LinkedHashMap();
            result.put("success", true);
            result.put("msg", "注册成功");
            return result;
        } catch (Exception e) {
            logger.error("注册失败", e);
            Map ret = new LinkedHashMap();
            ret.put("success", false);
            ret.put("msg", "注册失败");
            return ret;
        }
    }
	
    private String randomInvitationCode(int len) {
        while (true) {
            String str = randomNumAlph(len);
            if (mybatisService.queryRec("usr.queryUserByInvitation", str) == null) {
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


    public Object forgetPwd(String phone, String newPwd, String vcode,String signMsg) {
             
            if (!StringUtil.isEmpty(vcode)) {
                String msg = smsService.validateSmsCode(phone, SmsModel.SMS_TYPE_FINDREG , vcode);
                if (msg != null) {
                    Map ret = new LinkedHashMap();
                    ret.put("success", false);
                    ret.put("msg", msg);
                    return ret;
                }
            }


            if (dbService.update(SqlUtil.buildUpdateSql("im_user", MapUtil.array2Map(new Object[][]{
                {"login_name", phone},
                {"login_pwd", newPwd},
                {"loginpwd_modify_time", new Date()}
            }), "login_name")) == 1) {
                Map ret = new LinkedHashMap();
                ret.put("success", true);
                ret.put("msg", "密码已修改");
                return ret;
            } else {
                Map ret = new LinkedHashMap();
                ret.put("success", false);
                ret.put("msg", "密码修改失败");
                return ret;
            }

    }

    public Map login(final HttpServletRequest request, final String loginName,
                     final String loginPwd,String invitationCode, String blackBox) {
//        try {
//        	/*String _signMsg = MD5.encode(Global.getValue("app_key") + loginName + loginPwd);
//            if (!_signMsg.equalsIgnoreCase(signMsg)) {
//                Map ret = new LinkedHashMap();
//                ret.put("success", false);
//                ret.put("msg", "签名验签不通过");
//                return ret;
//            }*/
//
//            Map user = mybatisService.queryRec("usr.queryUserByLoginName", loginName);
//            if (user == null) {
//                Map ret = new LinkedHashMap();
//                ret.put("success", false);
//                ret.put("msg", "账户不存在");
//                return ret;
//            }
        try {

            String type = Global.getValue("sms_type");
            int state = loginService.login(loginName, loginPwd, type,invitationCode);
            Map ret = new LinkedHashMap();
            if (state == 1 || state == 2) {
                AppSessionBean session = appDbSession.create(request, loginName);

                // Date loginTime = new Date();
                //dbService.update("update im_user set login_time=? where login_name=?", loginTime, loginName);//设置当前时间为登录时间

                ret.put("state", state);
                ret.put("msg", "登录成功");
                ret.put("data", session.getFront());

            }
           else if (state == 0) {

                ret.put("state", state);
                ret.put("msg", "你输入参数有误");

            }
            else  if (state == -1) {
                ret.put("state", state);
                ret.put("msg", "登录超时,请重新登录");
            }
            return ret;
        } catch (Exception e) {
            logger.error("登录异常", e);
            Map ret = new LinkedHashMap();
            ret.put("code", 500);
            ret.put("msg", "系统异常");
            return ret;
        }
    }
}