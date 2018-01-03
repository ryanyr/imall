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
				还款计划信息
			</h1>
		</section>

		<!-- Main content -->
		<form class="form-inline" role="form" id="queryform" name="queryform" method="post" action="<%=basePath%>borrowRepay/repayPlan/list">
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
										<label for="inputOrderState">还款状态</label>
										<select class="form-control" id="inputOrderState" name="state" value="${searchParam.state}">
											<option value="">全部</option>
											<option value="10">已还款</option>
											<option value="20">未还款</option>
										</select>
									</div>
									<div class="form-group">
										<label>应还时间</label>
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
										<th>借款金额</th>
										<th>应还金额</th>
										<th>应还时间</th>
										<th>还款状态</th>
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
											<td><fmt:formatDate value="${order.repayTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<c:if test="${order.state==10}">已还款</c:if>
												<c:if test="${order.state==20}">未还款</c:if>
											</td>
											<td>
												<c:choose>
													<c:when test="${order.state==20}">
														<span><a href="javascript:void(0);" class="modal-confirm" data-orderid="${order.orderId}"
																 data-repayamount="${order.repayAmount}"
																 data-penaltyamount="${order.penaltyAmount}"
																 data-penaltyday="${order.penaltyDay}">确认还款</a></span>
														<span><a href="javascript:void(0);" data-toggle="modal" data-target="#modal-withhold" >代扣</a></span>
													</c:when>
													<c:otherwise>
														<span>--</span>
													</c:otherwise>
												</c:choose>
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
									action="/borrowRepay/repayPlan/list" />
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

	<div class="modal fade" id="modal-default">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">确认还款</h4>
				</div>
				<div class="modal-body">

					<div class="box box-info">
						<!-- form start -->
						<form method="post" action="<%=basePath%>borrowRepay/repayLog/orderRepay" class="form-horizontal" id="orderRepayForm" enctype="multipart/form-data">
							<input type="hidden" name="orderId" id="orderId" value="${order.orderId}" />
							<div class="box-body">
								<div class="form-group">
									<label for="inputRepayAccount" class="col-sm-2 control-label">还款帐号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputRepayAccount" name="repayAccount">
									</div>
								</div>

								<div class="form-group">
									<label for="inputRepayWay" class="col-sm-2 control-label">还款方式</label>
									<div class="col-sm-10">
										<select class="form-control" id="inputRepayWay" name="repayWay">
											<option value="10">代扣</option>
											<option value="20">银行卡转账</option>
											<option value="30">支付宝转账</option>
											<option value="40">快捷支付</option>
											<option value="50">京东快捷支付</option>
											<option value="60">聚合支付支付宝支付</option>
										</select>
									</div>
								</div>

								<div class="form-group">
									<label for="inputSerialNumber" class="col-sm-2 control-label">流水账号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputSerialNumber" name="serialNumber">
									</div>
								</div>

								<div class="form-group">
									<label for="inputRepayAmount" class="col-sm-2 control-label">应还金额</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputRepayAmount" name="repayAmount" value=${order.repayAmount}>
									</div>
								</div>

								<div class="form-group">
									<label for="inputPenaltyAmount" class="col-sm-2 control-label">逾期罚金</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputPenaltyAmount" name="penaltyAmount" value=${order.penaltyAmount}>
									</div>
								</div>

								<div class="form-group">
									<label for="inputPenaltyDay" class="col-sm-2 control-label">逾期天数</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="inputPenaltyDay" name="penaltyDay" value=${order.penaltyDay}>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 control-label">还款时间</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="payTime" id="inputPayTime" value="${searchParam.payTime}" />
									</div>

								</div>
								<div class="form-group">
									<label for="inputRepayVoucher" class="col-sm-2 control-label">还款凭证</label>
									<div class="col-sm-5">
										<input type="file" id="inputRepayVoucher" name="repayVoucher">
									</div>
								</div>
							</div>
							<!-- /.box-body -->
						</form>
					</div>
					<!-- /.box -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="modal-default-submit">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade" id="modal-withhold">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">代扣</h4>
				</div>
				<div class="modal-body">

					<div class="box box-info">
						<!-- form start -->
						<form action="<%=basePath%>review/repayPlan/withhold" class="form-horizontal" id="withholdForm">
							<input type="hidden" name="orderId" value="${order.orderId}" />
							<h3>是否确认代扣</h3>
							<!-- /.box-body -->
						</form>
					</div>
					<!-- /.box -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="modal-withhold-submit" >确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</div>
<!-- ./wrapper -->

<script>

    $(function() {

        $(".modal-confirm").on('click', function(){
            var orderid = $(this).data("orderid")
            var repayamount = $(this).data("repayamount")
            var penaltyamount = $(this).data("penaltyamount")
            var penaltyday = $(this).data("penaltyday")

            $('#orderId').val(orderid)
            $('#inputRepayAmount').val(repayamount)
            $('#inputPenaltyAmount').val(penaltyamount)
            $('#inputPenaltyDay').val(penaltyday)

            $('#modal-default').modal('show');
        });

        $("#modal-default-submit").on("click", function() {
            var repayAccount=orderRepayForm.repayAccount.value.trim();
            var serialNumber=orderRepayForm.serialNumber.value.trim();
            var payTime=orderRepayForm.payTime.value.trim();
            var repayVoucher=orderRepayForm.repayVoucher.value.trim();
            if(repayAccount=="" || repayAccount==null){
                alert("您必须要进行还款账号的输入");
                return;
            }
            if(serialNumber=="" || serialNumber==null){
                alert("您必须要进行流水账号的输入");
                return;
            }
            if(payTime=="" || payTime==null){
                alert("您必须要进行还款时间的输入");
                return;
            }
            if(repayVoucher==null || repayVoucher==""){
                alert("您必须上传还款凭证");
                return;
            }
            $("#orderRepayForm").ajaxSubmit({
                dataType: "json",
                success: function(data) {
                    if (data.code == "200") {
                        location.href = "<%=basePath%>borrowRepay/repayPlan/list";
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
        // 代扣
        $("#modal-withhold-submit").on("click", function() {
            $("#withholdForm").ajaxSubmit({
                dataType: "json",
                success: function(data) {
                    if (data.code == "200") {
                        $('#modal-certificate').modal('hide');
                    } else {
                        alert(data.msg);
                    }
                }
            });
        });
    });

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

    $('#inputPayTime').datepicker({
        todayBtn : "linked",
        autoclose : true,
        todayHighlight : true,
        language:"zh-CN",
        format:"yyyy-mm-dd",
        endDate : new Date()
    }).on('changeDate',function(e){
        var payTime = e.date;
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
