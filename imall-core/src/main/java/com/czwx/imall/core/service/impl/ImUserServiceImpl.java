package com.czwx.imall.core.service.impl;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.domain.User;
import com.czwx.imall.core.model.ImUserModel;
import com.czwx.imall.core.service.ImUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.czwx.imall.core.common.service.impl.BaseServiceImpl;
import com.czwx.imall.core.mapper.UserMapper;

/**
 * 用户认证ServiceImpl
 *
 */

@Service("imUserService")
public class ImUserServiceImpl extends BaseServiceImpl<User, Long> implements ImUserService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ImUserServiceImpl.class);

	@Resource
	private UserMapper userMapper;
	
	@Override
	public BaseMapper<User, Long> getMapper() {
		return userMapper;
	}

	@Override
	public Page<ImUserModel> listUser(Map<String, Object> params,
									  int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ImUserModel> list = userMapper.listModel(params);
		return  (Page<ImUserModel>) list;
	}

	@Override
	public List<ImUserModel> listUser(Map<String, Object> params) {
		List<ImUserModel> list = userMapper.listModel(params);
		return  list;
	}

	@Override
	public ImUserModel getModelById(Long id) {
		ImUserModel model = userMapper.getModel(id);
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAllDic() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		List<Map<String, Object>> list = userMapper.queryAllDic();
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> o : list) {
				Map<String, Object> fmap = new HashMap<String, Object>();
				String typeCode = o.get("typeCode").toString();
				List<Map<String, Object>> zlist = new ArrayList<Map<String, Object>>();
				if (!result.isEmpty()) {
			    	boolean flag=false;
			    	 for (Map<String, Object> r: result) {
			    		 if(r.containsKey(typeCode)){
			    			 flag=true; 
			    			 zlist=(List<Map<String, Object>>) r.get(typeCode);
			    			 break;
			    		 }
					  }
			    	 Map<String, Object> zmap=new HashMap<String, Object>();
			    	 zmap.put(o.get("itemCode").toString(), o.get("itemValue").toString());
				     //zmap.put("itemCode", o.get("itemCode").toString());
				     //zmap.put("itemValue",o.get("itemValue").toString());
				     
			    	 if(flag){
			    		 zlist.add(zmap);
			    	 }else{
					     fmap.put(typeCode, zlist);
						 result.add(fmap); 
			    	 }
			    }else{
			    	Map<String, Object> zmap=new HashMap<String, Object>();
			    	zmap.put(o.get("itemCode").toString(), o.get("itemValue").toString());
			    	//zmap.put("itemCode", o.get("itemCode").toString());
			    	//zmap.put("itemValue",o.get("itemValue").toString());
			    	zlist.add(zmap);
			    	fmap.put(typeCode, zlist);
					result.add(fmap);
			    }
			}
		}
		return result;
	}

	@Override
	public boolean updateByUuid(Map<String, Object> paramMap) {
		int result = userMapper.updateByUuid(paramMap);
		if (result > 0){
			return true;
		}
		return false;
	}

	@Override
	public User findByPhone(String phone) {
		return userMapper.findByLoginName(phone);
	}

	@Override
	public long todayCount() {
		return userMapper.todayCount();
	}

}