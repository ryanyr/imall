package com.czwx.imall.manage.controller;

import com.czwx.imall.core.common.exception.ServiceException;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.page.PageParam;
import com.czwx.imall.system.domain.SysConfig;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.service.SysConfigService;
import com.github.pagehelper.Page;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统配置管理
 */
@Controller
public class SysConfigController extends ImBaseController {

    @Resource
    private SysConfigService sysConfigService;

    @RequestMapping("sys/sysConfig/list")
    public ModelAndView userList(HttpServletRequest request) throws ServiceException {
        ModelAndView mv = new ModelAndView("/sys/sysConfig/configList");


        PageParam pageParam = pageinator(request);
        Map<String, Object> searchMap = new HashMap<>();
        Page<SysConfig> page = sysConfigService.getSysConfigPageList(pageParam.getCurrent(), pageParam.getPageSize(), searchMap);

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    @RequestMapping("sys/sysConfig/toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/sys/sysConfig/configAdd");

        return mv;
    }

    @RequestMapping("sys/sysConfig/save")
    public ModelAndView roleSave(HttpServletRequest request, SysConfig sysConfig) throws ServiceException {
        ModelAndView mv = new ModelAndView("redirect:/sys/sysConfig/list");

        SysUser loginUser = getLoginUser(request);
        sysConfig.setStatus(1);
        sysConfig.setCreator(loginUser.getUserId());

        sysConfigService.insertSysConfig(sysConfig);

        return mv;
    }

    @RequestMapping("sys/sysConfig/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, Long configId) {
        ModelAndView mv = new ModelAndView("/sys/sysConfig/configEdit");

        SysConfig sysConfig = sysConfigService.getSysConfigInfo(configId);

        mv.addObject("sysConfig", sysConfig);

        return mv;
    }

    @RequestMapping("sys/sysConfig/update")
    public ModelAndView configUpdate(HttpServletRequest request, SysConfig sysConfig) throws ServiceException {
        ModelAndView mv = new ModelAndView("redirect:/sys/sysConfig/list");

        sysConfigService.updateSysConfig(sysConfig);

        return mv;
    }
}
