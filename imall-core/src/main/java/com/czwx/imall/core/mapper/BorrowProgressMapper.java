package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.BorrowProgress;
import com.czwx.imall.core.model.BorrowProgressModel;
import com.czwx.imall.core.model.ManageBorrowProgressModel;
import java.util.List;
import java.util.Map;

/**
 * 借款进度表Dao
 */
@RDBatisDao
public interface BorrowProgressMapper extends BaseMapper<BorrowProgress,Long> {

	/**
	 * 首页查询进度
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listIndex(Map<String, Object> bpMap);
	
	/**
	 * 后台借款进度列表
	 * @param bpMap
	 * @return
	 */
	List<ManageBorrowProgressModel> listModel(Map<String, Object> params);

	/**
	 * 借款进度查询
	 * @param bpMap
	 * @return
	 */
	List<BorrowProgressModel> listProgress(Map<String, Object> bpMap);

}
