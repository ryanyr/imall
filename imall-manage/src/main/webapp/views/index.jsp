<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="base/commons.jsp" %>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>保赚后台管理系统</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				我的工作台
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-12">
					<!-- today data -->
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">每日数据</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<div class="col-md-6">
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">当天注册数</span>
											<span class="info-box-number">${count.registerCount}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">登录数</span>
											<span class="info-box-number">${count.loginCount}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->

								<!-- fix for small devices only -->
								<div class="clearfix visible-sm-block"></div>

								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">借款申请数</span>
											<span class="info-box-number">${count.applyCount}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">通过数</span>
											<span class="info-box-number">${count.pass}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->
							</div>
							<div class="col-md-6">
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">通过率</span>
											<span class="info-box-number">${count.passRate}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">放款量</span>
											<span class="info-box-number">${count.loanAmount}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->

								<!-- fix for small devices only -->
								<div class="clearfix visible-sm-block"></div>

								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">还款量</span>
											<span class="info-box-number">${count.repayment}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->
								<div class="col-md-3 col-sm-6 col-xs-12">
									<div class="info-box">
										<div class="info-box-content-today">
											<span class="info-box-text">逾期笔数</span>
											<span class="info-box-number">${count.overdue}</span>
										</div>
										<!-- /.info-box-content -->
									</div>
									<!-- /.info-box -->
								</div>
								<!-- /.col -->
							</div>
						</div>
					</div>
					<!--/.box -->
				</div>
			</div>

			<!-- Main row -->
			<div class="row">
				<div class="col-md-6">
					<!-- USERS LIST -->
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">累计数据</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-aqua"><i class="fa fa-users"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">总注册量</span>
										<span class="info-box-number">${count.registerSum}<small>人</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-red"><i class="fa fa-money"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">历史放款总量</span>
										<span class="info-box-number">41,410<small>笔</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->

							<!-- fix for small devices only -->
							<div class="clearfix visible-sm-block"></div>

							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-green"><i class="fa fa-history"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">历史还款总量</span>
										<span class="info-box-number">760<small>笔</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-yellow"><i class="fa fa-bell"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">历史逾期总量</span>
										<span class="info-box-number">2,000<small>笔</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->
						</div>
					</div>
					<!--/.box -->
				</div>

				<div class="col-md-6">
					<!-- USERS LIST -->
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">实时数据</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-aqua"><i class="fa fa-inbox"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">待还款总余额</span>
										<span class="info-box-number">90<small>元</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-red"><i class="fa fa-bell-o"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">逾期未还款总额</span>
										<span class="info-box-number">41,410<small>元</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->

							<!-- fix for small devices only -->
							<div class="clearfix visible-sm-block"></div>

							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-green"><i class="fa fa-check-circle-o"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">已还款总额</span>
										<span class="info-box-number">760<small>元</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-yellow"><i class="fa fa-money"></i></span>

									<div class="info-box-content">
										<span class="info-box-text">已放款总额</span>
										<span class="info-box-number">2,000<small>元</small></span>
									</div>
									<!-- /.info-box-content -->
								</div>
								<!-- /.info-box -->
							</div>
							<!-- /.col -->
						</div>
					</div>
					<!--/.box -->
				</div>
			</div>
			<!-- /.row (main row) -->

			<div class="row">
				<div class="col-md-6">
					<!-- USERS LIST -->
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">每日放款量</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<div class="chart">
								<canvas id="barChart" style="height:230px"></canvas>
							</div>
						</div>
					</div>
					<!--/.box -->
				</div>

				<div class="col-md-6">
					<!-- USERS LIST -->
					<div class="box box-danger">
						<div class="box-header with-border">
							<h3 class="box-title">每日还款量</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<div class="chart">
								<canvas id="barChart2" style="height: 230px; width: 595px;" width="743" height="287"></canvas>
							</div>
						</div>
					</div>
					<!--/.box -->
				</div>
			</div>
			<!-- /.row (main row) -->

		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<footer class="main-footer">
		<%--<div class="pull-right hidden-xs">
			<b>Version</b> 2.4.0
		</div>
		<strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
		reserved.--%>
	</footer>

	<!-- Control Sidebar -->

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->


<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>

<!-- Morris.js charts -->
<script src="<%=basePath %>bower_components/raphael/raphael.min.js"></script>
<script src="<%=basePath %>bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="<%=basePath %>bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="<%=basePath %>bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- Slimscroll -->
<script src="<%=basePath %>bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=basePath %>bower_components/fastclick/lib/fastclick.js"></script>


</body>
</html>
