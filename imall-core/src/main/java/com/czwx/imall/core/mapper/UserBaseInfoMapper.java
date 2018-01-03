package com.czwx.imall.core.mapper;

import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.model.ManagerUserModel;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.czwx.imall.core.common.mapper.RDBatisDao;
import com.czwx.imall.core.domain.UserBaseInfo;

/**
 * 用户详细信息Dao
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface UserBaseInfoMapper extends BaseMapper<UserBaseInfo,Long> {

	List<Map<String, Object>> getDictsCache(String type);

	ManagerUserModel getBaseModelByUserId(Long userId);

	UserBaseInfo findUserImags(@Param("userId") Long userId);


	/**
	 * 据用户id查询用户详细信息
	 * 
	 * @param userId
	 * @return
	 */
	UserBaseInfo findByUserId(@Param("userId") Long userId);


	/**
	 * 获取身份证照片信息
	 */
	UserBaseInfo findUserCardPics(@Param("userId") String userId);

	int insertSelective(UserBaseInfo userBaseInfo);

	int updateByPrimaryKey(UserBaseInfo userBaseInfo);

	Map<String,Object>  getUserInfo(String userId);

}
