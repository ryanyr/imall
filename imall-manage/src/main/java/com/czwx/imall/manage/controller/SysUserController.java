package com.czwx.imall.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.exception.ServiceException;
import com.czwx.imall.manage.common.helper.PasswordHelper;
import com.czwx.imall.manage.common.page.PageParam;
import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.model.SysUserModel;
import com.czwx.imall.system.service.SysRoleService;
import com.czwx.imall.system.service.SysUserService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户管理
 */
@Controller
public class SysUserController extends ImBaseController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("sys/sysUser/list")
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/sys/sysUser/userList");

        PageParam pageParam = pageinator(request);
        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        Page<SysUser> page = sysUserService.listSysUser(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    /**
     * 系统用户详情
     * @param request
     * @param userId
     * @return
     * @throws ServiceException
     */
    @RequestMapping("sys/sysUser/userInfo")
    public ModelAndView userInfo(HttpServletRequest request, @RequestParam("userId") Long userId) throws ServiceException {
        ModelAndView mv = new ModelAndView("/sys/sysUser/userInfo");

        SysUserModel sysUser = sysUserService.getModelById(userId);
        mv.addObject("sysUser", sysUser);

        return mv;
    }

    /**
     * 系统用户修改
     * @param request
     * @param userId
     * @return
     * @throws ServiceException
     */
    @RequestMapping("sys/sysUser/userEdit")
    public ModelAndView userEdit(HttpServletRequest request, @RequestParam("userId") Long userId) throws ServiceException {
        ModelAndView mv = new ModelAndView("/sys/sysUser/userEdit");

        SysUserModel sysUser = sysUserService.getModelById(userId);
        mv.addObject("sysUser", sysUser);

        List<Map<String, Object>> roleList = sysRoleService.fetchAllRoles();

        List<SysRole> roles = sysUser.getRoles();
        if (roles != null && roles.size() > 0){
            for (Map<String, Object> map : roleList) {
                for (SysRole role : roles) {
                    if (role.getRoleId().equals(map.get("roleId"))) {
                        map.put("selected", "selected");
                        break;
                    }
                }
            }
        }
        mv.addObject("roleList", roleList);

        return mv;
    }

    @RequestMapping("sys/sysUser/userAdd")
    public ModelAndView userAdd(HttpServletRequest request) throws ServiceException {
        ModelAndView mv = new ModelAndView("/sys/sysUser/userAdd");

        List<Map<String, Object>> roleList = sysRoleService.fetchAllRoles();
        mv.addObject("roleList", roleList);

        return mv;
    }

    @RequestMapping("sys/sysUser/save")
    public ModelAndView userSave(HttpServletRequest request, SysUser user) throws ServiceException {
        ModelAndView mv = new ModelAndView("redirect:/sys/sysUser/list");

        String roleIds = request.getParameter("userRoles");
        List<Long> userRoleList = JSONObject.parseArray("[" + roleIds + "]", Long.class);
        sysUserService.addSysUser(user, userRoleList);

        return mv;
    }

    @RequestMapping("sys/sysUser/userUpdate")
    public ModelAndView userUpdate(HttpServletRequest request, @RequestParam("userId") Long userId) throws ServiceException {
        ModelAndView mv = new ModelAndView("redirect:/sys/sysUser/list");

        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);

        String roleIds = request.getParameter("userRoles");
        List<Long> userRoleList = JSONObject.parseArray("[" + roleIds + "]", Long.class);
        sysUserService.updateSysUser(sysUser, userRoleList);

        return mv;
    }

    @RequestMapping("sys/sysUser/checkPassword")
    @ResponseBody
    public JSONObject checkPassword(HttpServletRequest request,
                                    @RequestParam("oldPassword") String oldPassword) {
        JSONObject result = new JSONObject();
        SysUser sysUser = getLoginUser(request);

        try {
            int count = sysUserService.checkPassword(sysUser.getUserId(), oldPassword);

            if (count == 1) {
                result.put("code", "200");
                result.put("msg", "密码正确");
            } else {
                result.put("code", "400");
                result.put("msg", "密码不正确");
            }
        } catch (Exception e) {
            result.put("code", "500");
            result.put("msg", "服务器错误");
        }
        return result;
    }

    @RequestMapping("sys/sysUser/changePassword")
    @ResponseBody
    public JSONObject changePassword(HttpServletRequest request,
                                     @RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("password") String password,
                                     @RequestParam("passwordConfirm") String passwordConfirm) throws ServiceException {
        JSONObject result = new JSONObject();

        SysUser sysUser = getLoginUser(request);
        sysUser.setPassword(password);
        PasswordHelper helper=new PasswordHelper();
        helper.encryptPassword(sysUser);
        sysUserService.updateUser(sysUser);

        result.put("code", "200");

        return result;
    }

}
