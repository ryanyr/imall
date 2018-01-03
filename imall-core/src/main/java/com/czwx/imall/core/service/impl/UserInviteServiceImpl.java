package com.czwx.imall.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.czwx.imall.core.common.exception.BussinessException;
import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.*;
import com.czwx.imall.core.form.OrderForm;
import com.czwx.imall.core.mapper.*;
import com.czwx.imall.core.model.ManageOrderModel;
import com.czwx.imall.core.service.OrderService;
import com.czwx.imall.core.service.ProductService;
import com.czwx.imall.core.service.UserInviteService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userInviteService")
@Transactional(rollbackFor = Exception.class)
public class UserInviteServiceImpl extends BaseServiceImpl<UserInvite, Long> implements UserInviteService {

    private static final Logger logger = LoggerFactory.getLogger(UserInviteServiceImpl.class);

    @Resource
    private UserInviteMapper userInviteMapper;

    @Override
    public BaseMapper<UserInvite, Long> getMapper() {
        return userInviteMapper;
    }
}