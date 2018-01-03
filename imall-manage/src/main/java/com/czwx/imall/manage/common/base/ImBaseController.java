package com.czwx.imall.manage.common.base;

import com.czwx.imall.manage.common.constants.SessionConstants;
import com.czwx.imall.manage.common.page.PageParam;
import com.czwx.imall.system.domain.SysUser;
import com.github.pagehelper.PageHelper;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class ImBaseController {

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    protected SysUser getLoginUser(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute(SessionConstants.CURRENT_USER);
        if(obj != null){
            return (SysUser) obj;
        }
        return null;
    }

    protected PageParam pageinator(HttpServletRequest request) {
        PageParam page = new PageParam();
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        int currentPage = 0, pageSize = 10;
        try{
            if (StringUtils.isNotBlank(currentPageStr)){
                currentPage = Integer.parseInt(currentPageStr);
            }
            if (StringUtils.isNotBlank(pageSizeStr)){
                pageSize = Integer.parseInt(pageSizeStr);
            }
        }catch (Exception e){
        }
        page.setCurrent(currentPage);
        page.setPageSize(pageSize);
        return page;
    }
}
