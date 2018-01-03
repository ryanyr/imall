package com.czwx.imall.manage.common.base;

import com.czwx.imall.system.domain.SysMenu;
import com.czwx.imall.system.domain.SysUser;
import com.czwx.imall.system.service.SysMenuService;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.czwx.imall.manage.common.constants.SessionConstants;
import com.czwx.imall.manage.common.helper.SpringContextHolder;

/**
 * Description: base Controller
 * 
 * @author Robin
 * @version V1.0
 * @createDateTime 2014-10-28
 * 
 */
public class BaseController extends GenericService {
	@Autowired
	private SysMenuService sysMenuService;

	public CacheManager cacheManager = CacheManager.getInstance(); // 缓存
	public List<SysMenu> list = null;
	public String BASE_REDIRECT_MARK="redirect:";
 
//	 // 菜单获取缓存
//	 Cache cache=cacheManager.getCache("mycache");
//	 Element element=cache.get("menulist");
	public void setSuccessMsg(String successMsg){
		this.getRequest().getSession().setAttribute("successMsg", successMsg);
	}
	public void setFailMsg(String failMsg){
		this.getRequest().getSession().setAttribute("failMsg", failMsg);
	}
	/**
	 * 获取session 用户信息
	 * 
	 * @return
	 */
	public SysUser getSessionUser() {
		return (SysUser) SecurityUtils.getSubject().getSession()
				.getAttribute(SessionConstants.CURRENT_USER);
	}
	public ServletContext getServletContext(){
		return SpringContextHolder.getServletContext();
	}
	
	public HttpServletRequest getRequest(){
		return	 ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}
}
