package com.czwx.imall.manage.controller;

import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.core.domain.UserAuth;
import com.czwx.imall.core.mapper.UserAuthMapper;
import com.czwx.imall.core.model.ImUserModel;
import com.czwx.imall.core.model.ManageOrderModel;
import com.czwx.imall.core.service.ImUserService;
import com.czwx.imall.core.service.OrderService;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.mybatisextend.PageContext;
import com.czwx.imall.manage.common.page.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 借款管理
 */
@Controller
public class ImOrderBorrowController extends ImBaseController {

    @Resource
    private OrderService orderService;
    @Resource
    private ImUserService imUserService;
    @Resource
    private UserAuthMapper userAuthMapper;

    /**
     * 借款管理 - 借款订单列表
     * @param request
     * @return
     */
    @RequestMapping("borrow/order/list")
    public ModelAndView onBorrowList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("borrow/orderList");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        searchMap.put("orderNo", request.getParameter("orderNo"));
        searchMap.put("state", request.getParameter("state"));
        searchMap.put("startTime", request.getParameter("startTime"));
        searchMap.put("endTime", request.getParameter("endTime"));

        PageParam pageParam = pageinator(request);
        Page<ManageOrderModel> page = orderService.listOrderModel(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    @RequestMapping("borrow/order/orderDetail")
    public ModelAndView borrowOrderDetail(HttpServletRequest request, @RequestParam("orderId") Long orderId) throws Exception {
        ModelAndView mv = new ModelAndView("borrow/borrowOrderDetail");
        ManageOrderModel order = orderService.getModelByOrderId(orderId);
        ImUserModel user = imUserService.getModelById(order.getUserId());
        UserAuth userAuth = userAuthMapper.selectByUserId(order.getUserId());

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
        mv.addObject("userAuth", userAuth);
        mv.addObject("order", order);

        return mv;
    }

    /**
     * 借款管理 - 放款订单列表
     * @param request
     * @return
     */
    @RequestMapping("borrow/loan/list")
    public ModelAndView onLoanList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("borrow/loanList");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        searchMap.put("orderNo", request.getParameter("orderNo"));
        if (StringUtils.isBlank(request.getParameter("state"))) {
            List stateList;
            stateList = Arrays.asList("40", "50", "60");
            searchMap.put("stateList", stateList);
            searchMap.put("state", "");
        } else {
            searchMap.put("state", request.getParameter("state"));
        }
//        searchMap.put("state", request.getParameter("state"));
        List stateList = Arrays.asList("40", "50", "60");
        searchMap.put("stateList", stateList);

        /*String createTimeRange = request.getParameter("createTimeRange");
        if (StringUtils.isNotBlank(createTimeRange)) {
            String[] timeRange = createTimeRange.split("~");
            searchMap.put("startTime", timeRange[0].trim());
            searchMap.put("endTime", timeRange[1].trim());

            searchMap.put("createTimeRange", request.getParameter("createTimeRange"));
        }*/

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int currentPage = 0, pageSize = 10;
        if (StringUtils.isNotBlank(currentPageStr)){
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (StringUtils.isNotBlank(pageSizeStr)){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageParam pageParam = pageinator(request);
        Page<ManageOrderModel> page = orderService.listOrderModel(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    @RequestMapping("borrow/loan/orderDetail")
    public ModelAndView loanOrderDetail(HttpServletRequest request, @RequestParam("orderId") Long orderId) throws Exception {
        ModelAndView mv = new ModelAndView("borrow/loanOrderDetail");
        ManageOrderModel order = orderService.getModelByOrderId(orderId);
        ImUserModel user = imUserService.getModelById(order.getUserId());
        UserAuth userAuth = userAuthMapper.selectByUserId(order.getUserId());

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
        mv.addObject("userAuth", userAuth);
        mv.addObject("order", order);

        return mv;
    }
}
