package com.czwx.imall.api.controller;
import com.czwx.imall.api.annotation.SysLog;
import com.czwx.imall.core.common.context.Constant;

import com.czwx.imall.core.common.util.ServletUtils;
import com.czwx.imall.core.common.web.controller.BaseController;

import com.czwx.imall.core.domain.UserExtraInfo;

import com.czwx.imall.core.domain.UserMakeMoneyInfo;
import com.czwx.imall.core.service.UserExtraInfoService;

import com.czwx.imall.core.service.UserExtraInfoService;
import com.czwx.imall.core.service.UserMakeMoneyInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
public class UserExtraInfoController extends BaseController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserExtraInfo.class);

	@Resource
    private UserExtraInfoService userExtraInfoService;
	
	@Resource
	private UserMakeMoneyInfoService userMakeMoneyInfoService;


	@SysLog(desc = "保存我要贷款")
	@RequestMapping("/api/act/mine/userExtraInfo/saveWantLoan.htm")
	public void saveWantLoan(long userId,BigDecimal loanAmount, String houseOwnership, String houseExtension,String houseMortgage,
							 String carMortgage , String accumulationFund,String socialSecurity, String salary,String insuranceInfo) {

		if(loanAmount==null){
			returnJson(400, "请输入您要贷款的金额", null);
			return;
		}
		UserExtraInfo userExtraInfo = new UserExtraInfo();
		userExtraInfo.setUserId(userId);
		userExtraInfo.setCreateTime(new Date());
		userExtraInfo.setUpdateTime(new Date());
		userExtraInfo.setAccumulationFund(accumulationFund);
		userExtraInfo.setCarMortgage(carMortgage);
		userExtraInfo.setInsuranceInfo(insuranceInfo);
		userExtraInfo.setHouseOwnership(houseOwnership);
		userExtraInfo.setHouseExtension(houseExtension);
		userExtraInfo.setSocialSecurity(socialSecurity);
		userExtraInfo.setSalary(salary);
		userExtraInfo.setState("1"); //1表示已经提交过
		userExtraInfo.setLoanAmount(loanAmount);
		userExtraInfo.setHouseMortgage(houseMortgage);
		boolean flag = false;
		try {

			UserExtraInfo info=userExtraInfoService.getByUserId(userId);
			if(info!=null){
				returnJson(400, "你已经提交过,请等待工作人员审核", null);
				return;
			}

			flag = userExtraInfoService.save(userExtraInfo);
			if (flag) {
				returnJson(200, "申请提交成功", null);
			}
		} catch (Exception e) {
			logger.error("error {}", e);
			returnJson(500, "网络异常,请稍后再提交", null);
		}
	}
		@SysLog(desc = "保存我要赚钱")
		@RequestMapping("/api/act/mine/userExtraInfo/saveWantMakeMoney.htm")
		public void saveWantExtra(long userId,BigDecimal loanAmount, String houseOwnership, String houseExtension,String houseMortgage,
				String carMortgage , String accumulationFund,String socialSecurity, String salary,String insuranceInfo) {
			if(loanAmount==null){
				returnJson(400, "请输入您要赚钱的金额", null);
				return;
			}
		UserMakeMoneyInfo info = new UserMakeMoneyInfo();
			info.setUserId(userId);
			info.setCreateTime(new Date());
			info.setUpdateTime(new Date());
			info.setAccumulationFund(accumulationFund);
			info.setCarMortgage(carMortgage);
			info.setInsuranceInfo(insuranceInfo);
			info.setHouseOwnership(houseOwnership);
			info.setHouseExtension(houseExtension);
			info.setSocialSecurity(socialSecurity);
			info.setSalary(salary);
			info.setLoanAmount(loanAmount);
			info.setHouseMortgage(houseMortgage);
			boolean flag=false;
			try {
				flag = userMakeMoneyInfoService.save(info);
				if(flag){
					returnJson(200, "申请提交成功", null);
				}
			}catch (Exception e){
				logger.error("error {}", e);
				returnJson(500, "网络异常,请稍后再提交", null);
			}

	}
	public void returnJson ( int code, String msg, Object obj){
			Map<String, Object> reposedata = new HashMap<>();
			reposedata.put(Constant.RESPONSE_DATA, obj);
			reposedata.put(Constant.RESPONSE_CODE, code);
			reposedata.put(Constant.RESPONSE_CODE_MSG, msg);
			ServletUtils.writeToResponse(response, reposedata);
		}
}
