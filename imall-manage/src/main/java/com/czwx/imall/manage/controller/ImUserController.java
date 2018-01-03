package com.czwx.imall.manage.controller;

import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.core.model.ImUserModel;
import com.czwx.imall.core.service.ImUserService;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.page.PageParam;
import com.github.pagehelper.Page;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImUserController extends ImBaseController {

    @Resource
    private ImUserService imUserService;

    @RequestMapping("user/userInfo/list")
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("user/userInfoList");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("loginName", request.getParameter("loginName"));
        searchMap.put("realName", request.getParameter("realName"));

        PageParam pageParam = pageinator(request);
        Page<ImUserModel> page = imUserService.listUser(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    @RequestMapping("user/userInfo/userDetail")
    public ModelAndView userDetail(HttpServletRequest request, @RequestParam("userId") Long userId) throws Exception{
        ModelAndView mv = new ModelAndView("user/userDetail");

        ImUserModel user = imUserService.getModelById(userId);

        String serverHost = Global.getValue("server_host");

        if (user != null && user.getId() != null) {
            //拼接文件路径
            user.setLivingImg(user.getLivingImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getLivingImg(), "UTF-8") : null);
            user.setFrontImg(user.getFrontImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getFrontImg(), "UTF-8") : null);
            user.setBackImg(user.getBackImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getBackImg(), "UTF-8") : null);

            user.setBankFrontImg(user.getBankFrontImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getBankFrontImg(), "UTF-8") : null);
            user.setBankBackImg(user.getBankBackImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getBankBackImg(), "UTF-8") : null);

            user.setCreditFrontImg(user.getCreditFrontImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getCreditFrontImg(), "UTF-8") : null);
            user.setCreditBackImg(user.getCreditBackImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getCreditBackImg(), "UTF-8") : null);

            user.setTitleImg(user.getTitleImg() != null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getTitleImg(), "UTF-8") : null);

            user.setCertificateImg(user.getCertificateImg()!= null ? serverHost + "/readFile.htm?path=" + URLEncoder.encode(user.getCertificateImg(), "UTF-8") : null);

        }

        mv.addObject("user", user);
        return mv;
    }
}
