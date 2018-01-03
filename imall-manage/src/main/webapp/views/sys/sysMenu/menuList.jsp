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
	<!-- Bootstrap 3.3.7 -->

	<link rel="stylesheet" href="<%=basePath %>JavaScript/plugins/treetable/css/jquery.treetable.css">
	<link rel="stylesheet" href="<%=basePath %>JavaScript/plugins/treetable/css/jquery.treetable.theme.im.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="../../base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				菜单管理
			</h1>
		</section>

		<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div class="box-body">
									<div class="row">
										<span><button id="expandAll" type="button" class="btn btn-primary">展开所有</button></span>
										<span><button id="collapseAll" type="button" class="btn btn-primary">收缩所有</button></span>
										<span><button id="menuAddBtn" data-toggle="modal" data-target="#modal-default" type="button" class="btn btn-primary">添加</button></span>
										<span><button id="menuModifyBtn" data-toggle="modal" data-target="#modal-default" disabled="disabled" type="button" class="btn btn-primary">修改</button></span>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
									<tr>
										<th></th>
										<th>菜单名称</th>
										<th>图标</th>
										<th>备注</th>
										<th>排序</th>
										<th>是否菜单</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="menu">
										<tr data-tt-id="${menu.menuId}">
											<td><input type="checkbox" value="${menu.menuId}" name="menuId"/></td>
											<td>${menu.menuName}</td>
											<td>${menu.dispImage}</td>
											<td>${menu.remark}</td>
											<td>${menu.menuOrder}</td>
											<td><c:choose><c:when test="${menu.isMenu eq '1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
										</tr>
										<c:if test="${not empty menu.melist}">
											<c:forEach items="${menu.melist}" var="memenu" varStatus="statu">
												<tr data-tt-id="${menu.menuId}-${memenu.menuId}" data-tt-parent-id="${menu.menuId}">
													<td><input type="checkbox" value="${memenu.menuId}" name="menuId" id="" /></td>
													<td>${memenu.menuName}</td>
													<td>${memenu.dispImage}</td>
													<td>${memenu.remark}</td>
													<td>${memenu.menuOrder}</td>
													<td><c:choose><c:when test="${memenu.isMenu eq '1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
												</tr>
											</c:forEach>
										</c:if>
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


			</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->

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
					<h4 class="modal-title" id="modalTitle">添加</h4>
				</div>
				<div class="modal-body">

					<div class="box box-info">
							<div class="box-body">
								<form id="menuform" class="form-horizontal" role="form">
									<fieldset>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="menu_name">菜单名称</label>
											<div class="col-sm-4">
												<input class="form-control" id="menu_name" type="text" />
											</div>
											<label class="col-sm-2 control-label" for="menu_url">菜单地址</label>
											<div class="col-sm-4">
												<input class="form-control" id="menu_url" type="text" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="menu_disp_image">图标</label>
											<div class="col-sm-4">
												<input class="form-control" id="menu_disp_image" type="text" />
											</div>
											<label class="col-sm-2 control-label" for="menu_order">排序</label>
											<div class="col-sm-4">
												<input class="form-control" id="menu_order" type="number" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="inputStatus">是否启用</label>
											<div class="col-sm-4">
												<select id="inputStatus" class="form-control">
													<option value="0">是</option>
													<option value="1">否</option>
												</select>
											</div>
											<label class="col-sm-2 control-label" for="is_menu">是否菜单</label>
											<div class="col-sm-4">
												<select id="is_menu" class="form-control">
													<option value="1">是</option>
													<option value="0">否</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="menu_desc">菜单说明</label>
											<div class="col-sm-4">
												<input class="form-control" id="menu_desc" type="text" />
											</div>
										</div>
									</fieldset>
								</form>
							</div>
					</div>
					<!-- /.box -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" data-type="create" id="modal-default-submit">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</div>
<!-- ./wrapper -->

<script src="<%=basePath %>JavaScript/plugins/treetable/jquery.treetable.js"></script>
<!-- page script -->
<script>
	// 添加或修改菜单
    function saveOrUpdateMenu(params) {
        $.ajax({
            async: true,
            type: "POST",
            data: params,
            dataType: "json",
            url: "<%=basePath%>sys/sysMenu/saveOrUpdate",
            success: function(data) {
                if (data.code == "200") {
                    location.href = "<%=basePath%>sys/sysMenu/list";
                } else {
                    alert(data.msg);
                }
                $("#modal-default-submit").removeAttr("disabled");
            },
			error:function () {
                $("#modal-default-submit").removeAttr("disabled");
            }
        });
	}

	$(function(){
        $("#example2").treetable({
            expandable : true,
            column: 1
        }).treetable('expandAll');

        $("#expandAll").on("click", function () {
            $("#example2").treetable("expandAll");
        })

        $("#collapseAll").on("click", function () {
            $("#example2").treetable("collapseAll");
        })

		$(":checkbox[name='menuId']").on("click", function () {
			if ($(this).prop("checked") == true) {
			    $(":checkbox[name='menuId']").prop("checked", false);
			    $(this).prop("checked", true);

			    if ($(this).val() == "1") {
                    $("#menuAddBtn").attr("disabled", "disabled");
				} else {
                    $("#menuAddBtn").removeAttr("disabled");
				}

                $("#menuModifyBtn").removeAttr("disabled");
			} else {
                $("#menuAddBtn").removeAttr("disabled");
                $("#menuModifyBtn").attr("disabled", "disabled");
			}
        })

        $('#modal-default').on('hide.bs.modal', function () {
			$("#menuform")[0].reset();
        })

        $("#menuAddBtn").on("click", function () {
            $("#modalTitle").html("添加");
            $("#menuModifyBtn").attr("data-type", "create");
        });

        // 显示更新菜单
        $("#menuModifyBtn").on("click", function () {
            var menuId = $(":checkbox[name='menuId']:checked").val();
            $("#modalTitle").html("修改");
            $("#menuModifyBtn").attr("data-type", "update");
            if (menuId != undefined) {
                $.ajax({
                    async: true,
                    type: "get",
                    data: {"menuId":menuId},
                    dataType: "json",
                    url: "<%=basePath%>sys/sysMenu/getMenuInfo",
                    success: function(data) {
                        console.log("success");
                        console.log(data);
                        if (data.code == "200") {
                            var menu = data.data.menu;
                            $("#menu_name").val(menu.menuName);
							$("#menu_url").val(menu.linkUrl);
							$("#menu_disp_image").val(menu.dispImage);
							$("#menu_order").val(menu.menuOrder);
                            $("#is_menu").val(menu.isMenu);
							$("#inputStatus").val(menu.status);
							$("#menu_desc").val(menu.remark);
                        } else {
                            alert(data.msg);
                        }
                    }
                });
			}

        })

		// 添加菜单
		$("#modal-default-submit").on('click', function () {
            $("#modal-default-submit").attr("disabled", "disabled");
		    var menuId = $(":checkbox[name='menuId']:checked").val();

            var type = $("#menuModifyBtn").attr("data-type");

		    var menu = {
                "menuName":$("#menu_name").val(),
                "linkUrl":$("#menu_url").val(),
                "dispImage":$("#menu_disp_image").val(),
                "menuOrder":$("#menu_order").val(),
                "isMenu":$("#is_menu").val(),
                "status":$("#inputStatus").val(),
                "remark":$("#menu_desc").val()
            };
		    if (type=="create"){
                menu["superiorId"]= menuId;
			} else if (type=="update") {
                menu['menuId'] = menuId;
			}
		    var data = {
		                 "type": type,
				         "menu": JSON.stringify(menu)
						};
            saveOrUpdateMenu(data);
        })


    });
</script>
</body>
</html>
