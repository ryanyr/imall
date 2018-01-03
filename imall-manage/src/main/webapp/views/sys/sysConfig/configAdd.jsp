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
				权限信息
			</h1>
		</section>

		<form action="<%=basePath %>sys/sysConfig/save" method="post" id="from1" name="from1">
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">新增系统参数</h3>

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
									<dt>参数编号：</dt>
									<dd>
										<div class="form-group">
											<input type="text" class="form-control" id="inputConfigCode" name="code" />
										</div>
									</dd>
									<dt>参数名称：</dt>
									<dd>
										<div class="form-group">
											<input type="text" class="form-control" id="inputConfigName" name="name" />
										</div>
									</dd>
									<dt>参数值：</dt>
									<dd>
										<div class="form-group">
											<input type="text" class="form-control" id="inputConfigValue" name="value" />
										</div>
									</dd>
									<dt>备注：</dt>
									<dd>
										<div class="form-group">
											<input type="text" class="form-control" id="inputRemark" name="remark" />
										</div>
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

			<div class="text-center">
				<button type="button" class="btn bg-maroon margin" onclick="javascript:location.href='<%=basePath%>sys/sysConfig/list';">取消</button>
				<button id="submit" type="submit" class="btn bg-maroon margin">保存</button>
			</div>
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

</body>
</html>
