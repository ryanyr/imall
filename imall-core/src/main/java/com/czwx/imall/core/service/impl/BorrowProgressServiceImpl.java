package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.domain.BorrowProgress;
import com.czwx.imall.core.domain.Order;
import com.czwx.imall.core.mapper.BorrowProgressMapper;
import com.czwx.imall.core.mapper.OrderMapper;
import com.czwx.imall.core.model.ManageBorrowProgressModel;
import com.czwx.imall.core.service.BorrowProgressService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 借款进度表ServiceImpl
 */
 
@Service("borrowProgressService")
public class BorrowProgressServiceImpl extends BaseServiceImpl<BorrowProgress, Long> implements BorrowProgressService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BorrowProgressServiceImpl.class);
   
    @Resource
    private BorrowProgressMapper borrowProgressMapper;
    @Resource
    private OrderMapper orderMapper;

	@Override
	public BaseMapper<BorrowProgress, Long> getMapper() {
		return borrowProgressMapper;
	}

	
	@Override
	public Map<String,Object> result(Order borrow) {
		// to do
		return null;
	}
	
	@Override
	public  Page<ManageBorrowProgressModel> listModel(Map<String, Object> params, int currentPage,
													  int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowProgressModel> list = borrowProgressMapper.listModel(params);
		return (Page<ManageBorrowProgressModel>)list;
	}

	@Override
	public boolean save(BorrowProgress borrowProgress){
		int result = borrowProgressMapper.save(borrowProgress);
		if(result > 0){
			return true;
		}
		return false;
	}

	@Override
	public List<BorrowProgress> listSeletetive(Map<String, Object> map) {
		return borrowProgressMapper.listSelective(map);
	}

	
}
