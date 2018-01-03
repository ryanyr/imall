package com.czwx.imall.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.context.Constant;
import com.czwx.imall.core.common.util.JsonUtil;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.system.domain.SysMenu;
import com.czwx.imall.system.service.SysMenuService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 菜单管理
 */
@Controller
public class SysMenuController extends ImBaseController {

    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping("sys/sysMenu/list")
    public ModelAndView menuList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/sys/sysMenu/menuList");

        List<SysMenu> list = sysMenuService.listSysMenu();
        list = list2Tree(list, "ROOT");

        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping("sys/sysMenu/getMenuInfo")
    @ResponseBody
    public JSONObject menuInfo(HttpServletRequest request, Long menuId) {
        JSONObject responseMap = new JSONObject();

        SysMenu sysMenu = sysMenuService.getSysMenu(menuId);

        JSONObject data = new JSONObject();
        data.put("menu", sysMenu);

        responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        responseMap.put(Constant.RESPONSE_CODE_MSG, "获取成功");
        responseMap.put(Constant.RESPONSE_DATA, data);

        return  responseMap;
    }

    @RequestMapping("sys/sysMenu/saveOrUpdate")
    @ResponseBody
    public JSONObject saveOrUpdateMenu(HttpServletRequest request,
                                       @RequestParam(value = "menu", required = false) String data,
                                       @RequestParam(value = "type", required = false) String type) {
        JSONObject responseMap = new JSONObject();

        Map<String, Object> dataMap = JsonUtil.parse(data, Map.class);

        if ("create".equalsIgnoreCase(type)) {
            int n = sysMenuService.addMenu(dataMap);
            if (n > 0) {
                responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
            } else {
                responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
            }
        } else if ("update".equalsIgnoreCase(type)) {
            int total = sysMenuService.updateMenu(dataMap);
            if (total > 0) {
                responseMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
                responseMap.put(Constant.RESPONSE_CODE_MSG, "保存成功");
            } else {
                responseMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
                responseMap.put(Constant.RESPONSE_CODE_MSG, "保存失败");
            }
        }

        responseMap.put("code", "200");
        responseMap.put("msg", "");
        return  responseMap;
    }

    List<SysMenu> list2Tree(List<SysMenu> list, String str) {
        List<SysMenu> tree = new ArrayList<SysMenu>();
        for (int i = 0; i < list.size(); i++) {
            SysMenu do1 = list.get(i);
            if (str.equals(do1.getSuperiorId())) {
                do1.setMelist(list2Tree(list, do1.getMenuId()));
                tree.add(do1);
            }
        }
        Collections.sort(tree, new Comparator<SysMenu>() {

            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                if (o1.getMenuOrder() < o2.getMenuOrder()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return tree;
    }
}
