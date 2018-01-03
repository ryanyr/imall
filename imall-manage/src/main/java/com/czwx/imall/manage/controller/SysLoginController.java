package com.czwx.imall.manage.controller;

import com.czwx.imall.core.mapper.OrderMapper;
import com.czwx.imall.core.mapper.RepayLogMapper;
import com.czwx.imall.core.mapper.UserMapper;
import com.czwx.imall.core.model.CountModel;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.service.SysMenuService;
import com.czwx.imall.system.service.SysUserService;
import com.czwx.imall.manage.common.base.BaseController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SysLoginController extends BaseController{
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RepayLogMapper repayLogMapper;

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView main2(HttpServletRequest request) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return new ModelAndView("index");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                              @RequestParam String username, @RequestParam String password) {

        UsernamePasswordToken token = new UsernamePasswordToken(username,
                password);
        token.setHost(request.getRemoteHost());
        token.setRememberMe(false);
        // shiro 登录
        Subject subject = SecurityUtils.getSubject();
        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            subject.login(token);
            request.setAttribute("successMsg", "登录成功");

            // 更新登录时间
            SysUser infoDO= sysUserService.queryUserInfo(username);
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("userId", infoDO.getUserId());
            map.put("loginTime", new Date());
            sysUserService.updateUser2(map);


            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message_login", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message_login", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message_login", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message_login", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            // 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            request.setAttribute("message_login", "用户名或密码不正确");
        }
        if(request.getAttribute("message_login")!=null){
            return new ModelAndView("login");
        }
        // Map<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView(BASE_REDIRECT_MARK + "workbench");
        //return new ModelAndView("login",requestMap);
    }

    /**
     * 我的工作台
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "workbench", method = RequestMethod.GET)
    public ModelAndView workbench(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", getSessionUser().getUserId());
        map.put("permValid",1);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);

        CountModel count = new CountModel();
        long registerCount = userMapper.selectRegisterCount(dateNowStr);
        long loginCount = userMapper.selectLoginCount(dateNowStr);
        long applyCount = orderMapper.selectApply(dateNowStr);
        long pass = orderMapper.selectPass(dateNowStr);
        double passRate = pass/(double)applyCount;
        BigDecimal loanAmount = orderMapper.selectLoanAmount(dateNowStr);
        BigDecimal repayment = repayLogMapper.selectRepayment(dateNowStr);
        long overdue = orderMapper.selectOverdue(dateNowStr);
        count.setRegisterCount(registerCount);
        count.setLoginCount(loginCount);
        count.setApplyCount(applyCount);
        count.setPass(pass);
        count.setPassRate(passRate+"%");
        count.setLoanAmount(loanAmount==null?new BigDecimal(BigInteger.ZERO):loanAmount);
        count.setRepayment(repayment);
        count.setOverdue(overdue);

        long registerSum = userMapper.selectRegisterCount("");
        count.setRegisterCount(registerSum);

        map.put("count",count);

        return new ModelAndView("index",map);
    }
}
