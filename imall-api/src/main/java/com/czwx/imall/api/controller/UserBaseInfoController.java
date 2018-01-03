package com.czwx.imall.api.controller;
import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.api.annotation.SysLog;
import com.czwx.imall.core.common.context.Constant;
import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.exception.BussinessException;
import com.czwx.imall.core.common.util.DateUtil;
import com.czwx.imall.core.common.util.ServletUtils;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.common.web.controller.BaseController;
import com.czwx.imall.core.domain.*;
import com.czwx.imall.core.model.FileTypeUtil;
import com.czwx.imall.core.model.UploadFileRes;
import com.czwx.imall.core.service.CouponService;
import com.czwx.imall.core.service.UserAuthService;
import com.czwx.imall.core.service.UserBaseInfoService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import javax.annotation.Resource;

import com.czwx.imall.core.service.UserCreditService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tool.util.NumberUtil;

/**
 * 用户详情表Controller
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class UserBaseInfoController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(UserBaseInfoController.class);

    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Resource
    private UserCreditService userCreditService;

    @Resource
    private CouponService couponService;

    @Resource
    private UserAuthService userAuthService;


    /**用户的个人基本信息
     * @since 1.0.0
     */
    @RequestMapping(value = "/api/act/mine/userInfo/getUserInfo.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public void getUserInfo(
            @RequestParam(value = "userId", required = true) String userId) throws Exception {
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,Object> data= userBaseInfoService.getUserInfo(userId);
        String serverHost= "";
        if("dev".equals(Global.getValue("app_environment"))){
            serverHost =  Global.getValue("local_host");;
        }else{
            serverHost= Global.getValue("server_host");
        }
       if (data != null ) {
           if(data.get("creditFrontImg")!=null){
               //data.put("creditFrontImg",serverHost+"/readFile.htm?path="+data.get("creditFrontImg").toString());
            data.put("creditFrontImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("creditFrontImg").toString(), "UTF-8"));
           }
           if(data.get("titleImg")!=null){
              // data.put("titleImg",serverHost+"/readFile.htm?path="+data.get("titleImg").toString());
               data.put("titleImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("titleImg").toString(), "UTF-8"));
           }
           if(data.get("backImg")!=null){
               //data.put("backImg",serverHost+"/readFile.htm?path="+data.get("backImg").toString());
               data.put("backImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("backImg").toString(), "UTF-8"));
           }
           if(data.get("creditBackImg")!=null){
              // data.put("creditBackImg",serverHost+"/readFile.htm?path="+data.get("creditBackImg").toString());
               data.put("creditBackImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("creditBackImg").toString(), "UTF-8"));
           }
           if(data.get("userFrontImg")!=null){
              // data.put("userFrontImg",serverHost+"/readFile.htm?path="+data.get("userFrontImg").toString());
               data.put("userFrontImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("userFrontImg").toString(), "UTF-8"));
           }
           if(data.get("frontImg")!=null){
               //data.put("frontImg",serverHost+"/readFile.htm?path="+data.get("frontImg").toString());
              data.put("frontImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("frontImg").toString(), "UTF-8"));
           }
           if(data.get("userLivingImg")!=null){
              // data.put("userLivingImg",serverHost+"/readFile.htm?path="+data.get("userLivingImg").toString());
              data.put("userLivingImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("userLivingImg").toString(), "UTF-8"));
           }
           if(data.get("userBackImg")!=null){
               //data.put("userBackImg",serverHost+"/readFile.htm?path="+data.get("userBackImg").toString());
               data.put("userBackImg",serverHost+"/readFile.htm?path="+URLEncoder.encode(data.get("userBackImg").toString(), "UTF-8"));
           }

       }
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response,result);
    }

    /**
     * 我的信息展示
     */
    @SysLog(desc = "我的信息展示")
    @RequestMapping(value="/api/act/mine/userInfo/getMyMessage.htm", method = RequestMethod.POST)
    public void getMyMessage(@RequestParam(value = "userId", required = true) long userId){
        try {
            JSONObject obj = new JSONObject();
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", userId);
            UserBaseInfo info = userBaseInfoService.findSelective(paramMap);

            if(info == null || !info.getState().equals("40")){
                obj.put("userInfo","未完善");
            }else{
                obj.put("userInfo","已完善");
            }
            User user = userBaseInfoService.selectUser(userId);
            if(user != null){
                obj.put("invitationCode",user.getInvitationCode());
            }
            Map<String, Object> param = new HashMap<>();
            //paramMap.put("userId", userId);
            UserCredit credit = userCreditService.findSelective(paramMap);
            BigDecimal total = credit.getTotal();
            BigDecimal used = credit.getUsed();
            BigDecimal unuse = credit.getUnuse();

            obj.put("total",total);
            obj.put("used",used);
            obj.put("unuse",unuse);

            returnJson(200, "申请成功", obj);
        }catch (BussinessException e){
            logger.error("error {}", e);
            returnJson(400, e.getMessage(), null);
        }catch (Exception e){
            logger.error("error {}", e);
            returnJson(500, "服务器错误", null);
        }
    }

    public void returnJson(int code, String msg, Object obj){
        Map<String, Object> reposedata = new HashMap<>();
        reposedata.put(Constant.RESPONSE_DATA, obj);
        reposedata.put(Constant.RESPONSE_CODE, code);
        reposedata.put(Constant.RESPONSE_CODE_MSG, msg);
        ServletUtils.writeToResponse(response, reposedata);
    }

    /**
     * 返回所有的保险公司信息
     */
    @RequestMapping(value = "/api/act/mine/userInfo/insuranceList.htm", method = RequestMethod.GET)
    public void list() {

        List<InsuranceList> data = userBaseInfoService.getInsuranceList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, data);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);

    }



    /**
     * 保存用户照片信息统一接口
     *
     *
     */

    @RequestMapping(value = "/api/act/mine/userInfo/saveImg.htm",method = RequestMethod.POST)
    public void saveUserImg(MultipartFile img) throws Exception {
        Map<String,Object> resultMap = new HashMap();
        List<UploadFileRes> list = new LinkedList<>();
        if(img==null){
            resultMap.put(Constant.RESPONSE_CODE_MSG,"上传照片不能为空");
            return ;
        }
        saveMultipartFile(list, img);
        resultMap.put(Constant.RESPONSE_DATA,list);
        ServletUtils.writeToResponse(response,resultMap);
    }

    /**
     * 保存用户身份证信息
     * @param userId
     * @param realName
     * @param phone
     * @param livingImg
     * @param frontImg
     * @param backImg
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/mine/userInfo/save.htm",method = RequestMethod.POST)
    public void saveUserBaseInfo(@RequestParam(value = "userId", required = true) String userId,
                            @RequestParam(value = "realName", required = true) String realName,
                            @RequestParam(value = "phone", required = true) String phone,
                            @RequestParam(value = "idNo", required = true) String idNo,
                            @RequestParam(value = "livingImg", required = true) String livingImg,
                            @RequestParam(value = "frontImg", required = true) String frontImg,
                            @RequestParam(value = "backImg", required = true) String backImg) throws Exception{
        Map<String,Object> resultMap = new HashMap();
        if(tool.util.StringUtil.isEmpty(realName)||tool.util.StringUtil.isEmpty(phone)||tool.util.StringUtil.isEmpty(idNo)
                ||tool.util.StringUtil.isEmpty(livingImg)||tool.util.StringUtil.isEmpty(frontImg)||tool.util.StringUtil.isEmpty(backImg)
                ){
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG,"参数不能为空");
            ServletUtils.writeToResponse(response, resultMap);
            return;
        }
        if (!StringUtil.isPhone(phone)) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG,"请输入正确的手机号码");
            ServletUtils.writeToResponse(response, resultMap);
            return;
        }
        Map<String, Object> infoMap = new HashMap<String, Object>();
//        infoMap.put("idNo", idNo);
//        UserBaseInfo infoBaseInfo = userBaseInfoService.findSelective(infoMap);
//        if (infoBaseInfo != null && infoBaseInfo.getUserId()!=NumberUtil.getLong(userId)) {
//            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//            resultMap.put(Constant.RESPONSE_CODE_MSG,"该身份证已经认证");
//            ServletUtils.writeToResponse(response, resultMap);
//            return;
//        }
        if (!StringUtil.isCard(idNo)) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG,"请输入正确的身份证号");
            ServletUtils.writeToResponse(response, resultMap);
            return;
        }

        //判断是否是修改
    try {
        infoMap.clear();
        infoMap.put("userId",NumberUtil.getLong(userId) );
        UserBaseInfo infoBase = userBaseInfoService.findSelective(infoMap);
        boolean flag = false;
        if(infoBase!=null){
            infoBase.setLivingImg(spilt(livingImg));
            infoBase.setFrontImg(spilt(frontImg));
            infoBase.setBackImg(spilt(backImg));
            infoBase.setIdNo(idNo);
            infoBase.setPhone(phone);
            infoBase.setRealName(realName);
            infoBase.setUpdateTime(new Date());
            flag= userBaseInfoService.updateData(infoBase);
        }else {
            UserBaseInfo info = new UserBaseInfo();
            info.setUserId(NumberUtil.getLong(userId));
            info.setLivingImg(spilt(livingImg));
            info.setFrontImg(spilt(frontImg));
            info.setBackImg(spilt(backImg));
            info.setIdNo(idNo);
            info.setPhone(phone);
            info.setRealName(realName);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            info.setState("10");
           flag= userBaseInfoService.save(info);
        }
         if(flag){
             resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
             resultMap.put(Constant.RESPONSE_CODE_MSG,"操作成功");
             ServletUtils.writeToResponse(response, resultMap);
         }
      }catch (Exception e){
          logger.error(e.getMessage(), e);
          resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
          resultMap.put(Constant.RESPONSE_CODE_MSG,"网络异常,请重试");
          ServletUtils.writeToResponse(response, resultMap);
      }

    }

    /**
     * 保存工作信息
     * @param userId
     * @param certificateNo
     * @param companyName
     * @param title
     * @param titleImg
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/mine/userInfo/saveWorkInfo.htm",method = RequestMethod.POST)
    public void saveUserWorkInfo(@RequestParam(value = "userId", required = true) String userId,
                                 @RequestParam(value = "certificateNo", required = true) String certificateNo,
                                 @RequestParam(value = "companyName", required = true) String companyName,
                                 @RequestParam(value = "title", required = true) String title,
                                 @RequestParam(value = "titleImg", required = true) String titleImg
    ) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        if (tool.util.StringUtil.isEmpty(certificateNo) || tool.util.StringUtil.isEmpty(companyName)
                || tool.util.StringUtil.isEmpty(title) || tool.util.StringUtil.isEmpty(titleImg)) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "参数不能为空");
            ServletUtils.writeToResponse(response, resultMap);
            return;
        }
        Map<String, Object> ret = new HashMap<String, Object>();
        try {
            Map<String, Object> infoMap = new HashMap<String, Object>();
            infoMap.put("userId", NumberUtil.getLong(userId));
            boolean flag = false;
            UserBaseInfo infoBase = userBaseInfoService.findSelective(infoMap);
            if (infoBase != null) {
                infoBase.setUpdateTime(new Date());
                infoBase.setCertificateNo(certificateNo);
                infoBase.setTitle(title);
                infoBase.setCompanyName(companyName);
                infoBase.setTitleImg(spilt(titleImg));
                infoBase.setState("40");//个人信息完善
                flag = userBaseInfoService.updateData(infoBase);

            }
            if (flag) {
                //提交成功给用户优惠券
                List<UserCoupon> list =couponService.findByUserId( NumberUtil.getLong(userId));
            if(list==null || (list!=null&&list.isEmpty())){
                UserCoupon userCoupon = new UserCoupon();
                userCoupon.setCreateTime(new Date());
                userCoupon.setUserId(NumberUtil.getLong(userId));
                userCoupon.setAmount(Integer.parseInt(Global.getValue("init_coupon_amount")));
                userCoupon.setValidStartTime(new Date());
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE,-1);
                cal.add(Calendar.MONTH, 1);
                String conponTime = DateUtil.dateStr2(cal.getTime());
                conponTime = conponTime + " 23:59:59";
                userCoupon.setValidEndTime(DateUtil.valueOf(conponTime, "yyyy-MM-dd HH:mm:ss"));
                userCoupon.setCouponName(Global.getValue("coupon_name"));
                userCoupon.setCouponNo(UUID.randomUUID().toString().replaceAll("-", ""));
                userCoupon.setCreateTime(new Date());
                userCoupon.setUpdateTime(new Date());
                userCoupon.setState("10");
                couponService.save(userCoupon);
                //将优惠券返回给页面
                ret.put("couponAmount",Global.getValue("init_coupon_amount"));
                ret.put("validEndTime",userCoupon.getValidEndTime());
                ret.put("couponName",Global.getValue("coupon_name"));
                }
                UserAuth auth=userAuthService.getByUserId(NumberUtil.getLong(userId));
               if(auth==null) {
                   //将用户信息添加到用户认证表
                   UserAuth userAuth = new UserAuth();
                   userAuth.setUserId(NumberUtil.getLong(userId));
                   userAuth.setCreateTime(new Date());
                   userAuth.setUpdateTime(new Date());
                   userAuth.setCertificateState("10");//10是未认证
                   userAuthService.save(userAuth);
               }
            }
            resultMap.put(Constant.RESPONSE_DATA,ret);
            resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "授信申请提交成功");
            ServletUtils.writeToResponse(response, resultMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "授信申请失败");
            ServletUtils.writeToResponse(response, resultMap);
        }

    }

    private void saveMultipartFile(List<UploadFileRes> list, MultipartFile file) {
        if (!file.isEmpty()) {
            UploadFileRes model = save(file);
            list.add(model);
        }
    }

    private UploadFileRes save(MultipartFile file) {
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

    //截取新的路径
    public String spilt(String s) throws Exception{
        int index=s.indexOf("=");
        String url=s.substring(index+1);
        return URLDecoder.decode(url,"UTF-8");
    }
}