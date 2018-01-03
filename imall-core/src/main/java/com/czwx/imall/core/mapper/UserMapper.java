package com.czwx.imall.core.mapper;


import com.czwx.imall.core.common.mapper.BaseMapper;
import com.czwx.imall.core.domain.User;
import com.czwx.imall.core.model.ImUserModel;
import java.util.List;
import java.util.Map;

import com.czwx.imall.core.common.mapper.RDBatisDao;

/**
 * 用户管理Dao
 *
 */
@RDBatisDao
public interface UserMapper extends BaseMapper<User,Long> {

	List<ImUserModel> listModel(Map<String, Object> params);

	ImUserModel getModel(Long id);

	List<Map<String, Object>> queryAllDic();

	/**
	 * 手机号查询id
	 * @param phone
	 * @return
	 */
	User findByLoginName(String phone);

	/**
	 * 修改等级
	 * @param user
	 * @return
	 */
	int updateLevel(User user);

	/**
	 * 查询用户等级
	 * @param map
	 * @return
	 */
	List<User> findUserLevel(Map<String, Object> map);

	/**
	 * 据uuid 修改用户信息
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateByUuid(Map<String, Object> paramMap);
	
	/**
	 * 今日注册用户数
	 * @return
	 */
	long todayCount();

	void updateByPrimaryKeySelective(User user);

	User selectByUserId(long userId);


	User findByInvitationCode(String invitationCode);

	long selectLoginCount(String dateNowStr);

	long selectRegisterCount(String dateNowStr);

}
