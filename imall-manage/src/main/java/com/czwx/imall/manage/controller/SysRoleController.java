package com.czwx.imall.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.page.PageParam;
import com.czwx.imall.system.domain.SysRole;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.service.SysMenuService;
import com.czwx.imall.system.service.SysRoleService;
import com.github.pagehelper.Page;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统角色管理
 */
@Controller
public class SysRoleController extends ImBaseController {

    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping("sys/sysRole/list")
    public ModelAndView roleList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/sys/sysRole/roleList");

        PageParam pageParam = pageinator(request);
        Map<String, Object> searchMap = new HashMap<>();
        Page<SysRole> page = sysRoleService.listSysRoleInfo(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    @RequestMapping("sys/sysRole/roleAdd")
    public ModelAndView roleAdd(HttpServletRequest request, SysRole sysRole) {
        ModelAndView mv = new ModelAndView("/sys/sysRole/roleAdd");

        return mv;
    }

    @RequestMapping("sys/sysRole/save")
    public ModelAndView roleSave(HttpServletRequest request, SysRole sysRole) {
        ModelAndView mv = new ModelAndView("redirect:/sys/sysRole/list");

        SysUser loginUser = getLoginUser(request);
        sysRole.setCreator(loginUser.getUserName());

        sysRoleService.saveRole(sysRole);

        return mv;
    }

    @RequestMapping("sys/sysRole/rolePerm/detail")
    public ModelAndView rolePermAlloc(HttpServletRequest request, @RequestParam("roleId") Long roleId) {
        ModelAndView mv = new ModelAndView("/sys/sysRole/roleDetail");

        SysRole sysRole = sysRoleService.getRoleInfo(roleId);

        mv.addObject("sysRole", sysRole);

        return mv;
    }

    @RequestMapping(value = "sys/sysRole/rolePerm/getMenuTree", method = RequestMethod.GET)
    public void getMenuTree(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
        try {
            request.setCharacterEncoding("utf-8"); // 设置编码
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");

            List<Map<String, Object>> menus;
            String roleIdStr = request.getParameter("roleId");
            if (StringUtils.isNotBlank(roleIdStr)) {
                Long roleId = Long.parseLong(request.getParameter("roleId"));
                menus = sysMenuService.fetchRoleMenuHas(roleId);
                for (Map<String, Object> map : menus) {
                    if (map.get("checked").equals("1")) {
                        map.put("checked", "true");
                        map.put("open", "true");
                    }
                }
            } else {
                menus = sysMenuService.fetchAllMenu();
            }

            String json= JSONArray.toJSONString(menus);
            response.getWriter().write(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("sys/sysRole/rolePerm/update")
    public ModelAndView rolePermAllocUpdate(HttpServletRequest request, @RequestParam("roleId") Long roleId) {
        ModelAndView mv = new ModelAndView("redirect:/sys/sysRole/list");

        String content =  request.getParameter("content");
        List<Long> menuIds = JSONObject.parseArray("[" + content + "]", Long.class);

        sysRoleService.saveOrUpdateRoleMenus(roleId, menuIds);

        return mv;
    }

}
