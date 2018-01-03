package com.czwx.imall.manage.system.permission.filter;

import com.czwx.imall.system.domain.SysMenu;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.service.SysMenuService;
import com.czwx.imall.system.service.SysUserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.czwx.imall.manage.common.constants.SessionConstants;

/**
 * Description
 * 
 * @author Robin
 * @version V1.0
 * @createDateTime：2014-10-30 11:35:26
 * @Company:
 * @Copyright: Copyright (c) 2014
 **/
public class SysUserFilter extends PathMatchingFilter {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		SysUser user = new SysUser();
		Session session = SecurityUtils.getSubject().getSession();
		if (userName != null) {
			if (session.getAttribute(SessionConstants.CURRENT_USER) == null) {
				user = sysUserService.queryUserInfo(userName);
				session.setAttribute(SessionConstants.CURRENT_USER, user);
			}

			// 查询菜单
			if (session.getAttribute("menuList") == null
					|| ((List<SysMenu>) session.getAttribute("menuList"))
							.size() <= 0) {
				List<SysMenu> menuList = sysMenuService.queryMenu(userName);

				for (SysMenu m : menuList) {
					System.out.println("SysUserFilter : " + m.getLinkUrl());
				}

				// 默认加载root目录菜单
				if(menuList.size()>0){
					List<SysMenu> list= getList(menuList, "ROOT");
					this.setSession("superMenu",list.get(0));
					this.setSession("menuList",list);
				}
			}
		}

		return true;

	}

	/**
	 * 处理用户菜单
	 * 
	 * @param tem
	 * @param str
	 * @return
	 */
	public List<SysMenu> getList(List<SysMenu> tem, String str) {
		List<SysMenu> list = new ArrayList<SysMenu>();
		tem.get(0).setThisclass("active");
		for (int i = 0; i < tem.size(); i++) {
			SysMenu do1 = tem.get(i);
			if (str.equals(do1.getSuperiorId())) {
				do1.setMelist(getList(tem, do1.getMenuId()));
				list.add(do1);
			}
			// 我的工作台 快捷菜单 2016-01-05
			if ("1".equals(str)) {
				if ("1".equals(do1.getPermValid())) {
					list.add(do1);
				}

			}
		}
		Collections.sort(list, new Comparator<SysMenu>() {

			@Override
			public int compare(SysMenu o1, SysMenu o2) {
				// TODO Auto-generated method stub
				if (o1.getMenuOrder() < o2.getMenuOrder()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return list;
	}
	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}
