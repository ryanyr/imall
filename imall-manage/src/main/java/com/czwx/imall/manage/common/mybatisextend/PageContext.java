package com.czwx.imall.manage.common.mybatisextend;

import javax.servlet.http.HttpServletRequest;

import com.czwx.imall.manage.common.page.Paginator;
/** 
 * Description
 * @author  Robin
 * @version V1.0 
 * @createDateTime：2014-10-30  11:35:26 
 * @Company: 
 * @Copyright: Copyright (c) 2014
 **/
public class PageContext extends Paginator  {
	private static ThreadLocal<PageContext> context = new ThreadLocal<PageContext>();

	public static PageContext getContext()
	{
		PageContext ci = context.get();
		if(ci == null)
		{
			ci = new PageContext();
			context.set(ci);
		}
		return ci;
	}
	public static PageContext getContext(HttpServletRequest request){
//		PageContext pageContext =new PageContext();
		PageContext pageContext = context.get();
		if(pageContext == null)
		{
			pageContext = new PageContext();
			context.set(pageContext);
		}
		String pageSize = getParameterWithDefault(request, "pageSize","10");   //每页十条
        pageContext.setPageSize(Integer.parseInt(pageSize));
        String currentPage = getParameterWithDefault(request, "currentPage", "1");
        pageContext.setCurrentPage(Integer.parseInt(currentPage));
        pageContext.setPagination(true);
		String currentFlag=getParameterWithDefault(request, "currentFlag", "1");
		if(!currentFlag.equals("0")){
			pageContext.setCurrentPage(1);
		}
        context.set(pageContext);
		return pageContext;
	}
	public static PageContext getContext(HttpServletRequest request,String pagesize){
//		PageContext pageContext =new PageContext();
		PageContext pageContext = context.get();
		if(pageContext == null)
		{
			pageContext = new PageContext();
			context.set(pageContext);
		}
		String pageSize = getParameterWithDefault(request, "pageSize",pagesize);   //每页十条
        pageContext.setPageSize(Integer.parseInt(pageSize));
        String currentPage = getParameterWithDefault(request, "currentPage", "1");
        pageContext.setCurrentPage(Integer.parseInt(currentPage));
        pageContext.setPagination(true);
		String currentFlag=getParameterWithDefault(request, "currentFlag", "1");
		if(!currentFlag.equals("0")){
			pageContext.setCurrentPage(1);
		}
        context.set(pageContext);
		return pageContext;
	}
	 public static String getParameterWithDefault(HttpServletRequest request, String name, String defaultValue)
	 {
	     String paramValue = request.getParameter(name);
	     return paramValue != null ? paramValue.trim() : defaultValue;
	  }
	public  static void removeContext()
	{
		context.remove();
	}
	protected void initialize() {

	}
	
	public static void setContent(PageContext pc){
	    context.set(pc);
	}
}
