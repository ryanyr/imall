package com.czwx.imall.manage.system.permission;

import com.czwx.imall.system.domain.SysMenu;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

/**
 * Description
 * 
 * @author Robin
 * @version V1.0
 * @createDateTime：2014-10-30 11:35:26
 * @Company:
 * @Copyright: Copyright (c) 2014
 **/
public class CustomPathMatchingFilterChainResolver extends
		PathMatchingFilterChainResolver {
	private CustomDefaultFilterChainManager customDefaultFilterChainManager;

	public void setCustomDefaultFilterChainManager(
			CustomDefaultFilterChainManager customDefaultFilterChainManager) {
		this.customDefaultFilterChainManager = customDefaultFilterChainManager;
		setFilterChainManager(customDefaultFilterChainManager);
	}

	@SuppressWarnings("unchecked")
	public FilterChain getChain(ServletRequest request,
			ServletResponse response, FilterChain originalChain) {
		FilterChainManager filterChainManager = getFilterChainManager();
		if (!filterChainManager.hasChains()) {
			return null;
		}

		String requestURI = getPathWithinApplication(request);
		
		List<String> chainNames = new ArrayList<String>();
		// the 'chain names' in this implementation are actually path patterns
		// defined by the user. We just use them
		// as the chain name for the FilterChainManager's requirements
		for (String pathPattern : filterChainManager.getChainNames()) {
			// If the path does match, then pass on to the subclass
			// implementation for specific checks:
			if (pathMatches(pathPattern, requestURI)) {
				chainNames.add(pathPattern);
			}
		}
		List<SysMenu> list=(List<SysMenu>)SecurityUtils.getSubject().getSession().getAttribute("menuList");

//		System.out.println("customPathMatchingFilterResolver : requestURI -> " + requestURI);
		if (list != null) {
			List<SysMenu> meList = null;
			List<SysMenu> pageList = null;
			SysMenu menu = null;
			SysMenu childMenu = null;
			SysMenu pageMenu = null;

			for (int i=0;i<list.size();i++) { // modules
				menu = list.get(i);
				meList = menu.getMelist();
				boolean isChildActive = false;
				for (int j = 0; j < meList.size(); j++) {
					childMenu = meList.get(j);
					if (requestURI.equals("/"+childMenu.getLinkUrl())){
						isChildActive = true;
						childMenu.setThisclass("active");
					} else {
						childMenu.setThisclass("");
					}

					if (!isChildActive) {
						pageList = childMenu.getMelist();
						if (pageList != null && pageList.size() > 0) {
							for (int k = 0; k < pageList.size(); k++) {
								pageMenu = pageList.get(k);

								if (requestURI.equals("/"+pageMenu.getLinkUrl())){
									isChildActive = true;
									childMenu.setThisclass("active");
									break;
								}
							}
						}
					}
				}
				if (isChildActive) {
					menu.setThisclass("active");
				} else if (requestURI.equals("/" + menu.getLinkUrl())){
					menu.setThisclass("active");
				} else {
					menu.setThisclass("");
				}
			}
		}
		
		if (chainNames.size() == 0) {
			return null;
		}

		return customDefaultFilterChainManager.proxy(originalChain, chainNames);
	} 
	 
}
