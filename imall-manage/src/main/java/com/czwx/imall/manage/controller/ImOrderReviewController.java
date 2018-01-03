package com.czwx.imall.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.DateUtil;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.domain.UserAuth;
import com.czwx.imall.core.mapper.UserAuthMapper;
import com.czwx.imall.core.model.FileTypeUtil;
import com.czwx.imall.core.model.ImUserModel;
import com.czwx.imall.core.model.ManageOrderModel;
import com.czwx.imall.core.model.UploadFileRes;
import com.czwx.imall.core.service.ImUserService;
import com.czwx.imall.core.service.OrderService;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.mybatisextend.PageContext;
import com.czwx.imall.manage.common.page.PageParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import java.net.URLEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 审核管理模块
 */
@Controller
public class ImOrderReviewController extends ImBaseController {

    public static final Logger logger = LoggerFactory.getLogger(ImOrderReviewController.class);

    @Resource
    private OrderService orderService;
    @Resource
    private ImUserService imUserService;
    @Resource
    private UserAuthMapper userAuthMapper;

    /**
     * 审核管理 - 待审核订单列表
     * @param request
     * @return
     */
    @RequestMapping("review/onReview/list")
    public ModelAndView onReviewList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("review/onReviewList");
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        searchMap.put("state", "10"); // 待审核订单

        PageParam pageParam = pageinator(request);
        Page<ManageOrderModel> page = orderService.listOrderModel(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }


    @RequestMapping("review/onReview/orderDetail")
    public ModelAndView onReviewOrderDetail(HttpServletRequest request, @RequestParam("orderId") Long orderId) throws Exception {
        ModelAndView mv = new ModelAndView("review/onReviewDetail");
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
     * 订单人工审核确认
     * @return
     */
    @RequestMapping("review/onReview/userAuthAudit")
    @ResponseBody
    public JSONObject userAuthAudit(@RequestParam(value = "certState", required = true) String certState,
                                    @RequestParam(value = "userId", required = true) Long userId,
                                    @RequestParam(value = "certificateImg", required = true) MultipartFile certificateImg) {
        JSONObject json = new JSONObject();

        ImUserModel user = imUserService.getModelById(userId);
        UserAuth userAuth = userAuthMapper.selectByUserId(userId);

        UploadFileRes file = saveFile(certificateImg);

        if (!userAuth.getCertificateState().equals("30")) {
            userAuth.setCertificateState("30");
            userAuth.setCertificateImg(file.getResPath());
            userAuthMapper.updateByPrimaryKey(userAuth);
        }
        json.put("code", "200");
        json.put("msg", "");

        return json;
    }

    /**
     * 订单人工审核确认
     * @param request
     * @return
     */
    @RequestMapping("review/onReview/orderAudit")
    @ResponseBody
    public JSONObject auditReview(HttpServletRequest request) {
        JSONObject json = new JSONObject();

        String auditState = request.getParameter("auditState");
        String auditRemark = request.getParameter("auditRemark");
        String orderId = request.getParameter("orderId");

        System.out.println(auditState);
        System.out.println(auditRemark);
        System.out.println(orderId);

        ManageOrderModel order = orderService.getModelByOrderId(Long.parseLong(orderId));
        UserAuth userAuth = userAuthMapper.selectByUserId(order.getUserId());

        if (!userAuth.getCertificateState().equals("30")) {
            json.put("code", "400");
            json.put("msg", "客户资格未认证通过，请先对客户资格进行认证");
        } else {
            orderService.reviewAudit(order, auditState, auditRemark);

            json.put("code", "200");
            json.put("msg", "");
        }

        return json;
    }

    @RequestMapping("review/onLoanReview/list")
    public ModelAndView onLoanReviewList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("review/onLoanReviewList");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        searchMap.put("state", "20"); // 人工审核通过

        PageParam pageParam = pageinator(request);
        Page<ManageOrderModel> page = orderService.listOrderModel(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    @RequestMapping("review/onLoanReview/loanOrderDetail")
    public ModelAndView onLoanReviewOrderDetail(HttpServletRequest request, @RequestParam("orderId") Long orderId) throws Exception{
        ModelAndView mv = new ModelAndView("review/onLoanReviewDetail");
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
     * 订单放款审核确认
     * @param request
     * @return
     */
    @RequestMapping("review/onLoanReview/orderAudit")
    @ResponseBody
    public JSONObject loanAuditReview(HttpServletRequest request) {
        JSONObject json = new JSONObject();

        String auditState = request.getParameter("auditState");
        String auditRemark = request.getParameter("auditRemark");
        String orderId = request.getParameter("orderId");

        System.out.println(auditState);
        System.out.println(auditRemark);
        System.out.println(orderId);

        ManageOrderModel order = orderService.getModelByOrderId(Long.parseLong(orderId));
        UserAuth userAuth = userAuthMapper.selectByUserId(order.getUserId());

        if (!userAuth.getCertificateState().equals("30")) {
            json.put("code", "400");
            json.put("msg", "客户资格未认证通过，请先对客户资格进行认证");
        } else {
            orderService.reviewLoanAudit(order, auditState, auditRemark);

            json.put("code", "200");
            json.put("msg", "");
        }

        return json;
    }

    private UploadFileRes saveFile(MultipartFile file) {
        UploadFileRes model = new UploadFileRes();
        model.setCreateTime(DateUtil.getNow());

        // 文件名称
        String picName = file.getOriginalFilename();

        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File newFile = (File) fi.getStoreLocation();
        logger.info("图片----------" + newFile);
        // 文件格式
        String fileType = FileTypeUtil.getFileType(newFile);
        if (StringUtil.isBlank(fileType) || !FileTypeUtil.isImage(newFile, fileType)) {
            model.setErrorMsg("图片格式错误或内容不规范");
            return model;
        }
        // 校验图片大小
        Long picSize = file.getSize();
        if (picSize.compareTo(2097152L) > 0) {
            model.setErrorMsg("图片超出2M大小限制");
            return model;
        }
        // 保存文件
        String s=File.separator;
        String filePath = s+"data"+s+"image"+s + fileType + s + System.currentTimeMillis() + s + picName;
        String dataDir = Global.getValue("file_home");
        if (s.equals("\\")) {//windows
            filePath = "D:" + filePath;
        }
        else {
            filePath = FilenameUtils.getFullPath(dataDir) + "data" + s + "image" + s + fileType + s + System.currentTimeMillis() + s + picName;
        }
        File files = new File(filePath);
        if (!files.exists()) {
            try {
                files.mkdirs();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.setErrorMsg("文件目录不存在");
                return model;
            }
        }
        try {
            file.transferTo(files);
            String serverHost= "";
            if("dev".equals(Global.getValue("app_environment"))){
                serverHost = Global.getValue("local_host");
            }else{
                serverHost= Global.getValue("server_host");
            }

            filePath= serverHost+ "/readFile.htm?path="+ URLEncoder.encode(filePath, "UTF-8");
        } catch (IllegalStateException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        // 转存文件
        model.setResPath(filePath);
        return model;
    }

    /*private UploadFileRes saveFile(MultipartFile file) {
        UploadFileRes model = new UploadFileRes();
        model.setCreateTime(DateUtil.getNow());

        // 文件名称
        String picName = file.getOriginalFilename();

        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File newFile = (File) fi.getStoreLocation();

        // 文件格式
        String fileType = FileTypeUtil.getFileType(newFile);
        if (StringUtil.isBlank(fileType) || !FileTypeUtil.isImage(newFile, fileType)) {
            model.setErrorMsg("图片格式错误或内容不规范");
            return model;
        }
        // 校验图片大小
        Long picSize = file.getSize();
        if (picSize.compareTo(2097152L) > 0) {
            model.setErrorMsg("图片超出2M大小限制");
            return model;
        }
        // 保存文件
        String s=File.separator;
        String filePath = s+"data"+s+"image"+s + fileType + s + System.currentTimeMillis() + s + picName;
        if (s.equals("\\")) {  // windows
            filePath = "D:" + filePath;
        }
        File files = new File(filePath);
        if (!files.exists()) {
            try {
                files.mkdirs();
            } catch (Exception e) {
                model.setErrorMsg("文件目录不存在");
                return model;
            }
        }
        try {
            file.transferTo(files);
            // String serverHost= Global.getValue("server_host");
            String serverHost = "http://localhost:8080";
            filePath= serverHost+ "/readFile.htm?path="+ URLEncoder.encode(filePath, "UTF-8");
        } catch (IllegalStateException | IOException e) {
        }
        // 转存文件
        model.setResPath(filePath);
        return model;
    }*/
}
