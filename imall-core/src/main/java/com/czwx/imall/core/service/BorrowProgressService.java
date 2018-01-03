package com.czwx.imall.core.service;

import com.czwx.imall.core.common.service.BaseService;
import com.czwx.imall.core.domain.BorrowProgress;
import com.czwx.imall.core.domain.Order;
import com.czwx.imall.core.model.ManageBorrowProgressModel;
import com.github.pagehelper.Page;
import java.util.List;
import java.util.Map;

/**
 * 借款进度表Service
 */
public interface BorrowProgressService extends BaseService<BorrowProgress, Long> {

	/**
	 * 进度查询
	 * @param order
	 * @return
	 */
	Map<String,Object> result(Order order);

	/**
	 * 后台还款进度列表
	 * @param params
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	Page<ManageBorrowProgressModel> listModel(Map<String, Object> params,
											  int currentPage, int pageSize);

	/**
	 * 保存借款进度
	 * @param borrowProgress
	 * @return
	 */
	boolean save(BorrowProgress borrowProgress);

	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	List<BorrowProgress> listSeletetive(Map<String, Object> map);
}
