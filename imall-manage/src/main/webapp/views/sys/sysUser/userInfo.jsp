<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				系统用户信息
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">基本信息</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
								title="Collapse">
							<i class="fa fa-minus"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="detail-section">
						<div class="row">
							<div class="col-sm-5">
								<dl class="dl-horizontal">
									<dt>用户名：</dt>
									<dd>${sysUser.userName}</dd>
									<dt>手机号：</dt>
									<dd>${sysUser.phone}</dd>
								</dl>
							</div>
							<div class="col-sm-7" id="cluster_info">
								<dl class="dl-horizontal">
									<dt>真实姓名：</dt>
									<dd>${sysUser.trueName}</dd>
									<dt>邮箱：</dt>
									<dd>${sysUser.email}</dd>
								</dl>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-5">
								<dl class="dl-horizontal">
									<dt>角色：</dt>
									<dd>
										<span class="select2 select2-container select2-container--default select2-container--below select2-container--focus" style="width: 100%;">
											<span class="selection">
												<span class="select2-selection select2-selection--multiple" >
												<ul class="select2-selection__rendered">
													<c:forEach items="${sysUser.roles}" var="role">
														<li class="select2-selection__choice" title="${role.roleName}">${role.roleName}</li>
													</c:forEach>
												</ul>
												</span>
											</span>
										</span>
									</dd>
								</dl>
							</div>
						</div>

					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

		</section>
		<!-- /.content -->

		<div class="text-center">
			<button type="button" class="btn bg-maroon margin" onclick="javascript:location.href='<%=basePath%>sys/sysUser/list';">返回</button>
		</div>


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

<script src="<%=basePath %>js/plugins/form/jquery.form.js"></script>
<!-- page script -->
<script>
$(function() {

});
</script>
</body>
</html>
