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
<%@include file="../base/commons.jsp" %>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>保赚后台管理系统</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="../base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				还款记录信息
			</h1>
		</section>

		<!-- Main content -->
		<form class="form-inline" role="form" id="queryform" name="queryform" method="post" action="<%=basePath%>borrowRepay/repayLog/list">
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
										<label for="inputOrderNo">订单号</label>
										<input type="text" class="form-control" id="inputOrderNo" name="orderNo" placeholder="输入订单号" value="${searchParam.orderNo}">
									</div>
									<div class="form-group">
										<label>订单生成时间</label>
										<input type="text" class="form-control" name="startTime" id="qBeginTime" value="${searchParam.startTime}" />
										<span>~</span>
										<input type="text" class="form-control" name="endTime" id="qEndTime" value="${searchParam.endTime}" />
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary">查询</button>
										<button type="button" class="btn btn-primary" onclick="formReset()">重置</button>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
									<tr>
										<th>客户姓名</th>
										<th>手机号码</th>
										<th>订单号</th>
										<th>提现金额</th>
										<th>还款金额</th>
										<th>实际还款时间</th>
										<th>逾期天数</th>
										<th>逾期罚金</th>
										<th>延期金额</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="order">
										<tr>
											<td>${order.realName}</td>
											<td>${order.phone}</td>
											<td>${order.orderNo}</td>
											<td>${order.amount} 元</td>
											<td>${order.repayAmount} 元</td>
											<td><fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
											<td>${order.penaltyDay}天</td>
											<td>${order.penaltyAmount}元</td>
											<td>${order.extensionAmount}元</td>
											<td><a href="<%=basePath%>borrowRepay/repayLog/repayLogDetail?orderId=${order.orderId}">查看</a></td>
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
									action="/borrowRepay/repayLog/list" />
				</div>
				<!-- 分页标签结束 -->

			</section>
		</form>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@include file="../base/footer.jsp"%>

	<!-- Control Sidebar -->

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- page script -->
<script>
    $(function() {
        $("#inputOrderState").val('${searchParam.state}');

        $('#qBeginTime').datepicker({
            todayBtn : "linked",
            autoclose : true,
            todayHighlight : true,
            language:"zh-CN",
            format:"yyyy-mm-dd",
            endDate : new Date()
        }).on('changeDate',function(e){
            var startTime = e.date;
            $('#qEndTime').datepicker('setStartDate',startTime);
        });
        $('#qEndTime').datepicker({
            todayBtn : "linked",
            autoclose : true,
            todayHighlight : true,
            language:"zh-CN",
            format:"yyyy-mm-dd",
            endDate : new Date()
        }).on('changeDate',function(e){
            var endTime = e.date;
            $('#qBeginTime').datepicker('setEndDate',endTime);
        });
    });

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
