<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="paginator" uri="/WEB-INF/paginator.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../../base/commons.jsp" %>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>保赚后台管理系统</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="../../base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				系统参数配置
			</h1>
		</section>

		<!-- Main content -->
		<form class="form-inline" role="form" id="queryform" name="queryform" method="post" action="<%=basePath%>sys/sysConfig/list">
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="box-body">
									<div class="form-group">
										<button type="button" class="btn btn-primary" onclick="javascript:location.href='<%=basePath%>sys/sysConfig/toAdd';">新增</button>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
									<tr>
										<th>参数编号</th>
										<th>参数名称</th>
										<th>参数值</th>
										<%--<th>参数类型</th>--%>
										<th>备注</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="sysConfig">
										<tr>
											<td>${sysConfig.code}</td>
											<td>${sysConfig.name}</td>
											<td>${sysConfig.value}</td>
											<%--<td>${sysConfig.type}</td>--%>
											<td>${sysConfig.remark}</td>
											<td>
											<c:choose>
												<c:when test="${sysConfig.status eq '1'}">启用</c:when>
												<c:otherwise>不启用</c:otherwise>
											</c:choose>
											</td>
											<td><a href="<%=basePath%>sys/sysConfig/toUpdate?configId=${sysConfig.id}">编辑</a></td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

				<!-- 分页标签开始  -->

				<div class="">
					<paginator:page name="paginator" form="queryform"
									action="/sys/sysConfig/list" />
				</div>
				<!-- 分页标签结束 -->

			</section>
		</form>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@include file="../../base/footer.jsp"%>

	<!-- Control Sidebar -->

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- page script -->
<script>

</script>
</body>
</html>
