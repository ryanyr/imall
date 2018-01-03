package com.czwx.imall.manage.common.page;

import com.czwx.imall.core.common.util.RdPage;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * Description: 分页tag,生成分页的跳转标识
 * @author  Robin
 * @version V1.0 
 * @createDateTime：2014-10-30  11:35:26 
 * @Company: 
 * @Copyright: Copyright (c) 2014
 **/
public class PaginatorTag extends TagSupport{
	private static Logger logger=Logger.getLogger(PaginatorTag.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String form;// 页面的form名

	private String action;// 跳转的action URL		

	private String name; // 分页器的名称

	private String scope = "request"; // 分页器所在位置page/request/session/application

	private static final Map<String,Integer> scopeMap = new HashMap<String,Integer>(); // 属性范围Map

	static {
		// 初始化属性范围Map
		scopeMap.put("page", PageContext.PAGE_SCOPE);
		scopeMap.put("request",PageContext.REQUEST_SCOPE);
		scopeMap.put("session",PageContext.SESSION_SCOPE);
		scopeMap.put("application", PageContext.APPLICATION_SCOPE);
	}
	public int doEndTag() throws JspTagException {
		RdPage paginator = (RdPage) pageContext.getAttribute(name, ((Integer) scopeMap.get(scope)).intValue());
		JspWriter out = pageContext.getOut();
		StringBuffer buffer = new StringBuffer(1000);
		int totalPage = paginator.getPages();
		try {
			if (paginator.getTotal() > 0 && totalPage>1) {// 有记录
				buffer.append("<script language=\"JavaScript\">\n");
				buffer.append("<!--\n");
				buffer.append("function GotoPage(id,flag) {\n");
				buffer.append("document." + form + ".action='" + action
						+ "';\n");
				buffer.append("if(id<=0){id=1;}\n");
				buffer.append("document." + form + ".currentPage.value=id;\n");
				buffer.append("document." + form + ".currentFlag.value=flag;\n");
				buffer.append("document." + form + ".submit();\n");
				buffer.append("}\n");
				buffer.append("function checkPage(size,page,total) {\n");
				buffer.append("if(total)");
				buffer.append("document." + form + ".action='" + action
						+ "';\n");
				buffer.append("document." + form + ".currentPage.value=id;\n");
				buffer.append("document." + form + ".submit();\n");
				buffer.append("}\n");		
				
				buffer.append("function checkPageSize(page,size,total){\n");
				buffer.append("var reg = /[^0-9]/;\n");
				buffer.append("if(size.match(reg) != null){\n");
				buffer.append("alert('每页记录数请使用数字');\n");				
				buffer.append("document."+form+".pageSize.focus();}\n");
				buffer.append("if(size<1){\n");
				buffer.append("document."+form+".pageSize.value=10;}\n");
				buffer.append("if(size>300){\n");
				buffer.append("document."+form+".pageSize.value=300;}\n");
				buffer.append("var newsize = document."+form+".pageSize.value;\n");
				buffer.append("checkPage(page,newsize,total);\n");
				buffer.append("}\n");
				buffer.append("function checkPage(page,size,total){\n");
				buffer.append("var reg = /[^0-9]/;\n");
				buffer.append("if(page.match(reg) != null){\n");
				buffer.append("alert('跳转页数请使用数字');\n");				
				buffer.append("document."+form+".currentPage.focus();}\n");
				buffer.append("var num = total/size;  \n");
				buffer.append("var nums = Math.round(num+0.4999999);\n");
				buffer.append("if(page<1){\n");
				buffer.append("document."+form+".currentPage.value=1;}\n");
				buffer.append("if(page > nums){\n");
				buffer.append("document."+form+".currentPage.value=nums;}\n");
				buffer.append("}\n");
				buffer.append("//-->\n");
				buffer.append("</script>\n");
				
				/**=====================以上为跳转的js 以下为显示的html========================*/
				buffer.append("<div class=\"clearall\">");
				buffer.append("<div class='pagination'>" );
				if (paginator.getCurrent() > 1) {// 不是第一页，显示“第一页”，“上一页”链接

					buffer.append("<a href=\"javascript:GotoPage("+ (paginator.getCurrent() - 1)+ ",0)\" class=\"page-prev\" title='上一页'><span>上一页</span></a>");
					if (paginator.getCurrent() > 4 && totalPage>5){
				 	}
				} else {// 是第一页，不显示“第一页”，“上一页”链接
//					buffer.append("<input type=\"button\" class=\"first\" value=\"首&nbsp;&nbsp;页\" disabled=\"disabled\" />");
//					buffer.append("<input type=\"button\" class=\"prev\"  value=\"上一页\" disabled=\"disabled\" />");
				}
				//对中间页面的处理
				if(paginator.getPages()>=paginator.getShowPage()){
					int frist=getFrist(paginator);
					if(frist>1){
						buffer.append("<a href=\"javascript:GotoPage(1,0)\" class=\"next\" title=\"首页\"><span>1</span></a>");
						if(frist>=(1+1)){
							buffer.append("<span class=\"page-break\">...</span>");
						}
					}
					int end = frist + paginator.getShowPage() - 1;
					if (end > paginator.getPages()) {
						end = paginator.getPages();
					}
					for (int i=frist;i<=end;i++){
						//判断是否为当前页
						if(paginator.getCurrent()==i){
							buffer.append("<span class=\"page-cur\" >"+i+ "</span>");
						}else{
							buffer.append("<a href=\"javascript:GotoPage("+i+ ",0)\" class=\"next\" title=\"\"><span>"+i+"</span></a>");
						}
					}
					if(frist+paginator.getShowPage()<=paginator.getPages()){
						buffer.append("<span class=\"page-break\">...</span>");
					}

					if (paginator.getCurrent() != totalPage && totalPage > 1) {
						int calIndex=totalPage-paginator.getCurrent();
						int calIndex2=totalPage-(paginator.getCurrent()+(paginator.getShowPage()-1)/2);
						if(calIndex2>=1 && totalPage>paginator.getShowPage()){
							buffer.append("<a href=\"javascript:GotoPage("+ totalPage+ ",0)\" class=\"next\" title=\"尾页\"><span>"+totalPage+"</span></a>");
						}
						buffer.append("<a href=\"javascript:GotoPage("+ (paginator.getCurrent() + 1)+ ",0)\" class=\"page-next\" title='下一页'><span>下一页</span></a>");
					}
				}else{
					for(int i=1;i<=paginator.getPages();i++){
						//判断是否为当前页
						if(paginator.getCurrent()==i){
							buffer.append("<span class=\"page-cur\" >"+i+ "</span>");
						}else{
							buffer.append("<a href=\"javascript:GotoPage("+i+ ",0)\" class=\"next\" title=\"\"><span>"+i+"</span></a>");
						}
					}
					if (paginator.getCurrent() != totalPage && totalPage > 1) {
						buffer.append("<a href=\"javascript:GotoPage("+ (paginator.getCurrent() + 1)+ ",0)\" class=\"page-next\" title='下一页'><span>下一页</span></a>");
					}
				}

				buffer.append(" <strong>  <input type='hidden'  name='currentPage' class=\"page-skip\" value='"+ paginator.getCurrent() + "'onkeyup=\"value=value.replace(/^[^1-9]+|[^\\d]/g,'');\" onblur='checkPage(document.all.currentPage.value,document.all.pageSize.value,"+paginator.getTotal()+")'/>");
				buffer.append(" <strong> 到第 <input type='text'  name='pagetag' class=\"page-skip\" value=''onkeyup=\"value=value.replace(/^[^1-9]+|[^\\d]/g,'');\" onblur='checkPage(document.all.currentPage.value,document.all.pageSize.value,"+paginator.getTotal()+")'/>页</strong>");
				buffer.append("<input type='hidden'  name='currentFlag' class=\"page-skip\" onkeyup=\"value=value.replace(/^[^1-9]+|[^\\d]/g,'');\" onblur='checkPage(document.all.currentPage.value,document.all.pageSize.value,"+paginator.getTotal()+")'/>");
//				buffer.append("页" );	
//				buffer.append("<button type=\"submit\" title=\"指定页码\" onclick=\"javascript:GotoPage(document.all.currentPage.value);\">确定</button></span>");
				buffer.append("<a href=\"javascript:GotoPage(document.all.pagetag.value,0);\" class=\"bt_go\" title=\"GO\"><span>GO</span></a>" );	
				buffer.append("<span class='page-skip'><span>共"+paginator.getTotal()+"条</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				buffer.append("<span>第"+paginator.getCurrent()+"页/共" + totalPage + "页</span></span>");
				buffer.append("</div></div> ");
			} 
			out.write(buffer.toString());
			out.flush();
		} catch (Exception e) {
			logger.error("生成分页html发生错误",e);			
		}

		return EVAL_PAGE;
	}

	public void setForm(String form) {
		this.form = form;
	}
	public void setAction(String action) {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		this.action = request.getContextPath() + action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getImagePath(){
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		return request.getContextPath()+"/images/go.gif";
	}
	/**
	 * 获取第一页
	 * @param paginator 分页对象
	 * @return
	 */
	private int getFrist(RdPage paginator){
		int frist=1;
		//当前页面
		int curPage=paginator.getCurrent();
		//总页数
		int totalPage=(int)paginator.getTotal();
		//获取当前页面的位置
		int s=paginator.getShowPage()/2;
		if(curPage-s<1){
			frist=1;
		}else if((curPage+s)>=totalPage){
			frist=totalPage-paginator.getShowPage()+1;
		}else{
			frist=curPage-s;
		}
		return frist;
	}
}
