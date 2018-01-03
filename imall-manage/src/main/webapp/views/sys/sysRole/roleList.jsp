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
				角色管理
			</h1>
		</section>

		<!-- Main content -->
		<form class="form-inline" role="form" id="queryform" name="queryform" method="post" action="<%=basePath%>sys/sysRole/list">
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="box-body">
									<div class="form-group">
										<button type="button" class="btn btn-primary" onclick="javascript:location.href='<%=basePath%>sys/sysRole/roleAdd';">新增</button>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
									<tr>

										<th>角色编号</th>
										<th>角色名称</th>
										<th>创建者</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="sysRole">
										<tr>

											<td>${sysRole.roleId}</td>
											<td>${sysRole.roleName}</td>
											<td>${sysRole.creator}</td>
											<td><fmt:formatDate value="${sysRole.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<span><a href="#">删除</a></span>
												<span><a href="<%=basePath%>sys/sysRole/rolePerm/detail?roleId=${sysRole.roleId}">分配权限</a></span>
											</td>
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
									action="/sys/sysRole/list" />
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
