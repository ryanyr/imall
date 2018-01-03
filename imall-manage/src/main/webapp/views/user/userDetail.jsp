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
				客户信息
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="box">
				<div class="box-header with-border">
					<h5 class="box-title">基本信息</h5>

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
												<span class="label label-danger">去认证</span>
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

		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<%@include file="../base/footer.jsp"%>

	<!-- Control Sidebar -->

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>

	<div class="modal fade" id="modal-certificate">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">认证审核</h4>
				</div>
				<div class="modal-body">

					<div class="box box-info">
						<!-- form start -->
						<form method="post" action="<%=basePath%>review/onReview/userAuthAudit" class="form-horizontal" id="certAuditForm" enctype="multipart/form-data">
							<input type="hidden" name="userId" value="${order.userId}" />
							<div class="box-body">
								<div class="form-group">
									<label class="col-sm-2 control-label">参考网站</label>
									<div class="col-sm-10"><a href="http://iir.circ.gov.cn/web/" target="_blank">保险中介监管信息系统</a>
									</div>
								</div>
								<div class="form-group">
									<label for="inputState" class="col-sm-2 control-label">审批意见</label>
									<div class="col-sm-10">
										<select class="form-control" id="inputState" name="certState">
											<option value="30">通过</option>
											<option value="10">拒绝</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="inputCertImg" class="col-sm-2 control-label">认证截图</label>
									<div class="col-sm-10">
										<input type="file" id="inputCertImg" name="certificateImg">
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
					<button type="button" class="btn btn-primary" id="modal-certificate-submit">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</div>
<!-- ./wrapper -->

<!-- page script -->
<script>
$(function() {
    // 用户证书认证
    $("#modal-certificate-submit").on("click", function() {
        $("#certAuditForm").ajaxSubmit({
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

</script>
</body>
</html>
