package com.czwx.imall.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.api.annotation.SysLog;
import com.czwx.imall.core.common.context.Constant;
import com.czwx.imall.core.common.exception.BussinessException;
import com.czwx.imall.core.common.util.NidGenerator;
import com.czwx.imall.core.common.util.ServletUtils;
import com.czwx.imall.core.common.web.controller.BaseController;
import com.czwx.imall.core.domain.*;
import com.czwx.imall.core.form.OrderForm;
import com.czwx.imall.core.service.*;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Scope("prototype")
@Controller
public class OrderController extends BaseController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Resource
	private OrderService orderService;

	@Resource
	private CouponService couponService;

	@Resource
	private ProductService productService;

	@Resource
	private UserCreditService userCreditService;

	@Resource
	private UserBaseInfoService userBaseInfoService;

	/**
     * 提现确认
     */
    @SysLog(desc = "提现确认")
    @RequestMapping(value = "/api/act/borrow/save.htm", method = RequestMethod.POST)
	public void saveOrder(OrderForm form) throws Exception {

    	if(StringUtils.isEmpty(form.getPolicyImg())&&StringUtils.isEmpty(form.getPolicyImg1())
				&&StringUtils.isEmpty(form.getPolicyImg2())&&StringUtils.isEmpty(form.getPolicyImg3())){
			returnJson(400, "图片不能为空", null);
			return;
		}
		Order order = new Order();
		order.setUserId(form.getUserId());
		order.setAmount(form.getAmount());
		order.setServiceFee(form.getServiceFee());
		order.setTimeLimit(form.getTimeLimit());
		order.setPolicyAmount(form.getPolicyAmount());
		if(!StringUtils.isEmpty(form.getPolicyImg())){
			order.setPolicyImg(spilt(form .getPolicyImg()));
		}
		if(!StringUtils.isEmpty(form.getPolicyImg1())){
			order.setPolicyImg1(spilt(form .getPolicyImg1()));
		}
		if(!StringUtils.isEmpty(form.getPolicyImg2())){
			order.setPolicyImg2(spilt(form .getPolicyImg2()));
		}
		if(!StringUtils.isEmpty(form.getPolicyImg3())){
			order.setPolicyImg3(spilt(form .getPolicyImg3()));
		}
		order.setInsuranceCompany(form.getInsuranceCompany());
		order.setClient(form.getClient());
		order.setChannelId(form.getChannelId());
		order.setBorrowType(form.getBorrowType());
		order.setOrderNo(NidGenerator.getOrderNo());
		order.setRealAmount(form.getAmount());
		try {
			Product product = productService.getProduct();
			if(product == null){
				throw new BussinessException("product 产品缺失");
			}

			if(StringUtils.isEmpty(form.getInsuranceCompany())){
				throw new BussinessException("insuranceCompany 未输入承保公司");
			}

			if(order.getPolicyAmount()==null|| order.getAmount() == null){
				throw new BussinessException("amount 金额缺失");
			}

			if(order.getBorrowType() == null){
				throw new BussinessException("borrowType 订单类型缺失");
			}


//			BigDecimal policyAmount = order.getPolicyAmount();
//			BigDecimal amount = order.getAmount();
//			String insuranceCompany = order.getInsuranceCompany();
			UserCredit credit = userCreditService.findByUserId(form.getUserId());
			if(credit==null || form.getAmount().compareTo(credit.getUnuse())>0){
				returnJson(400,"用户授信额度不足",null);
				return;
			}

			JSONObject Obj = orderService.saveOrder(order,product,form.getListCouponNo(),credit);

			returnJson(200, "申请成功", Obj);
		}catch (BussinessException e){
			logger.error("error {}", e);
            returnJson(400, e.getMessage(), null);
		}catch (Exception e){
			logger.error("error {}", e);
            returnJson(500, "服务器错误", null);
		}
	}

	/**
	 * 提现申请
	 */
	@SysLog(desc = "提现申请")
	@RequestMapping(value = "/api/act/borrow/apply.htm", method = RequestMethod.POST)
	public void apply(OrderForm form){
		try {
			Product product = productService.getProduct();
			if(product == null){
				throw new BussinessException("product 产品缺失");
			}
			int level = orderService.accessLevel(form.getAmount(),product);
			if(form.getPolicyAmount().compareTo(form.getAmount())==-1){
				throw new BussinessException("policyAmount 保单金额无效");
			}
			if(level == 0){
				throw new BussinessException("amount 提现金额无效");
			}
			//获取费用等级
			if(level==0){
				throw new BussinessException("FeeLevel is error");
			}
			BigDecimal serviceFee = product.getFeeLevelAmtStart().add(product.getFeeLevelAmt().multiply(new BigDecimal(level-1)));
			JSONObject obj =  new JSONObject();
			obj.put("serviceFee",serviceFee);
			obj.put("totalMoney",form.getAmount().add(serviceFee));
			obj.put("policyAmount",form.getPolicyAmount());
			obj.put("amount",form.getAmount());
			if(form.getListCouponNo() != null){
				List<String> list = form.getListCouponNo();
				int discount = 0;
				for(String couponNo : list){
					UserCoupon coupon = couponService.findByCouponNo(couponNo);
					if(coupon != null){
						discount += coupon.getAmount();
					}
				}
				obj.put("actualAmount",form.getAmount().add(serviceFee).subtract(new BigDecimal(discount)));
			}else{
				//查询用户优惠券
				Map<String,Object> params = new HashMap<>();
				params.put("userId",form.getUserId());
				params.put("state","10");
				params.put("nowTime",new Date());
				List<UserCoupon> list = couponService.listValid(params);

				if (list.size()==0){
					//state等于0 没有优惠券 1 有优惠券
					obj.put("state",0);
				}else {
					obj.put("state",1);
					obj.put("listCoupon",list);
				}
			}
			returnJson(200, "申请成功", obj);
		}catch (BussinessException e){
			logger.error("error {}", e);
			returnJson(400, e.getMessage(), null);
		}catch (Exception e){
			logger.error("error {}", e);
			returnJson(500, "服务器错误", null);
		}

	}

	/**
	 * 借款信息列表展示
	 * @param userId

	 * @param page
	 * @param pageSize
	 */
	@SysLog(desc = "借款信息列表展示")
	@RequestMapping(value = "/api/act/mine/borrow/list.htm", method = RequestMethod.POST)
	public void listBorrow(@RequestParam(value = "userId", required = true)long userId,
						   @RequestParam(value = "stateList", required = true)List stateList,
						   @RequestParam(value = "page", required = true)int page,
						   @RequestParam(value = "pageSize", required = true)int pageSize){
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("stateList",stateList);
			Page<OrderForm> list = (Page<OrderForm>) orderService.pagelistOrder(page, pageSize, params);
			/*if (orderType.equals("1")) {
				list = (Page<OrderForm>) orderService.pagelistNotOrder(page, pageSize, params);
			} else if (orderType.equals("2")) {
				list = (Page<OrderForm>) orderService.pagelistOnOrder(page, pageSize, params);
			} else if (orderType.equals("3")) {
				list = (Page<OrderForm>) orderService.pagelistHadPayOrder(page, pageSize, params);
			} else {
				returnJson(400, "无此订单状态", null);
				return;
			}*/
			PageInfo pageInfo = new PageInfo();
			pageInfo.setPage(page);
			pageInfo.setPageSize(pageSize);
			pageInfo.setTotal(list.getTotal());
			JSONObject obj = new JSONObject();
			obj.put("list", list);
			obj.put("pageInfo", pageInfo);
			returnJson(200, "获取订单成功", obj);
		}catch (BussinessException e){
			logger.error("error {}", e);
			returnJson(400, e.getMessage(), null);
		}catch (Exception e){
			logger.error("error {}", e);
			returnJson(500, "服务器错误", null);
		}

	}

	/**
	 * 借款信息展示
	 * @param  form(userId orderNo)
	 */
	@SysLog(desc = "借款信息展示")
	@RequestMapping(value = "/api/act/mine/borrow/orderInfo.htm", method = RequestMethod.POST)
	public void orderInfo(OrderForm form){
		try{
			Map<String,Object> params = new HashMap<>();

			params.put("userId",form.getUserId());
			params.put("orderNo",form.getOrderNo());
			OrderForm model= null;
			if("10".equals(form.getState()) || "20".equals(form.getState())||"30".equals(form.getState())){
				model=orderService.findOrderByOrderNo(params);
			}else{
				model = orderService.findOrderById(params);
			}

			if(model!=null &&model.getBorrowType().equals("10")){
				model.setBorrowType("信用卡代还");
			}else{
				model.setBorrowType("提现");
			}
			returnJson(200, "查询订单成功", model);
		}catch (BussinessException e){
			logger.error("error {}", e);
			returnJson(400, e.getMessage(), null);
		}catch (Exception e){
			logger.error("error {}", e);
			returnJson(500, "服务器错误", null);
		}

	}


	/**
	 * 信用卡代还订单提交申请
	 */
	@SysLog(desc = "信用卡代还订单提交申请")
	@RequestMapping(value = "/api/act/pay/repayment/apply.htm", method = RequestMethod.POST)
	public void payApply(OrderForm form){
		try {
			Product product = productService.getProduct();
			if(product == null){
				throw new BussinessException("product 产品缺失");
			}
			int level = orderService.accessLevel(form.getAmount(),product);
			if(level == 0){
				returnJson(400,"代还金额无效",null);
				return;
			}
			//获取费用等级
			if(level==0){
				throw new BussinessException("FeeLevel is error");
			}
			BigDecimal serviceFee = product.getFeeLevelAmtStart().add(product.getFeeLevelAmt().multiply(new BigDecimal(level-1)));
			JSONObject obj =  new JSONObject();
			obj.put("serviceFee",serviceFee);
			obj.put("totalMoney",form.getAmount().add(serviceFee));
			obj.put("amount",form.getAmount());
			if(form.getListCouponNo() != null){
				List<String> list = form.getListCouponNo();
				int discount = 0;
				for(String couponNo : list){
					UserCoupon coupon = couponService.findByCouponNo(couponNo);
					if(coupon != null){
						discount += coupon.getAmount();
					}
				}
				obj.put("actualAmount",form.getAmount().add(serviceFee).subtract(new BigDecimal(discount)));
			}else{
				//查询用户优惠券
				Map<String,Object> params = new HashMap<>();
				params.put("userId",form.getUserId());
				params.put("state","10");
				params.put("nowTime",new Date());
				List<UserCoupon> list = couponService.listValid(params);

				if (list.size()==0){
					//state等于0 没有优惠券 1 有优惠券
					obj.put("state",0);
				}else {
					obj.put("state",1);
					obj.put("listCoupon",list);
				}
			}
			returnJson(200, "申请成功", obj);
		}catch (BussinessException e){
			logger.error("error {}", e);
			returnJson(400, e.getMessage(), null);
		}catch (Exception e){
			logger.error("error {}", e);
			returnJson(500, "服务器错误", null);
		}

	}


	/**
	 * 信用卡代还订单提交确认
	 */
	@SysLog(desc = "信用卡代还订单提交确认")
	@RequestMapping(value = "/api/act/pay/repayment/verify.htm", method = RequestMethod.POST)
	public void payVerify(OrderForm form) {
		Order order = new Order();
		order.setUserId(form.getUserId());
		order.setAmount(form.getAmount());
		order.setServiceFee(form.getServiceFee());
		order.setTimeLimit(form.getTimeLimit());
		order.setClient(form.getClient());
		order.setChannelId(form.getChannelId());
		order.setBorrowType(form.getBorrowType());
		order.setOrderNo(NidGenerator.getOrderNo());
		order.setRealAmount(form.getAmount());
		try {
			Product product = productService.getProduct();
			if(product == null){
				throw new BussinessException("product 产品缺失");
			}

			if(order.getBorrowType() == null){
				throw new BussinessException("borrowType 订单类型缺失");
			}

			if(order.getAmount() == null){
				throw new BussinessException("amount 金额缺失");
			}

			UserCredit credit = userCreditService.findByUserId(form.getUserId());
			if(form.getAmount().compareTo(credit.getUnuse())>0){
				returnJson(400,"用户授信额度不足",null);
				return;
			}else if(credit.getState().equals("20")){
				returnJson(400,"用户授信冻结",null);
				return;
			}
			JSONObject Obj = orderService.saveOrder(order,product,form.getListCouponNo(),credit);

			returnJson(200, "申请成功", Obj);
		}catch (BussinessException e){
			logger.error("error {}", e);
			returnJson(400, e.getMessage(), null);
		}catch (Exception e){
			logger.error("error {}", e);
			returnJson(500, "服务器错误", null);
		}
	}

	/**
	 * 服务费计算接口
	 * @param amount
	 */
	@SysLog(desc = "服务费计算")
	@RequestMapping(value = "/api/act/mine/borrow/interest.htm", method = RequestMethod.POST)
	public void  countInterest(@RequestParam(value = "amount", required = true)BigDecimal amount){
		Product product = productService.getProduct();
		if(product == null){
			throw new BussinessException("product 产品缺失");
		}
		int level = orderService.accessLevel(amount,product);
		BigDecimal serviceFee = product.getFeeLevelAmtStart().add(product.getFeeLevelAmt().multiply(new BigDecimal(level-1)));
		returnJson(200, "计算成功", serviceFee);

	}

	/**
	 * 返回二维码路径
	 */
	@SysLog(desc = "返回二维码路径")
	@RequestMapping(value = "/api/act/mine/qrcode/create.htm", method = RequestMethod.POST)
	public void create(@RequestParam(value = "userId", required = true)Long userId){
		User user = userBaseInfoService.selectUser(userId);
		if(user == null){
			throw new BussinessException("userId 用户Id缺失");
		}
		String code = "invitationCode&";
		if(user.getInvitationCode() != null){
			code = "invitationCode&"+user.getInvitationCode();
		}
		returnJson(200, "成功", code);

	}

	public void returnJson(int code, String msg, Object obj){
		Map<String, Object> reposedata = new HashMap<>();
		reposedata.put(Constant.RESPONSE_DATA, obj);
		reposedata.put(Constant.RESPONSE_CODE, code);
		reposedata.put(Constant.RESPONSE_CODE_MSG, msg);
		ServletUtils.writeToResponse(response, reposedata);
	}

	//截取新的路径
	public String spilt(String s) throws Exception{
		int index=s.indexOf("=");
		String url=s.substring(index+1);
		return URLDecoder.decode(url,"UTF-8");
	}
	
}
