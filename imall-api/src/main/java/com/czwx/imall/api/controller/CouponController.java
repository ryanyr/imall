package com.czwx.imall.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.api.annotation.SysLog;
import com.czwx.imall.core.common.context.Constant;
import com.czwx.imall.core.common.util.ServletUtils;
import com.czwx.imall.core.common.web.controller.BaseController;
import com.czwx.imall.core.domain.PageInfo;
import com.czwx.imall.core.domain.UserCoupon;
import com.czwx.imall.core.service.CouponService;
import com.github.pagehelper.Page;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Scope("prototype")
@Controller
public class CouponController extends BaseController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CouponController.class);


	@Resource
	private CouponService couponService;


	/**
	 * 查询用户所有的可以使用优惠券
	 */
	@SysLog(desc = "查询用户优惠券")
	@RequestMapping("/api/act/coupon/query.htm")
	public void queryCoupon(@RequestParam(value = "userId", required = true)long userId,
							@RequestParam(value = "couponType", required = true)String couponType,
							@RequestParam(value = "page", required = true)int page,
							@RequestParam(value = "pageSize", required = true)int pageSize) {
		Map<String,Object> params = new HashMap<>();
		params.put("userId",userId);
		params.put("state","10");
		Page<UserCoupon> list = null;
		if("1".equals(couponType)){//1是查看未使用的
			list = couponService.findEnable(page,pageSize,params);
		}else if("2".equals(couponType)){//2是查看所有已使用的和过期的
			list = couponService.findOverdueCoupon(page,pageSize,userId);
		}else{
			returnJson(400, "无法查看优惠券", null);
			return;
		}
		JSONObject obj =  new JSONObject();
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(page);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(list.getTotal());
		obj.put("list", list);
		obj.put("pageInfo", pageInfo);
		returnJson(200,"查询成功",obj);
	}

	/**
	 * 查询优惠券的详情
	 */
	@SysLog(desc = "查询优惠券详情")
	@RequestMapping("/api/act/coupon/queryCouponDetail.htm")
	public void queryCouponDetail(Long id) {
		Map<String,Object> params = new HashMap<>();
		UserCoupon coupon = couponService.findCouponDetail(id);
		JSONObject obj =  new JSONObject();
			obj.put("couponDetail",coupon);

		returnJson(200,"查询成功",obj);
	}

	public void returnJson(int code, String msg, Object obj){
		Map<String, Object> reposedata = new HashMap<>();
		reposedata.put(Constant.RESPONSE_DATA, obj);
		reposedata.put(Constant.RESPONSE_CODE, code);
		reposedata.put(Constant.RESPONSE_CODE_MSG, msg);
		ServletUtils.writeToResponse(response, reposedata);
	}

}
