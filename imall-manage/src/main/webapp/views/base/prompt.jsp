<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.alibaba.fastjson.JSONArray"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<c:if test="${!empty successMsg}">
	<div class="alert alert-green fadein-top" style="margin-bottom: 4px;">
		<span class="close rotate-hover"></span><strong>${successMsg}</strong>
	</div>
</c:if>
<c:if test="${!empty failMsg}">
	<div class="alert alert-yellow fadein-top" style="margin-bottom: 4px;">
		<span class="close rotate-hover"></span><strong>${failMsg}</strong>
	</div>
</c:if>

	<%
	request.getSession().removeAttribute("successMsg");
	request.getSession().removeAttribute("failMsg");
	%>
