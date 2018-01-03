package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.RepayLog;
import com.czwx.imall.core.mapper.RepayLogMapper;
import com.czwx.imall.core.model.ManageRepayLogModel;
import com.czwx.imall.core.service.RepayLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("repayLogService")
@Transactional(rollbackFor = Exception.class)
public class RepayLogServiceImpl extends BaseServiceImpl<RepayLog, Long> implements RepayLogService {

    private static final Logger logger = LoggerFactory.getLogger(RepayLogServiceImpl.class);

    @Resource
    private RepayLogMapper repayLogMapper;

    @Override
    public BaseMapper<RepayLog, Long> getMapper() {
        return repayLogMapper;
    }

    @Override
    public Page<ManageRepayLogModel> listRepayLog(Map<String, Object> searchMap, int current, int pageSize) {
        PageHelper.startPage(current, pageSize);
        List<ManageRepayLogModel> list = repayLogMapper.listRepayLog(searchMap);

        return (Page<ManageRepayLogModel>) list;
    }

    @Override
    public ManageRepayLogModel getModelByOrderId(Long orderId) {
        ManageRepayLogModel model = new ManageRepayLogModel();
        RepayLog repayLog = repayLogMapper.selectByOrderId(orderId);
        if (repayLog == null) {
            logger.error("查询的订单不存在");
        } else {
            model = ManageRepayLogModel.instance(repayLog);
        }
        return model;
    }

    @Override
    public int save(RepayLog repayLog) {
        return repayLogMapper.save(repayLog);
    }

}
