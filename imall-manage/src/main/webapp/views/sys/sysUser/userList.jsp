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
				用户管理
			</h1>
		</section>

		<!-- Main content -->
		<form class="form-inline" role="form" id="queryform" name="queryform" method="post" action="<%=basePath%>sys/sysUser/list">
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="box-body">
									<div class="form-group">
										<label for="inputPhone">手机号</label>
										<input type="text" class="form-control" id="inputPhone" name="phone" placeholder="输入手机号" value="${searchParam.phone}">
									</div>
									<div class="form-group">
										<label for="inputName">姓名</label>
										<input type="text" class="form-control" id="inputName" name="realName" placeholder="输入姓名" value="${searchParam.realName}">
									</div>
									<div class="form-group">
										<span><button type="submit" class="btn btn-primary">查询</button></span>
										<span><button type="button" class="btn btn-primary" onclick="javascript:location.href='<%=basePath%>sys/sysUser/userAdd';">新增</button></span>
										<span><button type="button" class="btn btn-primary" onclick="formReset()">重置</button></span>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
									<tr>
										<th>用户名</th>
										<th>姓名</th>
										<th>手机号码</th>
										<th>邮箱</th>
										<th>注册时间</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="sysUser">
										<tr>
											<td>${sysUser.userName}</td>
											<td>${sysUser.trueName}</td>
											<td>${sysUser.phone}</td>
											<td>${sysUser.email}</td>
											<td><fmt:formatDate value="${sysUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
											<td>
												<a href="<%=basePath%>sys/sysUser/userInfo?userId=${sysUser.userId}">查看</a>
												<a href="<%=basePath%>sys/sysUser/userEdit?userId=${sysUser.userId}">修改</a>
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
									action="/sys/sysUser/list" />
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

    function formReset() {
        $(':input,#myform')
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('checked')
            .removeAttr('selected');
    }

</script>
</body>
</html>
