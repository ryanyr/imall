package com.czwx.imall.api.controller;


import com.czwx.imall.core.common.context.Constant;
import com.czwx.imall.core.common.context.Global;
import com.czwx.imall.core.common.util.DateUtil;
import com.czwx.imall.core.common.util.ServletUtils;
import com.czwx.imall.core.common.util.StringUtil;
import com.czwx.imall.core.common.web.controller.BaseController;

import com.czwx.imall.core.domain.BankCard;
import com.czwx.imall.core.domain.BankList;
import com.czwx.imall.core.domain.CreditCard;
import com.czwx.imall.core.domain.UserBaseInfo;
import com.czwx.imall.core.model.FileTypeUtil;
import com.czwx.imall.core.model.UploadFileRes;
import com.czwx.imall.core.service.BankCardService;
import com.czwx.imall.core.service.CreditCardService;
import com.czwx.imall.core.service.UserBaseInfoService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tool.util.NumberUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 用户详情表Controller
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@Controller
@Scope("prototype")
public class BankCardController extends BaseController {


    @Resource
    private BankCardService bankCardService;

    @Resource
    private UserBaseInfoService userBaseInfoService;

    @Resource
    private CreditCardService creditCardService;

    public static final Logger logger = LoggerFactory.getLogger(BankCardController.class);

    /**
     * 返回银行卡信息
     */
    @RequestMapping(value = "/api/act/mine/bank/list.htm", method = RequestMethod.GET)
    public void list() {

        List<BankList> list = bankCardService.getBankList();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put(Constant.RESPONSE_DATA, list);
        result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
        ServletUtils.writeToResponse(response, result);

    }

    /**
     * 保存银行卡信用卡信息
     *
     * @param userId
     * @param cardNo
     * @param bank
     * @throws Exception
     */
    @RequestMapping(value = "/api/act/mine/bank/save.htm", method = RequestMethod.POST)
    public void saveBankCard(@RequestParam(value = "userId", required = true) String userId,
                             @RequestParam(value = "cardNo", required = true) String cardNo,
                             @RequestParam(value = "bank", required = true) String bank,
                             @RequestParam(value = "creditBank", required = true) String creditBank,
                             @RequestParam(value = "creditNo", required = true) String creditNo,
                             @RequestParam(value = "frontImg", required = true) String frontImg,
                             @RequestParam(value = "backImg", required = true) String backImg,
                             @RequestParam(value = "creditfrontImg", required = true) String creditfrontImg,
                             @RequestParam(value = "creditbackImg", required = true) String creditbackImg
                             ) throws Exception {
        Map<String, Object> resultMap = new HashMap();
        if (tool.util.StringUtil.isEmpty(cardNo) || tool.util.StringUtil.isEmpty(bank)
                ||tool.util.StringUtil.isEmpty(creditBank)||tool.util.StringUtil.isEmpty(creditNo)
                ||tool.util.StringUtil.isEmpty(frontImg)||tool.util.StringUtil.isEmpty(backImg)
                ||tool.util.StringUtil.isEmpty(creditfrontImg)||tool.util.StringUtil.isEmpty(creditbackImg)
                ) {
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "参数不能为空");
            ServletUtils.writeToResponse(response, resultMap);
                return;
        }
        if(!tool.util.StringUtil.isNumber(cardNo) || cardNo.length() <15 || cardNo.length() > 25   ){

            resultMap.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG,"银行卡号格式有误");
            ServletUtils.writeToResponse(response,resultMap);
            return ;
        }
        if(!tool.util.StringUtil.isNumber(creditNo) || creditNo.length() <13 || creditNo.length() > 20   ){
            Map<String,Object> result=new HashMap<String,Object>();
            result.put(Constant.RESPONSE_CODE,Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG,"信用卡号格式有误");
            ServletUtils.writeToResponse(response,result);
            return ;
        }

        Map<String, Object> infoMap = new HashMap<String, Object>();
//        infoMap.put("cardNo", cardNo);
//        infoMap.put("bankType", "1");
//
//        BankCard bankCardInfo = bankCardService.findSelective(infoMap);
//        if (bankCardInfo != null&&bankCardInfo.getUserId()!=NumberUtil.getLong(userId)) {
//            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//            resultMap.put(Constant.RESPONSE_CODE_MSG, "该银行卡已认证过");
//            ServletUtils.writeToResponse(response, resultMap);
//            return ;
//        }
//        infoMap.clear();
//        infoMap.put("creditNo", creditNo);
//        infoMap.put("bankType", "2");
//        BankCard credit = bankCardService.findSelective(infoMap);
//        if (credit != null&&credit.getUserId()!=NumberUtil.getLong(userId)) {
//            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//            resultMap.put(Constant.RESPONSE_CODE_MSG, "该信用卡已认证过");
//            ServletUtils.writeToResponse(response, resultMap);
//            return ;
//        }
        try {
            //查询用户是否有银行卡信息
            infoMap.clear();
            infoMap.put("userId", NumberUtil.getLong(userId));
            BankCard bankCard = bankCardService.findSelective(infoMap);
            //查询用户是否有信用卡信息
            infoMap.clear();
            infoMap.put("userId",NumberUtil.getLong(userId));
            CreditCard creditCard = creditCardService.findSelective(infoMap);

            boolean flag =false;
            boolean flag1 =false;
            if(bankCard!=null && creditCard!=null){
                //是修改信息
                bankCard.setBank(bank);
                bankCard.setUpdateTime(new Date());
                bankCard.setCardNo(cardNo);
                bankCard.setFrontImg(spilt(frontImg));
                bankCard.setBackImg(spilt(backImg));
                flag= bankCardService.update(bankCard);

                creditCard.setBank(creditBank);
                creditCard.setUpdateTime(new Date());
                creditCard.setCardNo(creditNo);
                creditCard.setFrontImg(spilt(creditfrontImg));
                creditCard.setBackImg(spilt(creditbackImg));
                flag1 =creditCardService.update(creditCard);
            }else{
                //是第一次添加银行卡
                BankCard bc = new BankCard();
                bc.setUserId(NumberUtil.getLong(userId));
                bc.setBank(bank);
                bc.setCreateTime(new Date());
                bc.setUpdateTime(new Date());
                bc.setCardNo(cardNo);
                bc.setUseNow("1");
                bc.setFrontImg(spilt(frontImg));
                bc.setBackImg(spilt(backImg));
                flag = bankCardService.save(bc);
                //添加信用卡
                CreditCard credit = new CreditCard();
                credit.setUserId(NumberUtil.getLong(userId));
                credit.setBank(creditBank);
                credit.setCreateTime(new Date());
                credit.setUpdateTime(new Date());
                credit.setCardNo(creditNo);
                credit.setUseNow("1");
                credit.setFrontImg( spilt(creditfrontImg));
                credit.setBackImg(spilt(creditbackImg));
               flag1 = creditCardService.save(credit);
               //添加银行卡  将银行卡关联到baseInfo表中
                infoMap.clear();
                infoMap.put("userId",NumberUtil.getLong(userId));
                UserBaseInfo userBaserInfo=userBaseInfoService.findByUserId(NumberUtil.getLong(userId));
                BankCard bankCad = bankCardService.findSelective(infoMap);
                CreditCard creditCad = creditCardService.findSelective(infoMap);
                if(bankCad!=null && creditCad!=null&&userBaserInfo!=null){
                    userBaserInfo.setBankId(bankCad.getId());
                    userBaserInfo.setCreditId(creditCad.getId());
                    userBaserInfo.setState("30");
                    userBaseInfoService.updateData(userBaserInfo);
                }
            }
            if(flag && flag1){
            resultMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "操作成功");
            ServletUtils.writeToResponse(response, resultMap);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultMap.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            resultMap.put(Constant.RESPONSE_CODE_MSG, "操作失败");
            ServletUtils.writeToResponse(response, resultMap);
        }

}
    //截取新的路径
    public String spilt(String s) throws Exception{
        int index=s.indexOf("=");
        String url=s.substring(index+1);
        return URLDecoder.decode(url,"UTF-8");
    }

}