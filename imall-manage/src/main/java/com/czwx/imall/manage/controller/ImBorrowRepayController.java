package com.czwx.imall.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.DateUtil;
import com.czwx.imall.core.common.util.RdPage;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.domain.*;
import com.czwx.imall.core.mapper.UserAuthMapper;
import com.czwx.imall.core.mapper.UserCouponMapper;
import com.czwx.imall.core.model.*;
import com.czwx.imall.core.service.*;
import com.czwx.imall.manage.common.base.ImBaseController;
import com.czwx.imall.manage.common.page.PageParam;
import com.github.pagehelper.Page;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 还款管理
 */
@Controller
public class ImBorrowRepayController extends ImBaseController {

    public static final Logger logger = LoggerFactory.getLogger(ImBorrowRepayController.class);

    @Resource
    private OrderRepayPlanService orderRepayPlanService;

    @Resource
    private RepayLogService repayLogService;

    @Resource
    private OrderService orderService;

    @Resource
    private ImUserService imUserService;

    @Resource
    private BankCardService bankCardService;

    @Resource
    private UserCouponMapper couponMapper;

    @Resource
    private UserAuthMapper userAuthMapper;

    @Resource
    private UserCreditService userCreditService;

    /**
     * 还款管理 - 还款计划
     * @param request
     * @return
     */
    @RequestMapping("borrowRepay/repayPlan/list")
    public ModelAndView repayPlanList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("borrowRepay/repayPlanList");

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        searchMap.put("orderNo", request.getParameter("orderNo"));
        searchMap.put("startTime", request.getParameter("startTime"));
        searchMap.put("endTime", request.getParameter("endTime"));
        searchMap.put("state", request.getParameter("state"));

        PageParam pageParam = pageinator(request);
        Page<ManageRepayPlanModel> page = orderRepayPlanService.listRepayPlan(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    /**
     * 还款记录
     * @param request
     * @return
     */
    @RequestMapping("borrowRepay/repayLog/list")
    public ModelAndView repayPlanLogList(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("borrowRepay/repayLogList");

        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("phone", request.getParameter("phone"));
        searchMap.put("realName", request.getParameter("realName"));
        searchMap.put("orderNo", request.getParameter("orderNo"));
        searchMap.put("startTime", request.getParameter("startTime"));
        searchMap.put("endTime", request.getParameter("endTime"));

        PageParam pageParam = pageinator(request);
        Page<ManageRepayLogModel> page = repayLogService.listRepayLog(searchMap, pageParam.getCurrent(), pageParam.getPageSize());

        request.setAttribute("paginator", new RdPage(page));
        mv.addObject("list", page);
        mv.addObject("searchParam", searchMap);
        return mv;
    }

    /**
     * 还款记录详情
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("borrowRepay/repayLog/repayLogDetail")
    public ModelAndView repayLogDetail(HttpServletRequest request, @RequestParam("orderId") Long orderId)throws Exception {
        ModelAndView mv = new ModelAndView("borrowRepay/repayLogDetail");
        ManageRepayLogModel repayLog = repayLogService.getModelByOrderId(orderId);
        ImUserModel user = imUserService.getModelById(repayLog.getUserId());
        UserAuth userAuth = userAuthMapper.selectByUserId(repayLog.getUserId());

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
        mv.addObject("repayLog", repayLog);

        return mv;
    }

    /**
     * 代扣
     * @return
     */
    @RequestMapping("review/repayPlan/withhold")
    @ResponseBody
    public JSONObject withhold() {
        JSONObject json = new JSONObject();

        json.put("code", "200");
        json.put("msg", "");
        return json;
    }

    /**
     * 确认还款
     * @return
     */
    @RequestMapping("borrowRepay/repayLog/orderRepay")
    @ResponseBody
    public JSONObject orderRepay(@RequestParam(value = "orderId", required = true) Long orderId,
                                 @RequestParam(value = "repayWay", required = true) String repayWay,
                                 @RequestParam(value = "serialNumber", required = true) String serialNumber,
                                 @RequestParam(value = "repayAmount", required = true) BigDecimal repayAmount,
                                 @RequestParam(value = "penaltyAmount", required = true) BigDecimal penaltyAmount,
                                 @RequestParam(value = "payTime", required = true) String payTime,
                                 @RequestParam(value = "penaltyDay", required = true) Integer penaltyDay,
                                 @RequestParam(value = "repayVoucher", required = true) MultipartFile repayVoucher) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(payTime);

            JSONObject json = new JSONObject();
            UploadFileRes file = saveFile(repayVoucher);

            ManageOrderModel model = orderService.getModelByOrderId(orderId);
            BankCard card = bankCardService.getBankCardByUserId(model.getUserId());
            Map<String,Object> param = new HashMap<>();
            param.put("orderNo",model.getOrderNo());
            List<UserCoupon> list = couponMapper.listSelective(param);
            RepayLog repayLog = new RepayLog();
            StringBuffer noStr = new StringBuffer();
            if(list != null){
                for(UserCoupon coupon : list){
                    repayLog.setCouponAmount(repayLog.getCouponAmount().add(new BigDecimal(coupon.getAmount())));
                    noStr.append(coupon.getCouponNo()).append(",");
                }
                repayLog.setRemitAmount(repayLog.getCouponAmount());
                repayLog.setRemitRemark("优惠券减免");

            }
            if(noStr.length()!=0&&noStr!= null){
                repayLog.setCouponNo(noStr.delete(noStr.length()-1, noStr.length()).toString());
            }
            OrderRepayPlan plan = orderRepayPlanService.getPlanByOrderId(orderId);
            repayLog.setOrderId(orderId);
            repayLog.setUserId(plan.getUserId());
            repayLog.setPlanId(plan.getId());
            repayLog.setAmount(repayAmount);
            repayLog.setPenaltyDay(penaltyDay);
            repayLog.setPenaltyAmount(penaltyAmount);
            repayLog.setExtensionAmount(plan.getExtensionAmount());
            repayLog.setRepayWay(repayWay);
            repayLog.setRepayAccount(card.getCardNo());
            repayLog.setSerialNumber(serialNumber);
            repayLog.setRepayVoucher(file.getResPath());

            repayLog.setPayTime(date);
            repayLog.setCreateTime(new Date());
            repayLog.setUpdateTime(new Date());
            repayLogService.save(repayLog);

            OrderRepayPlan repayPlan = new OrderRepayPlan();
            repayPlan.setOrderId(orderId);
            repayPlan.setState("10");
            orderRepayPlanService.updateByOrderId(repayPlan);

            Order order = new Order();
            order.setId(orderId);
            order.setState("50");
            orderService.updateByOrderId(order);

            //查询借款金额
            Order or=orderService.selectByPrimaryKey(orderId);
            if(or!=null && plan!=null){
                UserCredit userCredit=userCreditService.findByUserId(plan.getUserId());
                BigDecimal amonut=or.getAmount();
                userCredit.setUsed(userCredit.getUsed().subtract(amonut));
                userCredit.setUnuse(userCredit.getUnuse().add(amonut));
                userCredit.setUpdateTime(new Date());
                userCreditService.updateByUserId(userCredit);
            }
            json.put("code", "200");
            json.put("msg", "");

            return json;
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
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

}
