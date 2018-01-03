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

	<link rel="stylesheet" href="<%=basePath %>JavaScript/plugins/sweetalert/sweetalert.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="../base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				订单信息
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<input type="hidden" id="orderId" value="${order.id}" />

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
					<div class="container">
						<div class="row" >
							<div class="col-xs-6 col-sm-3">
								<label>姓名：</label>
								<span>${user.realName}</span>
								<c:choose>
								<c:when test="${user.realName == null}">
								</c:when>
								<c:otherwise>
								<span><c:choose>
											<c:when test="${userAuth.certificateState eq '30'}">
												<span class="label label-success">已认证
											</c:when>
											<c:otherwise>
												<span class="label label-danger" data-toggle="modal" data-target="#modal-certificate">去认证</span>
											</c:otherwise>
											</c:choose>
										</span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>手机号：</label>
								<span>${user.phone}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>身份证号：</label>
								<span>${user.idNo}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>资格证书号：</label>
								<span>${user.certificateNo}</span>
							</div>
						</div>
						<div class="row" >
							<div class="col-xs-6 col-sm-3">
								<label>银行卡号：</label>
								<span>${user.cardNo}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>所属银行：</label>
								<span>${user.bank}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>职务：</label>
								<span>${user.title}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>所属公司：</label>
								<span>${user.companyName}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
							</div>
						</div>

						<div class="row" >
							<div class="col-xs-6 col-sm-3">
								<label>信用卡号：</label>
								<span>${user.creditCardNo}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>所属银行：</label>
								<span>${user.creditBank}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label></label>
								<span></span>
							</div>
							<div class="col-xs-6 col-sm-3">
							</div>
						</div>

					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-header with-border">
					<h3 class="box-title">身份证照片</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.livingImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.livingImg} target="_blank"><img src=${user.livingImg} style="width:150px;height:150px" alt="手持身份证照片"/></a>
								</c:otherwise>
							</c:choose>

						</div>
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.frontImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.frontImg} target="_blank"><img src=${user.frontImg} style="width:150px;height:150px" alt="身份证正面照"/></a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.backImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.backImg} target="_blank"><img src=${user.backImg} style="width:150px;height:150px" alt="身份证背面照"/></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="box-header with-border">
					<h3 class="box-title">银行卡照片</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.bankFrontImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.bankFrontImg} target="_blank"><img src=${user.bankFrontImg} style="width:150px;height:150px" alt="银行卡正面照片"/></a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.bankBackImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.bankBackImg} target="_blank"><img src=${user.bankBackImg} style="width:150px;height:150px" alt="银行卡背面照片"/></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="box-header with-border">
					<h3 class="box-title">信用卡照片</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.creditFrontImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.creditFrontImg} target="_blank"><img src=${user.creditFrontImg} style="width:150px;height:150px" alt="信用卡正面照片"/></a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.bankBackImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.bankBackImg} target="_blank"><img src=${user.bankBackImg} style="width:150px;height:150px" alt="信用卡背面照片"/></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="box-header with-border">
					<h3 class="box-title">行销系统职务照片</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.titleImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.titleImg} target="_blank"><img src=${user.titleImg} style="width:150px;height:150px" alt="职务照片"/></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="box-header with-border">
					<h3 class="box-title">资格证书认证照片</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<div class="col-sm-6 col-md-3">
							<c:choose>
								<c:when test="${user.certificateImg == null}">
									<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
								</c:when>
								<c:otherwise>
									<a href=${user.certificateImg} target="_blank"><img src=${user.certificateImg} style="width:150px;height:150px" alt="资格证书认证照片"/></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>

			</div>
			<!-- /.box -->

			<!-- borrow info -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">借款信息</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
								title="Collapse">
							<i class="fa fa-minus"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="container">
						<div class="row" >
							<div class="col-xs-6 col-sm-3">
								<label>保单金额：</label>
								<span>${order.policyAmount} 元</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>提现金额：</label>
								<span>${order.amount} 元</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>费用：</label>
								<span>${order.serviceFee} 元</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>期限：</label>
								<span>${order.timeLimit} 天</span>
							</div>
						</div>
						<div class="row" >
							<div class="col-xs-6 col-sm-3">
								<label>订单编号：</label>
								<span>${order.orderNo}</span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label>借款日期：</label>
								<span><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label></label>
								<span></span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label></label>
								<span></span>
							</div>
							<div class="col-xs-6 col-sm-3">
								<label></label>
								<span></span>
							</div>
							<div class="col-xs-6 col-sm-3">
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-body -->
				<div class="box-header with-border">
					<h3 class="box-title">费用清单照片</h3>
				</div>
				<div class="box-body">
					<div class="row">
						<c:choose>
							<c:when test="${order.policyImg == null}">
								<div align="center" style="width:150px;height:150px"><span>暂无照片</span></div>
							</c:when>
							<c:otherwise>
								<a href=${order.policyImg} target="_blank"><img src=${order.policyImg} style="width:150px;height:150px" alt="费用清单照片"/></a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<!-- /.box -->

			<!-- audit info -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">审核信息</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
								title="Collapse">
							<i class="fa fa-minus"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="detail-section">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group">
									<textarea id="auditRemark" name="auditRemark" class="form-control" rows="3" placeholder="备注"></textarea>
								</div>
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
			<span><button id="auditRefuse" type="button" class="btn bg-maroon margin">不通过</button></span>
			<span><button id="auditPass" type="button" class="btn bg-primary margin">通过</button></span>
		</div>

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

<script src="<%=basePath %>JavaScript/plugins/jquery/jquery.form.js"></script>
<script src="<%=basePath %>JavaScript/plugins/sweetalert/sweetalert.min.js"></script>

<!-- page script -->
<script>
    function reviewAudit(dataParam) {
        $.ajax({
            async: false,
            type: "POST",
            data: dataParam.data,
            dataType: "json",
            url: dataParam.url,
            success: function(data) {
                if (data.code == "200") {
                    location.href = "<%=basePath%>review/onLoanReview/list";
                } else {
                    alert(data.msg);
                }
            }
        });
    }

	$(function () {

        $("#auditRefuse").on('click', function () {
            swal({
                title:"确认不通过订单吗？",
                type:"warning",
                showCancelButton:true,
                confirmButtonColor:"#DD6B55",
                confirmButtonText:"确认",
                cancelButtonText:"取消"
            }, function(isConfirm) {
                if (isConfirm) {
                    var dataParam = {};
                    dataParam.data = {"orderId": $("#orderId").val(), "auditState":"32", "auditRemark":$("#auditRemark").val(), "refuseReason":$("#refuseReason").val()};
                    dataParam.url = "<%=basePath%>review/onLoanReview/orderAudit";

                    reviewAudit(dataParam);
                }
            });
        })

        $("#auditPass").on('click', function () {
            swal({
                title:"确认通过订单吗？",
                type:"warning",
                showCancelButton:true,
                confirmButtonColor:"#DD6B55",
                confirmButtonText:"确认",
                cancelButtonText:"取消"
            }, function(isConfirm) {
                if (isConfirm) {
                    var dataParam = {};
                    dataParam.data = {"orderId": $("#orderId").val(), "auditState":"31", "auditRemark":$("#auditRemark").val(), "refuseReason":$("#refuseReason").val()};
                    dataParam.url = "<%=basePath%>review/onLoanReview/orderAudit";

                    reviewAudit(dataParam);
                }
            });

        })
    })
</script>
</body>
</html>
