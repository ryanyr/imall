package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.RepayLog;
import com.czwx.imall.core.model.ManageRepayLogModel;

import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;

public interface RepayLogService extends BaseService<RepayLog, Long> {

    Page<ManageRepayLogModel> listRepayLog(Map<String,Object> searchMap, int current, int pageSize);

    ManageRepayLogModel getModelByOrderId(Long OrderId);

    int save(RepayLog repayLog);
}
